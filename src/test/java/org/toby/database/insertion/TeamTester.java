package org.toby.database.insertion;

import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.TeamDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.TeamInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;


public class TeamTester {

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setUpManager(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new TeamInsertion(connector);
        deletion = new TeamDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
    }

    @Test
    public void populateTeamTable(){
        sqlManager.insert();
    }
}
