package org.toby.database.testtable;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestTableDataRetriever {

    private static final String RETRIEVELASTTESTRESULTSQLSTATEMENT = "SELECT TOP 1 tst.TestOutcome FROM [test].[TestResults] tst WHERE tst.DateOfTestExecution > ? ORDER BY tst.DateOfTestExecution;";
    private LocalDateTime testInitialiserTime;
    private LolDbConnector connector;

    public TestTableDataRetriever(LolDbConnector connector, LocalDateTime testInitialiserTime) {
        this.connector = connector;
        this.testInitialiserTime = testInitialiserTime;
    }

    public int getLastestTestResult() {
        this.connector.connect();
        try(PreparedStatement getLatestTestResult = this.connector.getConnection().prepareStatement(RETRIEVELASTTESTRESULTSQLSTATEMENT)){
            getLatestTestResult.setTimestamp(1, Timestamp.valueOf(this.testInitialiserTime));
            ResultSet results = getLatestTestResult.executeQuery();
            if(results.next()) {
                return results.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                this.connector.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The test result could not be retrieved. Please double check the testInitialiserTime.");
    }

    public void setTestInitialiserTime(LocalDateTime testInitialiserTime) {
        this.testInitialiserTime = testInitialiserTime;
    }
}
