package org.toby.database.deletion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

public class TeamDeletionTester {

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;
    private TestTableDataRetriever testOutcomeRetrieval;
    private LocalDateTime testInitialiserTime;

    @BeforeClass
    public static void setData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new TeamInsertion(connector);
        deletion = new TeamDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
    }

    @Before
    public void setupTestInitialiserTime(){
        this.testInitialiserTime = LocalDateTime.now();
        testOutcomeRetrieval = new TestTableDataRetriever(connector, this.testInitialiserTime);
    }

    @Test
    public void ensureDataHasBeenDeletedFromTheTeamTable(){
        sqlManager.delete();
        connector.connect();
        try(PreparedStatement executeSpEnsureEmptyTable = connector.getConnection().prepareStatement("EXECUTE [test].[spEnsureEmptyTable] @SchemaName = ?, @SelectedTable = ?")){
            executeSpEnsureEmptyTable.setString(1, "dbo");
            executeSpEnsureEmptyTable.setString(2, "Team");
            executeSpEnsureEmptyTable.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
    }
}