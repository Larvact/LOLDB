package org.toby.database.idmapping;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.csvobjects.databasetransfer.season.SeasonCollection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SeasonIdMapper implements IdMapper<Byte>{

    private SeasonCollection seasonCollection;
    private LolDbConnector connector;
    private Map<Byte, Byte> seasonIdMap;

    public SeasonIdMapper(SeasonCollection seasonCollection, LolDbConnector connector) {
        this.seasonCollection = seasonCollection;
        this.connector = connector;
        this.seasonIdMap = new HashMap<>();
    }

    @Override
    public void map() {
        try{
            ResultSet seasonData = selectSeasonTableData();
            while (seasonData.next()){
                for(byte season : this.seasonCollection.getSeasonSet()){
                    if(season == Byte.parseByte(seasonData.getString("Name"))){
                        this.seasonIdMap.put(season, Byte.parseByte(seasonData.getString("Id")));
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

    private ResultSet selectSeasonTableData() throws SQLException {
        this.connector.connect();
        PreparedStatement selectSeasonStatement = this.connector.getConnection().prepareStatement("SELECT s.Id, s.Name FROM [dbo].[Season] s");
        return selectSeasonStatement.executeQuery();
    }

    @Override
    public Map<Byte, Byte> getMapping() {
        return this.seasonIdMap;
    }
}
