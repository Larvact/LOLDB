package org.toby.valueobject.csvobjects.databasetransfer;

import org.toby.valueobject.csvobjects.GameDetail;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GameMilestone {

    private long gameId;
    private byte firstBlood;
    private byte firstTower;
    private byte firstInhibitor;
    private byte firstBaron;
    private byte firstDragon;
    private byte firstRiftHerald;

    public GameMilestone(GameDetail gameDetail) {
        this.gameId = gameDetail.getNewId();
        this.firstBlood = gameDetail.getFirstBlood();
        this.firstTower = gameDetail.getFirstTower();
        this.firstInhibitor = gameDetail.getFirstInhibitor();
        this.firstBaron = gameDetail.getFirstBaron();
        this.firstDragon = gameDetail.getFirstDragon();
        this.firstRiftHerald = gameDetail.getFirstRiftHerald();
    }
}
