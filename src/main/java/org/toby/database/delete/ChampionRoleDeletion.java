package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChampionRoleDeletion extends Deletion{

    private static final String DELETECHAMPIONROLEDATASQLSTATEMENT = "DELETE FROM [dbo].[ChampionRole];";

    public ChampionRoleDeletion(LolDbConnector connection) {
        super(connection);
    }

    @Override
    public void delete() {
        try {
            connection.connect();
            try (PreparedStatement deleteChampionRoleStatement = this.connection.getConnection().prepareStatement(DELETECHAMPIONROLEDATASQLSTATEMENT)) {
                deleteChampionRoleStatement.execute();
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
