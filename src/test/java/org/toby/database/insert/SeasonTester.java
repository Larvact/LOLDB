package org.toby.database.insert;

import org.junit.*;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.SeasonDeletion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.csv.reader.CsvGameReader;
import org.toby.csv.reader.CsvReader;
import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.season.SeasonCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeasonTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;
    private TestTableDataRetriever testOutcomeRetrieval;
    private LocalDateTime testInitialiserTime;
    private final int expectedNumberOfSeasons = 1;
    private List<Integer> expectedSeasonsList;

    @BeforeClass
    public static void setUpData(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new SeasonInsertion(connector, new SeasonCollection(csvReader.getRowDetailsMap().values()));
        deletion = new SeasonDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    @Before
    public void setupTestInitialiserTime(){
        this.testInitialiserTime = LocalDateTime.now();
        this.testOutcomeRetrieval = new TestTableDataRetriever(connector, this.testInitialiserTime);
        connector.connect();
    }

    @Test
    public void ensureThatOneSeasonHasBeenInsertedIntoTheSeasonTable(){
        try(PreparedStatement executeSpSeasonTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSeasonTableTest] @ExpectedNumberOfSeasons = ?")) {
            executeSpSeasonTableTest.setInt(1, this.expectedNumberOfSeasons);
            executeSpSeasonTableTest.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, this.testOutcomeRetrieval.getLastestTestResult());
    }

    @Test
    public void ensureThatTheCorrectSeasonHasBeenAddedToTheSeasonTable(){
        populateExpectedTeamsList();
        for(Integer season : this.expectedSeasonsList) {
            this.testOutcomeRetrieval.setTestInitialiserTime(LocalDateTime.now());
            try (PreparedStatement executeSpTeamTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSpecificSeasonTest] @SelectedSeason = ?")) {
                executeSpTeamTableTest.setInt(1, season);
                executeSpTeamTableTest.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Assert.assertEquals(1, this.testOutcomeRetrieval.getLastestTestResult());
        }
    }

    private void populateExpectedTeamsList(){
        this.expectedSeasonsList = new ArrayList<>();
        this.expectedSeasonsList.add(9);
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

