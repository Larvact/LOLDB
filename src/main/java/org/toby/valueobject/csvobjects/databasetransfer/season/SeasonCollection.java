package org.toby.valueobject.csvobjects.databasetransfer.season;

import org.toby.valueobject.csvobjects.GameDetail;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SeasonCollection {

    private Set<Byte> seasonSet;

    public SeasonCollection(Collection<GameDetail> gameDetails) {
        this.setSeasonSet(gameDetails);
    }

    public Set<Byte> getSeasonSet() {
        return seasonSet;
    }

    private void setSeasonSet(Collection<GameDetail> gameDetails){
        seasonSet = new HashSet<>();
        for(GameDetail gameDetail : gameDetails){
            seasonSet.add(gameDetail.getSeasonId());
        }
    }
}
