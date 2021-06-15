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
import org.toby.json.dbobjectgenerators.SummonerSpellCollectionGenerator;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.json.reader.LolJsonReader;
import org.toby.json.reader.Reader;

public class SummonerSpellIdMapperTester {

    private static Reader reader;
    private static SummonerSpellCollectionGenerator summonerSpellCollectionGenerator;
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
        readJson();
        setupDataBaseData();
        setupSummonerSpellIdMapper();
    }

    private static void readJson(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.SUMMONER_SPELL_DATA_FILE_LOCATION.toString()));
        summonerSpellCollectionGenerator = new SummonerSpellCollectionGenerator(reader);
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new SummonerSpellInsertion(connector, summonerSpellCollectionGenerator.getCollection());
        deletion = new SummonerSpellDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupSummonerSpellIdMapper(){
        summonerSpellIdMapper = new SummonerSpellIdMapper(summonerSpellCollectionGenerator.getCollection(), connector);
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
