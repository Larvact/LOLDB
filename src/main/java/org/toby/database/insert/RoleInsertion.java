package org.toby.database.insert;

import org.toby.content.champion.ChampionCollection;
import org.toby.content.role.RoleCollection;
import org.toby.database.LolDbConnector;
import org.toby.lolobject.Champion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleInsertion extends Insertion{

    private RoleCollection roleCollection;

    public RoleInsertion(LolDbConnector connection, ChampionCollection championCollection) {
        super(connection);
        this.roleCollection = new RoleCollection(championCollection);
    }

    @Override
    public void insertData() {
        try {
            connection.connect();
            PreparedStatement insertRoleStatement = this.connection.getConnection().prepareStatement(constructSQLInsertStatement());
            insertRoleStatement.execute();
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

    private String constructSQLInsertStatement(){
        String sqlInsertStatement = "INSERT INTO dbo.Role (Name) VALUES ";
        String valueString = "";
        for(String role : roleCollection.getRoles()){
            valueString = "(\'" + role + "\'), ";
            sqlInsertStatement += valueString;
        }
        sqlInsertStatement = sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
        System.out.println(sqlInsertStatement);
        return sqlInsertStatement;
    }
}
