package org.toby.valueobject.csvobjects.databasetransfer.player.blue;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.player.Player;

public class ForthBluePlayer extends Player {

    public ForthBluePlayer(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 1);
        this.setPositionId((byte) 4);
        this.setChampionId(gameDetail.getT1_champ4id());
        this.setBannedChampionId(gameDetail.getT1_ban4());
    }
}
