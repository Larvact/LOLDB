package org.toby.database.insert;

import org.toby.database.LolDbConnector;
import org.toby.valueobject.jsondeserialise.SummonerSpell;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SummonerSpellInsertion extends Insertion{

    private final SummonerSpellCollection summonerSpellCollection;

    public SummonerSpellInsertion(LolDbConnector connector, SummonerSpellCollection summonerSpellCollection) {
        super(connector);
        this.summonerSpellCollection = summonerSpellCollection;
    }

    @Override
    public void insertData() {
        try {
            this.connector.connect();
            try (PreparedStatement sqlInsertStatement = this.connector.getConnection().prepareStatement(constructSQLInsertStatement())) {
                sqlInsertStatement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                this.connector.getConnection().close();
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
        return sqlInsertStatement.substring(0, sqlInsertStatement.length() - 2) + ";";
    }
}
