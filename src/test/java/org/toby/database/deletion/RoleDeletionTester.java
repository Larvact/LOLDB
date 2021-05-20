package org.toby.database.deletion;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.RoleDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.RoleInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class RoleDeletionTester {

    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setUpManager(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        mapper = new ChampionCollectionMapper(reader);
        insertion = new RoleInsertion(connector, mapper.getCollection());
        deletion = new RoleDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
    }
/*
    @Before
    public void setupRoleData(){
        sqlManager.insert();
    }
*/
    @Test
    public void deleteDataFromRoleTable(){
        sqlManager.delete();
    };
}
