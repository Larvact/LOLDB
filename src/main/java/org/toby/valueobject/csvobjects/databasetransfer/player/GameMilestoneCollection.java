package org.toby.valueobject.csvobjects.databasetransfer.player;

import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.Game;
import org.toby.valueobject.csvobjects.databasetransfer.GameMilestone;

import java.util.List;

public class GameMilestoneCollection {

    private List<GameMilestone> gameMilestones;

    public GameMilestoneCollection(List<GameDetail> gameDetails) {
        for(GameDetail gameDetail : gameDetails){
            GameMilestone gameMilestone = new GameMilestone(gameDetail);
            gameMilestones.add(gameMilestone);
        }
    }

    public List<GameMilestone> getGames() {
        return gameMilestones;
    }
}
