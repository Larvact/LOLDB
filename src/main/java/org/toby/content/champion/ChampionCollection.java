package org.toby.content.champion;

import org.toby.lolobject.Champion;
import java.util.Set;

public class ChampionCollection {

    private String type;
    private double version;
    private Set<Champion> champions;

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

    public Set<Champion> getChampions() {
        return champions;
    }

    public void setChampions(Set<Champion> champions) {
        this.champions = champions;
    }
}
