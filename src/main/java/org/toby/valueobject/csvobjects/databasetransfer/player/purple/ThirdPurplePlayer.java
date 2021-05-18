package org.toby.valueobject.csvobjects.databasetransfer.player.purple;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class ThirdPurplePlayer extends Player {

    public ThirdPurplePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 2);
        this.setPositionId((byte) 1);
        this.setChampionId(gameDetail.getT2_champ3id());
        this.setBannedChampionId(gameDetail.getT2_ban3());
    }
}
