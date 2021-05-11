package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDeletion extends Deletion{

    private static final String DELETEROLEDATASQLSTATEMENT = "DELETE FROM dbo.Role; DBCC CHECKIDENT ('Role', RESEED, 0);";

    public RoleDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        try {
            connector.connect();
            try (PreparedStatement deleteRoleStatement = this.connector.getConnection().prepareStatement(DELETEROLEDATASQLSTATEMENT)) {
                deleteRoleStatement.execute();
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
    }
}
