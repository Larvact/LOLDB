package org.toby.database.idmapping.formatters;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.Map;

public class TeamIdFormatter implements Format{

    private Map<Integer, GameDetail> gameDetails;
    private Map<Byte, Byte> teamIdMap;

    public TeamIdFormatter(Map<Integer, GameDetail> gameDetails, Map<Byte, Byte> teamIdMap) {
        this.gameDetails = gameDetails;
        this.teamIdMap = teamIdMap;
    }

    @Override
    public void format() {
        for(GameDetail gameDetail : this.gameDetails.values()){
            gameDetail.setWinner(this.teamIdMap.get(gameDetail.getWinner()));
            gameDetail.setFirstBlood(this.teamIdMap.get(gameDetail.getFirstBlood()));
            gameDetail.setFirstTower(this.teamIdMap.get(gameDetail.getFirstTower()));
            gameDetail.setFirstInhibitor(this.teamIdMap.get(gameDetail.getFirstInhibitor()));
            gameDetail.setFirstBaron(this.teamIdMap.get(gameDetail.getFirstBaron()));
            gameDetail.setFirstDragon(this.teamIdMap.get(gameDetail.getFirstDragon()));
            gameDetail.setFirstRiftHerald(this.teamIdMap.get(gameDetail.getFirstRiftHerald()));
        }
    }
}
