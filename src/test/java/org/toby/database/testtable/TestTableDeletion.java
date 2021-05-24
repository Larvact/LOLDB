package org.toby.database.testtable;

import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestTableDeletion extends Deletion {

    private static final String DELETETESTRESULTSDATASQLSTATEMENT = "DELETE FROM [test].[TestResults]; DBCC CHECKIDENT ('TestResults', RESEED, 0);";


    protected TestTableDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        this.connector.connect();
        try(PreparedStatement deleteTestResultsStatement = this.connector.getConnection().prepareStatement(DELETETESTRESULTSDATASQLSTATEMENT)){
            deleteTestResultsStatement.execute();
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
    }
}
