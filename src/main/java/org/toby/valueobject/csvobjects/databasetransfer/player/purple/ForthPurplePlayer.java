package org.toby.valueobject.csvobjects.databasetransfer.player.purple;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class ForthPurplePlayer extends Player {

    public ForthPurplePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 2);
        this.setPositionId((byte) 1);
        this.setChampionId(gameDetail.getT2_champ4id());
        this.setBannedChampionId(gameDetail.getT2_ban4());
    }
}
