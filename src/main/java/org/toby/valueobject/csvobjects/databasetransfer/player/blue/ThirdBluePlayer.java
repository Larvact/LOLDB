package org.toby.valueobject.csvobjects.databasetransfer.player.blue;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class ThirdBluePlayer extends Player {

    public ThirdBluePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 1);
        this.setPositionId((byte) 3);
        this.setChampionId(gameDetail.getT1_champ3id());
        this.setBannedChampionId(gameDetail.getT1_ban3());
    }
}
