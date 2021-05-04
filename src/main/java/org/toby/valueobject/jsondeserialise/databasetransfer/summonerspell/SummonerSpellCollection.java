package org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell;

import org.toby.valueobject.jsondeserialise.SummonerSpell;

import java.util.Set;

public class SummonerSpellCollection {

    private String type;
    private double version;
    private Set<SummonerSpell> summonerSpells;

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

    public Set<SummonerSpell> getSummonerSpells() {
        return summonerSpells;
    }

    public void setSummonerSpells(Set<SummonerSpell> summonerSpells) {
        this.summonerSpells = summonerSpells;
    }
}
