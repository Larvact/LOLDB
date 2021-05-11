package org.toby.database.insert;

import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.valueobject.jsondeserialise.databasetransfer.role.RoleCollection;
import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleInsertion extends Insertion{

    private RoleCollection roleCollection;

    public RoleInsertion(LolDbConnector connector, ChampionCollection championCollection) {
        super(connector);
        this.roleCollection = new RoleCollection(championCollection);
    }

    @Override
    public void insertData() {
        try {
            connector.connect();
            try (PreparedStatement insertRoleStatement = this.connector.getConnection().prepareStatement(constructSQLInsertStatement())) {
                insertRoleStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                this.connector.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String constructSQLInsertStatement(){
        StringBuilder sqlInsertStatement = new StringBuilder("INSERT INTO dbo.Role (Name) VALUES ");
        for(String role : roleCollection.getRoles()){
            sqlInsertStatement.append("('").append(role).append("'), ");
        }
        return sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
    }
}
