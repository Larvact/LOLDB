package org.toby.valueobject.jsondeserialise;

public class SummonerSpell implements Comparable<SummonerSpell>{

    private long id;
    private String name;
    private String description;
    private short levelRequired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(short levelRequired) {
        this.levelRequired = levelRequired;
    }

    @Override
    public int compareTo(SummonerSpell o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SummonerSpell)){
            return false;
        }
        SummonerSpell summonerSpell = (SummonerSpell) obj;
        return this.name.equals(summonerSpell.getName());
    }
}
