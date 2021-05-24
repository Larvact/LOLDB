package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamDeletion extends Deletion{

    private static final String DELETETEAMDATASQLSTATEMENT = "DELETE FROM [dbo].[Team]; DBCC CHECKIDENT ('Team', RESEED, 0);";

    public TeamDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        connector.connect();
        try (PreparedStatement deleteRoleStatement = this.connector.getConnection().prepareStatement(DELETETEAMDATASQLSTATEMENT)) {
            deleteRoleStatement.execute();
        }
        catch (SQLException throwables) {
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
