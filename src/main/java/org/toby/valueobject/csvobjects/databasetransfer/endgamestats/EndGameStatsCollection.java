package org.toby.valueobject.csvobjects.databasetransfer.endgamestats;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.List;

public class EndGameStatsCollection {

    private List<EndGameStats> endGameStats;

    public EndGameStatsCollection(List<GameDetail> gameDetails) {
        for(GameDetail gameDetail : gameDetails){
            EndGameStats blueEndGameStats = new BlueEndGameStats(gameDetail);
            EndGameStats purpleEndGameStats = new PurpleEndGameStats(gameDetail);
            endGameStats.add(blueEndGameStats);
            endGameStats.add(purpleEndGameStats);
        }
    }

    public List<EndGameStats> getEndGameStats() {
        return endGameStats;
    }
}
