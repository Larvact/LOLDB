package org.toby.database.idmapping.formatters;

import java.util.List;
import java.util.Map;

import org.toby.valueobject.csvobjects.GameDetail;

public class ChampionIdFormatter implements Format{

    private List<GameDetail> gameDetails;
    private Map<Integer, Integer> championIdMap;

    public ChampionIdFormatter(List<GameDetail> gameDetails, Map<Integer, Integer> championIdMap) {
        this.gameDetails = gameDetails;
        this.championIdMap = championIdMap;
    }

    @Override
    public void format() {
        for(GameDetail gameDetail : this.gameDetails){
            gameDetail.setT1_champ1id(this.championIdMap.get(gameDetail.getT1_champ1id()));
            gameDetail.setT1_champ2id(this.championIdMap.get(gameDetail.getT1_champ2id()));
            gameDetail.setT1_champ3id(this.championIdMap.get(gameDetail.getT1_champ3id()));
            gameDetail.setT1_champ4id(this.championIdMap.get(gameDetail.getT1_champ4id()));
            gameDetail.setT1_champ5id(this.championIdMap.get(gameDetail.getT1_champ5id()));

            gameDetail.setT1_ban1(this.championIdMap.get(gameDetail.getT1_ban1()));
            gameDetail.setT1_ban2(this.championIdMap.get(gameDetail.getT1_ban2()));
            gameDetail.setT1_ban3(this.championIdMap.get(gameDetail.getT1_ban3()));
            gameDetail.setT1_ban4(this.championIdMap.get(gameDetail.getT1_ban4()));
            gameDetail.setT1_ban5(this.championIdMap.get(gameDetail.getT1_ban5()));

            gameDetail.setT2_champ1id(this.championIdMap.get(gameDetail.getT2_champ1id()));
            gameDetail.setT2_champ2id(this.championIdMap.get(gameDetail.getT2_champ2id()));
            gameDetail.setT2_champ3id(this.championIdMap.get(gameDetail.getT2_champ3id()));
            gameDetail.setT2_champ4id(this.championIdMap.get(gameDetail.getT2_champ4id()));
            gameDetail.setT2_champ5id(this.championIdMap.get(gameDetail.getT2_champ5id()));

            gameDetail.setT2_ban1(this.championIdMap.get(gameDetail.getT2_ban1()));
            gameDetail.setT2_ban2(this.championIdMap.get(gameDetail.getT2_ban2()));
            gameDetail.setT2_ban3(this.championIdMap.get(gameDetail.getT2_ban3()));
            gameDetail.setT2_ban4(this.championIdMap.get(gameDetail.getT2_ban4()));
            gameDetail.setT2_ban5(this.championIdMap.get(gameDetail.getT2_ban5()));

        }
    }
}
