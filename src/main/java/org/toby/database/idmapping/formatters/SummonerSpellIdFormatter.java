package org.toby.database.idmapping.formatters;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.List;
import java.util.Map;

public class SummonerSpellIdFormatter implements Format{


    private List<GameDetail> gameDetails;
    private Map<Integer, Integer> summonerSpellIdMap;

    public SummonerSpellIdFormatter(List<GameDetail> gameDetails, Map<Integer, Integer> summonerSpellIdMap) {
        this.gameDetails = gameDetails;
        this.summonerSpellIdMap = summonerSpellIdMap;
    }

    @Override
    public void format() {
        for(GameDetail gameDetail : this.gameDetails){
            gameDetail.setT1_champ1_sum1(this.summonerSpellIdMap.get(gameDetail.getT1_champ1_sum1()));
            gameDetail.setT1_champ1_sum2(this.summonerSpellIdMap.get(gameDetail.getT1_champ1_sum2()));
            gameDetail.setT1_champ2_sum1(this.summonerSpellIdMap.get(gameDetail.getT1_champ2_sum1()));
            gameDetail.setT1_champ2_sum2(this.summonerSpellIdMap.get(gameDetail.getT1_champ2_sum2()));
            gameDetail.setT1_champ3_sum1(this.summonerSpellIdMap.get(gameDetail.getT1_champ3_sum1()));
            gameDetail.setT1_champ3_sum2(this.summonerSpellIdMap.get(gameDetail.getT1_champ3_sum2()));
            gameDetail.setT1_champ4_sum1(this.summonerSpellIdMap.get(gameDetail.getT1_champ4_sum1()));
            gameDetail.setT1_champ4_sum2(this.summonerSpellIdMap.get(gameDetail.getT1_champ4_sum2()));
            gameDetail.setT1_champ5_sum1(this.summonerSpellIdMap.get(gameDetail.getT1_champ5_sum1()));
            gameDetail.setT1_champ5_sum2(this.summonerSpellIdMap.get(gameDetail.getT1_champ5_sum2()));

            gameDetail.setT2_champ1_sum1(this.summonerSpellIdMap.get(gameDetail.getT2_champ1_sum1()));
            gameDetail.setT2_champ1_sum2(this.summonerSpellIdMap.get(gameDetail.getT2_champ1_sum2()));
            gameDetail.setT2_champ2_sum1(this.summonerSpellIdMap.get(gameDetail.getT2_champ2_sum1()));
            gameDetail.setT2_champ2_sum2(this.summonerSpellIdMap.get(gameDetail.getT2_champ2_sum2()));
            gameDetail.setT2_champ3_sum1(this.summonerSpellIdMap.get(gameDetail.getT2_champ3_sum1()));
            gameDetail.setT2_champ3_sum2(this.summonerSpellIdMap.get(gameDetail.getT2_champ3_sum2()));
            gameDetail.setT2_champ4_sum1(this.summonerSpellIdMap.get(gameDetail.getT2_champ4_sum1()));
            gameDetail.setT2_champ4_sum2(this.summonerSpellIdMap.get(gameDetail.getT2_champ4_sum2()));
            gameDetail.setT2_champ5_sum1(this.summonerSpellIdMap.get(gameDetail.getT2_champ5_sum1()));
            gameDetail.setT2_champ5_sum2(this.summonerSpellIdMap.get(gameDetail.getT2_champ5_sum2()));
        }
    }
}
