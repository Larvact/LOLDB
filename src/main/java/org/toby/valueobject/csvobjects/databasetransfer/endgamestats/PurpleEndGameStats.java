package org.toby.valueobject.csvobjects.databasetransfer.endgamestats;

import org.toby.valueobject.csvobjects.GameDetail;

public class PurpleEndGameStats extends EndGameStats{

    public PurpleEndGameStats(GameDetail gameDetail) {
        this.setGameId(gameDetail.getNewId());
        this.setTeamId((byte) 2);
        this.setTowerKills(gameDetail.getT2_towerKills());
        this.setInhibitorKills(gameDetail.getT2_inhibitorKills());
        this.setBaronKills(gameDetail.getT2_baronKills());
        this.setDragonKills(gameDetail.getT2_dragonKills());
        this.setRiftHeraldKills(gameDetail.getT2_riftHeraldKills());
    }
}
