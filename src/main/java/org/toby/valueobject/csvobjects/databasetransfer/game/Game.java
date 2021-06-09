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

    public Game(GameDetail gameDetail) {
        this.creationTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(gameDetail.getCreationTime()), ZoneId.systemDefault());
        this.gameDuration = gameDetail.getGameDuration();
        this.seasonId = gameDetail.getSeasonId();
        this.winner = gameDetail.getWinner();
    }
}
