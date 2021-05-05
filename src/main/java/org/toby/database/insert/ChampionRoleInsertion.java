package org.toby.database.insert;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.jsondeserialise.Champion;
import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ChampionRoleInsertion extends Insertion{

    private Map<String, Integer> championIdMap;
    private Map<String, Integer> roleIdMap;
    private final ChampionCollection championCollection;

    public ChampionRoleInsertion(LolDbConnector connection, ChampionCollection championCollection) {
        super(connection);
        this.championCollection = championCollection;
        this.setChampionIdMap();
        this.setRoleIdMap();
    }

    @Override
    public void insertData() {
        try {
            connection.connect();
            try (PreparedStatement insertChampionRoleStatement = this.connection.getConnection().prepareStatement(constructSqlInsertStatement())) {
                insertChampionRoleStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                this.connection.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void setChampionIdMap() {
        this.championIdMap = new HashMap<>();
        this.connection.connect();
        try(PreparedStatement getChampionStatement = this.connection.getConnection().prepareStatement("SELECT c.Id, c.Name FROM [dbo].[Champion] c;")){
            ResultSet championIdResults = getChampionStatement.executeQuery();
            while(championIdResults.next()){
                String championName = championIdResults.getString(2);
                int championId = championIdResults.getInt(1);
                this.championIdMap.put(championName, championId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                this.connection.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void setRoleIdMap() {
        this.roleIdMap = new HashMap<>();
        this.connection.connect();
        try(PreparedStatement getRoleStatement = this.connection.getConnection().prepareStatement("SELECT r.Id, r.Name FROM [dbo].[Role] r;")){
            ResultSet roleIdResults = getRoleStatement.executeQuery();
            while(roleIdResults.next()){
                String roleName = roleIdResults.getString(2);
                int roleId = roleIdResults.getInt(1);
                this.roleIdMap.put(roleName, roleId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                this.connection.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private String constructSqlInsertStatement(){
        StringBuilder sqlInsertStatement = new StringBuilder("INSERT INTO [dbo].[ChampionRole] (ChampionId, RoleId) VALUES ");
        for(Champion champion : championCollection.getChampions()){
            List<String> championRoles = champion.getRoles();
            for(String role : championRoles){
                sqlInsertStatement.append("('").append(this.championIdMap.get(champion.getName())).append("', '").append(this.roleIdMap.get(role)).append("'), ");
            }
        }
        return sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
    }
}
