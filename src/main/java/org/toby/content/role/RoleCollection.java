package org.toby.content.role;

import org.toby.content.champion.ChampionCollection;
import org.toby.lolobject.Champion;

import java.util.*;

public class RoleCollection {

    private Set<String> roles;

    public RoleCollection(ChampionCollection championCollection) {
        setRoles(championCollection);
    }

    private void setRoles(ChampionCollection championCollection){
        roles = new TreeSet<>();
        for(Champion champion : championCollection.getChampions()){
            roles.addAll(champion.getRoles());
        }
    }

    public Set<String> getRoles() {
        return roles;
    }
}
