package org.toby.valueobject.csvobjects.databasetransfer.game;

import org.toby.valueobject.csvobjects.GameDetail;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Game {

    private String creationTime;
    private short gameDuration;
    private int seasonId;
    private byte winner;
    private long legacyId;

    public Game(GameDetail gameDetail) {
        this.creationTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.ofInstant(Instant.ofEpochMilli(gameDetail.getCreationTime()), ZoneId.systemDefault()));
        this.gameDuration = gameDetail.getGameDuration();
        this.seasonId = gameDetail.getSeasonId();
        this.winner = gameDetail.getWinner();
        this.legacyId = gameDetail.getOldId();
    }

    public String getCreationTime() {
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
