package org.toby.lolobject;

public class SummonerSpell {

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
}
