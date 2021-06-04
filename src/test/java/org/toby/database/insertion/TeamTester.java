package org.toby.database.insertion;

import org.junit.*;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.TeamDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.TeamInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TeamTester {

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;
    private TestTableDataRetriever testOutcomeRetrieval;
    private LocalDateTime testInitialiserTime;
    private final int expectedNumberOfTeams = 3;
    private List<String> expectedTeamsList;

    @BeforeClass
    public static void setUpData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new TeamInsertion(connector);
        deletion = new TeamDeletion(connector);
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
    public void ensureThatThreeTeamsHaveBeenInsertedIntoTheTeamsTable(){
        try(PreparedStatement executeSpTeamTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spTeamTableTest] @ExpectedNumberOfTeams = ?")) {
            executeSpTeamTableTest.setInt(1, this.expectedNumberOfTeams);
            executeSpTeamTableTest.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, this.testOutcomeRetrieval.getLastestTestResult());
    }

    @Test
    public void ensureThatTheCorrectTeamsHaveBeenAddedToTheTeamsTable(){
        populateExpectedTeamsList();
        for(String team : this.expectedTeamsList) {
            this.testOutcomeRetrieval.setTestInitialiserTime(LocalDateTime.now());
            try (PreparedStatement executeSpTeamTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSpecificTeamTest] @SelectedTeam = ?")) {
                executeSpTeamTableTest.setString(1, team);
                executeSpTeamTableTest.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Assert.assertEquals(1, this.testOutcomeRetrieval.getLastestTestResult());
        }
    }

    private void populateExpectedTeamsList(){
        this.expectedTeamsList = new ArrayList<>();
        this.expectedTeamsList.add("Blue");
        this.expectedTeamsList.add("Purple");
        this.expectedTeamsList.add("Neither");
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
