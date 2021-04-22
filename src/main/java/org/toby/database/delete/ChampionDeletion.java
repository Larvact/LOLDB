package org.toby.database.delete;

import org.toby.content.champion.ChampionCollection;
import org.toby.database.LolDbConnector;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ChampionDeletion extends Deletion{

    private ChampionCollection championCollection;

    public ChampionDeletion(LolDbConnector connection) {
        super(connection);
    }

    @Override
    public void delete() {
        try {
            connection.connect();
            PreparedStatement deleteChampionStatement = this.connection.getConnection().prepareStatement(constructSQLDeleteStatement());
            deleteChampionStatement.execute();
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

    private String constructSQLDeleteStatement(){
        String sqlDeleteStatement = "DELETE FROM dbo.Champion; DBCC CHECKIDENT ('Champion', RESEED, 0);";
        return sqlDeleteStatement;
    }

}
