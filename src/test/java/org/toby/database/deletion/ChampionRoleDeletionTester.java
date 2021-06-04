package org.toby.database.deletion;

import org.junit.*;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.ChampionDeletion;
import org.toby.database.delete.ChampionRoleDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.RoleDeletion;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.ChampionRoleInsertion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.RoleInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ChampionRoleDeletionTester {

    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static Insertion championInsertion;
    private static Deletion championDeletion;
    private static Insertion roleInsertion;
    private static Deletion roleDeletion;
    private static Insertion championRoleInsertion;
    private static Deletion championRoleDeletion;
    private TestTableDataRetriever testOutcomeRetrieval;
    private LocalDateTime testInitialiserTime;

    @BeforeClass
    public static void setUpData(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        mapper = new ChampionCollectionMapper(reader);
        insertChampionData();
        insertRoleData();
        insertChampionRoleRoleData();
    }

    private static void insertChampionData(){
        championInsertion = new ChampionInsertion(connector, mapper.getCollection());
        championDeletion = new ChampionDeletion(connector);
        sqlManager = new SQLTableManager(championInsertion, championDeletion);
        sqlManager.insert();
    }

    private static void insertRoleData(){
        roleInsertion = new RoleInsertion(connector, mapper.getCollection());
        sqlManager.setInsertion(roleInsertion);
        sqlManager.insert();
    }

    private static void insertChampionRoleRoleData(){
        championRoleInsertion = new ChampionRoleInsertion(connector, mapper.getCollection());
        sqlManager.setInsertion(championRoleInsertion);
        sqlManager.insert();
    }

    @Before
    public void setupTestInitialiserTime(){
        this.testInitialiserTime = LocalDateTime.now();
        testOutcomeRetrieval = new TestTableDataRetriever(connector, this.testInitialiserTime);
    }

    @Test
    public void ensureDataHasBeenDeletedFromTheSummonerSpellTable(){
        deleteChampionRoleData();
        connector.connect();
        try(PreparedStatement executeSpEnsureEmptyTable = connector.getConnection().prepareStatement("EXECUTE [test].[spEnsureEmptyTable] @SchemaName = ?, @SelectedTable = ?")){
            executeSpEnsureEmptyTable.setString(1, "dbo");
            executeSpEnsureEmptyTable.setString(2, "ChampionRole");
            executeSpEnsureEmptyTable.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
    }

    private void deleteChampionRoleData(){
        championRoleDeletion = new ChampionRoleDeletion(connector);
        sqlManager.setDeletion(championRoleDeletion);
        sqlManager.delete();
    }

    @AfterClass
    public static void cleanupResidualData(){
        deleteChampionData();
        deleteRoleData();
    }

    private static void deleteChampionData(){
        sqlManager.setDeletion(championDeletion);
        sqlManager.delete();
    }

    private static void deleteRoleData(){
        roleDeletion = new RoleDeletion(connector);
        sqlManager.setDeletion(roleDeletion);
        sqlManager.delete();
    }
}
