package org.toby.valueobject.csvobjects.databasetransfer.player.blue;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class SecondBluePlayer extends Player {

    public SecondBluePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 1);
        this.setPositionId((byte) 2);
        this.setChampionId(gameDetail.getT1_champ2id());
        this.setBannedChampionId(gameDetail.getT1_ban2());
    }
}
