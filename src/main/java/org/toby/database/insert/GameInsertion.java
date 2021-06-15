package org.toby.database.insert;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.csvobjects.databasetransfer.game.Game;
import org.toby.valueobject.csvobjects.databasetransfer.game.GameCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameInsertion extends Insertion{

    private GameCollection gameCollection;
    private int recordInsertCount;

    public GameInsertion(LolDbConnector connector, GameCollection gameCollection) {
        super(connector);
        this.gameCollection = gameCollection;
        this.recordInsertCount = 0;
    }

    @Override
    public void insertData() {
        while (this.recordInsertCount < this.gameCollection.getGames().size()) {
            connector.connect();
            try (PreparedStatement gameInsertStatement = this.connector.getConnection().prepareStatement(constructSQLInsertStatement())) {
                gameInsertStatement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                connector.closeConnection();
            }
            this.recordInsertCount = this.recordInsertCount + 1000;
        }
    }

    private String constructSQLInsertStatement() {
        StringBuilder sqlInsertStatement = new StringBuilder("INSERT INTO [dbo].[Game] VALUES ");
        for(int count = this.recordInsertCount; count < this.recordInsertCount + 1000 && count < this.gameCollection.getGames().size(); count++){
            Game game = this.gameCollection.getGames().get(count);
            sqlInsertStatement.append("('")
                    .append(game.getCreationTime()).append("', ")
                    .append(game.getGameDuration()).append(", ")
                    .append(game.getWinner()).append(", ")
                    .append(game.getLegacyId()).append(", ")
                    .append(game.getSeasonId()).append("), ");
        }
        return sqlInsertStatement.substring(0,sqlInsertStatement.length() - 2) + ";";
    }
}
