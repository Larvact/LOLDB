package org.toby.database.insertion;

import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.ChampionRoleDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.insert.ChampionRoleInsertion;
import org.toby.database.insert.Insertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class ChampionRoleTester {

    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static final String lolDbConnectionString = "jdbc:sqlserver://localhost:1434;"  + "databaseName=loldb;" + "integratedSecurity=true;";
    private static final String lolChampionFilePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setUpManager(){
        reader = new LolJsonReader(lolChampionFilePath);
        connector = new LolDbConnector(lolDbConnectionString);
        mapper = new ChampionCollectionMapper(reader);
        insertion = new ChampionRoleInsertion(connector, mapper.getCollection());
        deletion = new ChampionRoleDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
    }

    @Test
    public void populateChampionRoleTable(){
        sqlManager.insert();
    }

}
