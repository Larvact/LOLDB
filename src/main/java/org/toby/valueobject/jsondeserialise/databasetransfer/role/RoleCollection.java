package org.toby.valueobject.jsondeserialise.databasetransfer.role;

import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.valueobject.jsondeserialise.Champion;

import java.util.Set;
import java.util.TreeSet;

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
        roles.remove("");
    }

    public Set<String> getRoles() {
        return roles;
    }
}
