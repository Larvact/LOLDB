package org.toby.valueobject.csvobjects.databasetransfer.game;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.ArrayList;
import java.util.List;

public class GameCollection {

    private List<Game> games;

    public GameCollection(List<GameDetail> gameDetails) {
        games = new ArrayList<>();
        for(GameDetail gameDetail : gameDetails){
            Game game = new Game(gameDetail);
            games.add(game);
        }
    }

    public List<Game> getGames() {
        return games;
    }
}
