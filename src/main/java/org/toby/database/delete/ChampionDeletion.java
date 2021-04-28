package org.toby.database.delete;

import org.toby.database.LolDbConnector;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ChampionDeletion extends Deletion{

    private static final String DELETECHAMPIONDATASQLSTATEMENT = "DELETE FROM dbo.Champion; DBCC CHECKIDENT ('Champion', RESEED, 0);";

    public ChampionDeletion(LolDbConnector connection) {
        super(connection);
    }

    @Override
    public void delete() {
        try {
            connection.connect();
            try (PreparedStatement deleteChampionStatement = this.connection.getConnection().prepareStatement(DELETECHAMPIONDATASQLSTATEMENT)) {
                deleteChampionStatement.execute();
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