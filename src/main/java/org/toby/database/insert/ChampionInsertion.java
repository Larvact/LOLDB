package org.toby.database.insert;

import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.database.LolDbConnector;
import org.toby.valueobject.jsondeserialise.Champion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

public class ChampionInsertion extends Insertion{

    private final ChampionCollection championCollection;

    public ChampionInsertion(LolDbConnector connection, ChampionCollection championCollection) {
        super(connection);
        this.championCollection = championCollection;
        Collections.sort(championCollection.getChampions());
    }

    @Override
    public void insertData() {
        try {
            connection.connect();
            try (PreparedStatement insertChampionStatement = this.connection.getConnection().prepareStatement(constructSQLInsertStatement())) {
                insertChampionStatement.execute();
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

    private String constructSQLInsertStatement(){
        StringBuilder sqlInsertStatement = new StringBuilder("INSERT INTO dbo.Champion (Name, Title) VALUES ");
        for(Champion champion : championCollection.getChampions()){
            sqlInsertStatement.append("('").append(champion.getName()).append("', '").append(champion.getTitle()).append("'), ");
        }
        return sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
    }
}