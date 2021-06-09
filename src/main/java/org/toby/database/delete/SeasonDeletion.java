package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeasonDeletion extends Deletion{

    private static final String DELETESEASONDATASQLSTATEMENT = "DELETE FROM [dbo].[Season]; DBCC CHECKIDENT ('Season', RESEED, 0);";

    public SeasonDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        connector.connect();
        try (PreparedStatement deleteSeasonStatement = this.connector.getConnection().prepareStatement(DELETESEASONDATASQLSTATEMENT)) {
            deleteSeasonStatement.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
    }
}
