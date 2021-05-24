package org.toby.database.insert;

import org.toby.database.LolDbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamInsertion extends Insertion{


    public TeamInsertion(LolDbConnector connector) {
        super(connector);
    }

    @Override
    public void insertData(){
        connector.connect();
        try(PreparedStatement teamInsertStatement = this.connector.getConnection().prepareStatement(constructTeamInsertStatement())) {
            teamInsertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connector.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String constructTeamInsertStatement() {
        StringBuilder insertStatementBuilder = new StringBuilder();
        insertStatementBuilder.append("INSERT INTO [dbo].[Team] VALUES ")
                            .append("('Blue'), ")
                            .append("('Purple'), ")
                            .append("('Neither');");
        return insertStatementBuilder.toString();
    }
}
