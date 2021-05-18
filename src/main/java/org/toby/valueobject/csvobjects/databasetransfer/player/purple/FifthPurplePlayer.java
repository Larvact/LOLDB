package org.toby.valueobject.csvobjects.databasetransfer.player.purple;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class FifthPurplePlayer extends Player {

    public FifthPurplePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 2);
        this.setPositionId((byte) 1);
        this.setChampionId(gameDetail.getT2_champ5id());
        this.setBannedChampionId(gameDetail.getT2_ban5());
    }
}
