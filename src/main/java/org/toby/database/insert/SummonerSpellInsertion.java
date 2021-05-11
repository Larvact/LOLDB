package org.toby.database.insert;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.jsondeserialise.SummonerSpell;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SummonerSpellInsertion extends Insertion{

    private final SummonerSpellCollection summonerSpellCollection;

    public SummonerSpellInsertion(LolDbConnector connection, SummonerSpellCollection summonerSpellCollection) {
        super(connection);
        this.summonerSpellCollection = summonerSpellCollection;
    }

    @Override
    public void insertData() {
        try {
            this.connection.connect();
            try (PreparedStatement sqlInsertStatement = this.connection.getConnection().prepareStatement(constructSQLInsertStatement())) {
                sqlInsertStatement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                this.connection.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String constructSQLInsertStatement(){
        StringBuilder sqlInsertStatement = new StringBuilder("INSERT INTO [dbo].[SummonerSpell] (Name, Description, SummonerLevelRequired) VALUES ");
        for(SummonerSpell summonerSpell : this.summonerSpellCollection.getSummonerSpells()) {
            sqlInsertStatement.append("('")
                    .append(summonerSpell.getName())
                    .append("', '")
                    .append(summonerSpell.getDescription())
                    .append("', '")
                    .append(summonerSpell.getLevelRequired())
                    .append("'), ");
        }
        System.out.println(sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";");
        return sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
    }
}
