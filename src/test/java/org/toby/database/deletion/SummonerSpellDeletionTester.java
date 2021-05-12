package org.toby.database.deletion;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.SummonerSpellDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.SummonerSpellInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.json.mappers.SummonerSpellCollectionMapper;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class SummonerSpellDeletionTester {

    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static SummonerSpellCollectionMapper mapper;
    private static final String lolDbConnectionString = "jdbc:sqlserver://localhost:1434;"  + "databaseName=loldb;" + "integratedSecurity=true;";
    private static final String summonerSpellFilePath = "loldb/datafiles/summoner_spell_info.json";
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setUpManager(){
        reader = new LolJsonReader(summonerSpellFilePath);
        connector = new LolDbConnector(lolDbConnectionString);
        mapper = new SummonerSpellCollectionMapper(reader);
        insertion = new SummonerSpellInsertion(connector, mapper.getCollection());
        deletion = new SummonerSpellDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
    }

    @Before
    public void populateSummonerSpellTable(){
        sqlManager.insert();
    }

    @Test
    public void deleteDataFromSummonerSpellTable(){
        sqlManager.delete();
    }




}
