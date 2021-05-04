package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDeletion extends Deletion{

    private static final String DELETEROLEDATASQLSTATEMENT = "DELETE FROM dbo.Role; DBCC CHECKIDENT ('Role', RESEED, 0);";

    public RoleDeletion(LolDbConnector connection) {
        super(connection);
    }

    @Override
    public void delete() {
        try {
            connection.connect();
            try (PreparedStatement deleteRoleStatement = this.connection.getConnection().prepareStatement(DELETEROLEDATASQLSTATEMENT)) {
                deleteRoleStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                this.connection.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
