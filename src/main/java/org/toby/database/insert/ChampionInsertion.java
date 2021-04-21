package org.toby.database.insert;

import org.toby.content.champion.ChampionCollection;
import org.toby.database.LolDbConnector;
import org.toby.lolobject.Champion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

public class ChampionInsertion extends Insertion{

    private ChampionCollection championCollection;

    public ChampionInsertion(LolDbConnector connection, ChampionCollection championCollection) {
        super(connection);
        this.championCollection = championCollection;
        Collections.sort(championCollection.getChampions());
    }

    @Override
    public void insertData() {
        try {
            connection.connect();
            PreparedStatement insertChampionStatement = this.connection.getConnection().prepareStatement(constructSQLInsertStatement());
            insertChampionStatement.execute();
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
        String sqlInsertStatement = "INSERT INTO dbo.Champion (Name, Title) VALUES ";
        String valueString = "";
        for(Champion champion : championCollection.getChampions()){
            valueString = "(\'" + champion.getName() + "\', \'" + champion.getTitle() + "\'), ";
            sqlInsertStatement += valueString;
        }
        sqlInsertStatement = sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
        System.out.println(sqlInsertStatement);
        return sqlInsertStatement;
    }
}