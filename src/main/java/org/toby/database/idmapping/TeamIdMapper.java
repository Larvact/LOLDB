package org.toby.database.idmapping;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TeamIdMapper implements IdMapper<Byte>{

    private Map<String, Byte> legacyTeamMap;
    private LolDbConnector connector;
    private Map<Byte, Byte> seasonIdMap;

    public TeamIdMapper(LolDbConnector connector) {
        setLegacyTeamMap();
        this.connector = connector;
        this.seasonIdMap = new HashMap<>();
    }

    @Override
    public void map() {
        try{
            ResultSet teamData = selectTeamTableData();
            while (teamData.next()){
                for(String team : this.legacyTeamMap.keySet()){
                    if(team.equals(teamData.getString("Name"))){
                        this.seasonIdMap.put(this.legacyTeamMap.get(team), Byte.parseByte(teamData.getString("Id")));
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

    private ResultSet selectTeamTableData() throws SQLException {
        this.connector.connect();
        PreparedStatement selectSeasonStatement = this.connector.getConnection().prepareStatement("SELECT t.Id, t.Name FROM [dbo].[Team] t");
        return selectSeasonStatement.executeQuery();
    }

    private void setLegacyTeamMap(){
        this.legacyTeamMap = new HashMap<>();
        this.legacyTeamMap.put("Neither", (byte) 0);
        this.legacyTeamMap.put("Blue", (byte) 1);
        this.legacyTeamMap.put("Purple", (byte) 2);
    }

    @Override
    public Map<Byte, Byte> getMapping() {
        return this.seasonIdMap;
    }
}
