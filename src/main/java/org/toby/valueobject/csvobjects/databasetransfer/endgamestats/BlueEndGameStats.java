package org.toby.valueobject.csvobjects.databasetransfer.endgamestats;

import org.toby.valueobject.csvobjects.GameDetail;

public class BlueEndGameStats extends EndGameStats {

    public BlueEndGameStats(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 1);
        this.setTowerKills(gameDetail.getT1_towerKills());
        this.setInhibitorKills(gameDetail.getT1_inhibitorKills());
        this.setBaronKills(gameDetail.getT1_baronKills());
        this.setDragonKills(gameDetail.getT1_dragonKills());
        this.setRiftHeraldKills(gameDetail.getT1_riftHeraldKills());
    }
}
