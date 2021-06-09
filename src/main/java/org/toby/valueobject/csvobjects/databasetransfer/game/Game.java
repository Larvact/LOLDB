package org.toby.valueobject.csvobjects.databasetransfer.game;

import org.toby.valueobject.csvobjects.GameDetail;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Game {

    private LocalDateTime creationTime;
    private short gameDuration;
    private int seasonId;
    private byte winner;
    private long legacyId;

    public Game(GameDetail gameDetail) {
        this.creationTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(gameDetail.getCreationTime()), ZoneId.systemDefault());
        this.gameDuration = gameDetail.getGameDuration();
        this.seasonId = gameDetail.getSeasonId();
        this.winner = gameDetail.getWinner();
        this.legacyId = gameDetail.getOldId();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public short getGameDuration() {
        return gameDuration;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public byte getWinner() {
        return winner;
    }

    public long getLegacyId() {
        return legacyId;
    }
}
