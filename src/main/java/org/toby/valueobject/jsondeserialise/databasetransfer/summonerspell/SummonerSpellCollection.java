package org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell;

import org.toby.valueobject.jsondeserialise.SummonerSpell;

import java.util.List;

public class SummonerSpellCollection {

    private String type;
    private double version;
    private List<SummonerSpell> summonerSpells;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public List<SummonerSpell> getSummonerSpells() {
        return summonerSpells;
    }

    public void setSummonerSpells(List<SummonerSpell> summonerSpells) {
        this.summonerSpells = summonerSpells;
    }
}
