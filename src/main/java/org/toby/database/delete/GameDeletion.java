package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDeletion extends Deletion{

    private static final String DELETEGAMEDATASQLSTATEMENT = "DELETE FROM [dbo].[Game]; DBCC CHECKIDENT ('Game', RESEED, 0);";

    public GameDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        try {
            connector.connect();
            try (PreparedStatement deleteGameStatement = this.connector.getConnection().prepareStatement(DELETEGAMEDATASQLSTATEMENT)) {
                deleteGameStatement.execute();
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
