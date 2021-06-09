package org.toby.database.idmapping;

import org.junit.AfterClass;
import org.junit.Assert;
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
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class SummonerSpellMapperTester {

    private static Reader reader;
    private static SummonerSpellCollectionMapper summonerSpellCollectionMapper;
    private static SummonerSpellIdMapper summonerSpellIdMapper;

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    private final int expectedNumberOfSummonerSpells = 15;
    //change values as per specific summoner spell. Get the old Id from the file and the new Id from the database after running the summoner spell table insert. Currently using the summoner spell Flash.
    private final int summonerSpellOldId = 4;
    private final int expectedSummonerSpellNewId = 6;


    @BeforeClass
    public static void setup() {
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.SUMMONER_SPELL_DATA_FILE_LOCATION.toString()));
        summonerSpellCollectionMapper = new SummonerSpellCollectionMapper(reader);
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new SummonerSpellInsertion(connector, summonerSpellCollectionMapper.getCollection());
        deletion = new SummonerSpellDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
        setupSummonerSpellIdMapper();
    }

    private static void setupSummonerSpellIdMapper(){
        summonerSpellIdMapper = new SummonerSpellIdMapper(summonerSpellCollectionMapper.getCollection(), connector);
        summonerSpellIdMapper.map();
    }

    @Test
    public void ensureSpecificSummonerSpellHasCorrectNewIdMappedViaTheirOldId(){
        Integer newId = summonerSpellIdMapper.getMapping().get(summonerSpellOldId);
        Assert.assertEquals(expectedSummonerSpellNewId, newId.intValue());
    }

    @Test
    public void ensureCorrectNumberOfSummonerSpellsArePresentInTheMap(){
        Assert.assertEquals(expectedNumberOfSummonerSpells, summonerSpellIdMapper.getMapping().size());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
