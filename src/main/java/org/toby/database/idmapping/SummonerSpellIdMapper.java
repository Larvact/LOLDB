package org.toby.database.idmapping;

import org.toby.valueobject.jsondeserialise.SummonerSpell;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SummonerSpellIdMapper implements IdMapper<Integer> {

    private SummonerSpellCollection summonerSpellCollection;
    private Map<Integer, Integer> summonerSpellIdMap;


    public SummonerSpellIdMapper(SummonerSpellCollection summonerSpellCollection) {
        this.summonerSpellCollection = summonerSpellCollection;
        this.summonerSpellIdMap = new HashMap<>();

    }
    @Override
    public void map() {
        Collections.sort(this.summonerSpellCollection.getSummonerSpells());
        int newIndex = 1;
        for(SummonerSpell summonerSpell : summonerSpellCollection.getSummonerSpells()){
            this.summonerSpellIdMap.put(summonerSpell.getId(), newIndex);
            newIndex++;
        }
    }

    @Override
    public Map<Integer, Integer> getMapping(){
        return summonerSpellIdMap;
    }
}