package org.toby.database.insert;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.csvobjects.databasetransfer.game.Game;
import org.toby.valueobject.csvobjects.databasetransfer.game.GameCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameInsertion extends Insertion{

    private GameCollection gameCollection;

    public GameInsertion(LolDbConnector connector, GameCollection gameCollection) {
        super(connector);
        this.gameCollection = gameCollection;
    }

    @Override
    public void insertData() {
        connector.connect();
        try(PreparedStatement gameInsertStatement = this.connector.getConnection().prepareStatement(constructSQLInsertStatement())){
            gameInsertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
    }

    private String constructSQLInsertStatement() {
        StringBuilder sqlInsertStatement = new StringBuilder("INSERT INTO [dbo].[Game] VALUES ");
        for(Game game : this.gameCollection.getGames()){
            sqlInsertStatement.append("(")
                    .append(game.getGameDuration()).append(", ")
                    .append(game.getGameDuration()).append(", ")
                    .append(game.getWinner()).append(", ")
                    .append(game.getLegacyId()).append(", ")
                    .append(game.getSeasonId()).append("), ");
        }
        return sqlInsertStatement.substring(0,sqlInsertStatement.length() - 2) + ";";
    }
}
