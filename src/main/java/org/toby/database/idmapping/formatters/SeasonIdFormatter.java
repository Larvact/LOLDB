package org.toby.database.idmapping.formatters;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.Map;

public class SeasonIdFormatter implements Format{

    private Map<Integer, GameDetail> gameDetails;
    private Map<Byte, Byte> seasonIdMap;

    public SeasonIdFormatter(Map<Integer, GameDetail> gameDetails, Map<Byte, Byte> seasonIdMap) {
        this.gameDetails = gameDetails;
        this.seasonIdMap = seasonIdMap;
    }

    @Override
    public void format() {
        for (GameDetail gameDetail : this.gameDetails.values()) {
            gameDetail.setSeasonId(this.seasonIdMap.get(gameDetail.getSeasonId()));
        }
    }
}
