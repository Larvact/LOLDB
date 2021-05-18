package org.toby.valueobject.csvobjects.databasetransfer.player.blue;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class FifthBluePlayer extends Player {

    public FifthBluePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 1);
        this.setPositionId((byte) 5);
        this.setChampionId(gameDetail.getT1_champ5id());
        this.setBannedChampionId(gameDetail.getT1_ban5());
    }
}
