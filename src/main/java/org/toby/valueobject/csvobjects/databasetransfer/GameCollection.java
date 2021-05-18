package org.toby.valueobject.csvobjects.databasetransfer;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.List;

public class GameCollection {

    private List<Game> games;

    public GameCollection(List<GameDetail> gameDetails) {
        for(GameDetail gameDetail : gameDetails){
            Game game = new Game(gameDetail);
            games.add(game);
        }
    }

    public List<Game> getGames() {
        return games;
    }
}
