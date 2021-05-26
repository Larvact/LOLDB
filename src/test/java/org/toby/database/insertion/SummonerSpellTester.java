package org.toby.database.insertion;

import org.junit.*;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.SummonerSpellDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.SummonerSpellInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.json.mappers.SummonerSpellCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;
import org.toby.valueobject.jsondeserialise.SummonerSpell;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SummonerSpellTester {
    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static SummonerSpellCollectionMapper mapper;
    private static Insertion insertion;
    private static Deletion deletion;
    private LocalDateTime testInitialiserTime;
    private final short expectedNumberOfSummonerSpells = 15;
    private TestTableDataRetriever testOutcomeRetrieval;

    @BeforeClass
    public static void setUpData(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.SUMMONER_SPELL_DATA_FILE_LOCATION.toString()));
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        mapper = new SummonerSpellCollectionMapper(reader);
        insertion = new SummonerSpellInsertion(connector, mapper.getCollection());
        deletion = new SummonerSpellDeletion(connector);
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
    public void ensureTheNumberOfSummonerSpellsInsideTheSummonerSpellTableIsCorrect(){
        try(PreparedStatement executeSpSummonerSpellTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSummonerSpellTableTest] ?")){
            executeSpSummonerSpellTableTest.setInt(1, this.expectedNumberOfSummonerSpells);
            executeSpSummonerSpellTableTest.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
    }

    @Test
    public void ensureThatAllSpecificSummonerSpellsHaveBeenInsertedIntoTheSummonerSpellTable(){
        for(SummonerSpell summonerSpell : mapper.getCollection().getSummonerSpells()) {
            testOutcomeRetrieval.setTestInitialiserTime(LocalDateTime.now());
            try (PreparedStatement executeSpSpecificSummonerSpellTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSpecificSummonerSpellTest] ?")) {
                executeSpSpecificSummonerSpellTest.setString(1, summonerSpell.getName());
                executeSpSpecificSummonerSpellTest.execute();
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
