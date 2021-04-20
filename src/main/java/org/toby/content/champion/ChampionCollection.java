package org.toby.content.champion;

import org.toby.lolobject.Champion;
import java.util.*;

public class ChampionCollection {

    private String type;
    private double version;
    private List<Champion> champions;

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

    public List<Champion> getChampions() {
        return champions;
    }

    public void setChampions(List<Champion> champions) {
        this.champions = champions;
    }
}
