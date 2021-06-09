package org.toby.database.idmapping;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.jsondeserialise.SummonerSpell;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SummonerSpellIdMapper implements IdMapper<Integer> {

    private SummonerSpellCollection summonerSpellCollection;
    private LolDbConnector connector;
    private Map<Integer, Integer> summonerSpellIdMap;

    public SummonerSpellIdMapper(SummonerSpellCollection summonerSpellCollection, LolDbConnector connector) {
        this.summonerSpellCollection = summonerSpellCollection;
        this.connector = connector;
        this.summonerSpellIdMap = new HashMap<>();

    }
    @Override
    public void map() {
        ResultSet summonerSpellData = selectSummonerSpellTableData();
        try{
            while (summonerSpellData.next()){
                for(SummonerSpell summonerSpell : summonerSpellCollection.getSummonerSpells()){
                    if(summonerSpell.getName().equals(summonerSpellData.getString("Name"))){
                        this.summonerSpellIdMap.put(summonerSpell.getId(), summonerSpellData.getInt("Id"));
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

    private ResultSet selectSummonerSpellTableData(){
        this.connector.connect();
        try{
            PreparedStatement selectSummonerSpellStatement = this.connector.getConnection().prepareStatement("SELECT ss.Id, ss.Name FROM [dbo].[SummonerSpell] ss");
            return selectSummonerSpellStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NullPointerException("Unable to retrieve a result set from the select summoner spells SQL query - SummonerSpellIdMapper: selectSummonerSpellTableData");
    }

    @Override
    public Map<Integer, Integer> getMapping(){
        return summonerSpellIdMap;
    }
}