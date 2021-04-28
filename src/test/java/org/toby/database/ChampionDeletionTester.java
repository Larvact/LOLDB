package org.toby.database;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.delete.ChampionDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.database.managers.SQLChampionManager;
import org.toby.database.managers.SQLManager;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.reader.LolFileReader;
import org.toby.reader.Reader;

public class ChampionDeletionTester {

    private static SQLManagementDelegator delegator;
    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static final String lolDbConnectionString = "jdbc:sqlserver://localhost:1434;"  + "databaseName=loldb;" + "integratedSecurity=true;";
    private static final String lolChampionFilePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setUpChampionManager(){
        reader = new LolFileReader(lolChampionFilePath);
        connector = new LolDbConnector(lolDbConnectionString);
        mapper = new ChampionCollectionMapper(reader);
        insertion = new ChampionInsertion(connector, mapper.getChampionCollection());
        deletion = new ChampionDeletion(connector);
        sqlManager = new SQLChampionManager(insertion, deletion);
        delegator = new SQLManagementDelegator(sqlManager);
    }

    @Before
    public void setupChampionData(){
        delegator.insert();
    }

    @Test
    public void deleteDataFromChampionTable(){
        delegator.delete();
    };
}
