package org.toby.database.idmapping;


import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.valueobject.jsondeserialise.Champion;

import java.util.*;

public class ChampionIdMapper implements IdMapper<Integer> {

    private ChampionCollection championCollection;
    private Map<Integer, Integer> championIdMap;


    public ChampionIdMapper(ChampionCollection championCollection) {
        this.championCollection = championCollection;
        this.championIdMap = new HashMap<>();

    }
    @Override
    public void map() {
        Collections.sort(this.championCollection.getChampions());
        int newIndex = 1;
        for(Champion champion : championCollection.getChampions()){
            this.championIdMap.put(champion.getId(), newIndex);
            newIndex++;
        }
    }

    @Override
    public Map<Integer, Integer> getMapping(){
        return championIdMap;
    }
}
