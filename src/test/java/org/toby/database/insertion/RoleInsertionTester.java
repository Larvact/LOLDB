package org.toby.database.insertion;

import org.junit.*;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.RoleDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.RoleInsertion;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;
import org.toby.valueobject.jsondeserialise.Champion;
import org.toby.valueobject.jsondeserialise.databasetransfer.role.RoleCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RoleInsertionTester {
    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static Insertion insertion;
    private static Deletion deletion;
    private LocalDateTime testInitialiserTime;
    private final short expectedNumberOfRoles = 6;
    private TestTableDataRetriever testOutcomeRetrieval;

    @BeforeClass
    public static void setupData(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        mapper = new ChampionCollectionMapper(reader);
        insertion = new RoleInsertion(connector, mapper.getCollection());
        deletion = new RoleDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    @Before
    public void setupTestInitialiserTime(){
        this.testInitialiserTime = LocalDateTime.now();
        testOutcomeRetrieval = new TestTableDataRetriever(connector, this.testInitialiserTime);
        connector.connect();
    }

    @Test
    public void ensureTheNumberOfRolesInsideTheRolesTableIsCorrect(){
        try(PreparedStatement executeSpRoleTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spRoleTableTest] ?")){
            executeSpRoleTableTest.setInt(1, this.expectedNumberOfRoles);
            executeSpRoleTableTest.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
    }

    @Test
    public void ensureThatAllSpecificRolesHaveBeenInsertedIntoTheRoleTable(){
        for(String role : new RoleCollection(mapper.getCollection()).getRoles()) {
            testOutcomeRetrieval.setTestInitialiserTime(LocalDateTime.now());
            try (PreparedStatement executeSpSpecificRoleTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSpecificRoleTest] ?")) {
                executeSpSpecificRoleTest.setString(1, role);
                executeSpSpecificRoleTest.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
        }
    }

    @After
    public void teardown(){
        connector.closeConnection();
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
