package org.toby.database.delete;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SummonerSpellDeletion extends Deletion{

    private static final String DELETESUMMONERSPELLDATASQLSTATEMENT = "DELETE FROM [dbo].[SummonerSpell]; DBCC CHECKIDENT ('SummonerSpell', RESEED, 0);";

    public SummonerSpellDeletion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void delete() {
        try {
            connector.connect();
            try (PreparedStatement deleteSummonerSpellStatement = this.connector.getConnection().prepareStatement(DELETESUMMONERSPELLDATASQLSTATEMENT)) {
                deleteSummonerSpellStatement.execute();
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
}
