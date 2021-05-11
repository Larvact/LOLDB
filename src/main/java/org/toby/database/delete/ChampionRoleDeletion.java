package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChampionRoleDeletion extends Deletion{

    private static final String DELETECHAMPIONROLEDATASQLSTATEMENT = "DELETE FROM [dbo].[ChampionRole];";

    public ChampionRoleDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        try {
            connector.connect();
            try (PreparedStatement deleteChampionRoleStatement = this.connector.getConnection().prepareStatement(DELETECHAMPIONROLEDATASQLSTATEMENT)) {
                deleteChampionRoleStatement.execute();
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
