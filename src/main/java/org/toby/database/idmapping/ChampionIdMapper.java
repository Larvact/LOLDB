package org.toby.database.idmapping;


import org.toby.database.LolDbConnector;
import org.toby.valueobject.jsondeserialise.SummonerSpell;
import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.valueobject.jsondeserialise.Champion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ChampionIdMapper implements IdMapper<Integer> {

    private ChampionCollection championCollection;
    private LolDbConnector connector;
    private Map<Integer, Integer> championIdMap;

    public ChampionIdMapper(ChampionCollection championCollection, LolDbConnector connector) {
        this.championCollection = championCollection;
        this.connector = connector;
        this.championIdMap = new HashMap<>();

    }
    @Override
    public void map() {
        ResultSet championData = selectChampionTableData();
        try{
            while (championData.next()){
                for(Champion champion : championCollection.getChampions()){
                    if(champion.getName().equals(championData.getString("Name"))){
                        this.championIdMap.put(champion.getId(), championData.getInt("Id"));
                        break;
                    }
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            this.connector.closeConnection();
        }
    }

    private ResultSet selectChampionTableData(){
        this.connector.connect();
        try{
            PreparedStatement selectChampionStatement = this.connector.getConnection().prepareStatement("SELECT c.Id, c.Name FROM [dbo].[Champion] c");
            return selectChampionStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NullPointerException("Unable to retrieve a result set from the select champion SQL query - ChampionIdMapper: selectChampionTableData");
    }

    @Override
    public Map<Integer, Integer> getMapping(){
        return championIdMap;
    }
}
