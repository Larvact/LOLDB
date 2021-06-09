package org.toby.database.insert;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.csvobjects.databasetransfer.season.SeasonCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeasonInsertion extends Insertion{

    private SeasonCollection seasonCollection;

    public SeasonInsertion(LolDbConnector connector, SeasonCollection seasons) {
        super(connector);
        this.seasonCollection = seasons;
    }

    @Override
    public void insertData() {
        this.connector.connect();
        try(PreparedStatement seasonInsertStatement = this.connector.getConnection().prepareStatement(constructSQLInsertStatement())){
            seasonInsertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            this.connector.closeConnection();
        }
    }

    private String constructSQLInsertStatement() {
        StringBuilder seasonInsertStatement = new StringBuilder();
        seasonInsertStatement.append("INSERT INTO [dbo].[Season] VALUES ");
        for(byte season : this.seasonCollection.getSeasonSet()){
            seasonInsertStatement.append("(").append(season).append("), ");
        }
        return seasonInsertStatement.substring(0, seasonInsertStatement.length() - 2) + ";";
    }
}
