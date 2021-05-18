package org.toby.valueobject.csvobjects.databasetransfer.player;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.blue.*;
import org.toby.valueobject.csvobjects.databasetransfer.player.purple.*;

import java.util.List;

public class PlayerCollection {

    private List<Player> players;

    public PlayerCollection(List<GameDetail> gameDetails) {
        for(GameDetail gameDetail : gameDetails){
            populatePlayersFromGame(gameDetail);
        }
    }

    private void populatePlayersFromGame(GameDetail gameDetail){
        Player firstBluePlayer = new FirstBluePlayer(gameDetail);
        Player secondBluePlayer = new SecondBluePlayer(gameDetail);
        Player thirdBluePlayer = new ThirdBluePlayer(gameDetail);
        Player forthBluePlayer = new ForthBluePlayer(gameDetail);
        Player fifthBluePlayer = new FifthBluePlayer(gameDetail);
        Player firstPurplePlayer = new FirstPurplePlayer(gameDetail);
        Player secondPurplePlayer = new SecondPurplePlayer(gameDetail);
        Player thirdPurplePlayer = new ThirdPurplePlayer(gameDetail);
        Player forthPurplePlayer = new ForthPurplePlayer(gameDetail);
        Player fifthPurplePlayer = new FifthPurplePlayer(gameDetail);

        this.players.add(firstBluePlayer);
        this.players.add(secondBluePlayer);
        this.players.add(thirdBluePlayer);
        this.players.add(forthBluePlayer);
        this.players.add(fifthBluePlayer);
        this.players.add(firstPurplePlayer);
        this.players.add(secondPurplePlayer);
        this.players.add(thirdPurplePlayer);
        this.players.add(forthPurplePlayer);
        this.players.add(fifthPurplePlayer);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
