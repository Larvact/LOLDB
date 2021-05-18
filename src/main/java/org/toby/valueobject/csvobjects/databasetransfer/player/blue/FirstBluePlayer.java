package org.toby.valueobject.csvobjects.databasetransfer.player.blue;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class FirstBluePlayer extends Player {

    public FirstBluePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 1);
        this.setPositionId((byte) 1);
        this.setChampionId(gameDetail.getT1_champ1id());
        this.setBannedChampionId(gameDetail.getT1_ban1());
    }
}
