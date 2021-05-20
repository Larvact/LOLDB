package org.toby.database.idmapping;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.json.mappers.SummonerSpellCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class SummonerSpellMapperTester {

    private static Reader reader;
    private static SummonerSpellCollectionMapper summonerSpellCollectionMapper;
    private static SummonerSpellIdMapper summonerSpellIdMapper;

    private final int expectedNumberOfSummonerSpells = 15;
    //change values as per specific summoner spell. Get the old Id from the file and the new Id from the database after running the summoner spell table insert. Currently using the summoner spell Flash.
    private final int summonerSpellOldId = 4;
    private final int expectedSummonerSpellNewId = 6;


    @BeforeClass
    public static void setup() {
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.SUMMONER_SPELL_DATA_FILE_LOCATION.toString()));
        summonerSpellCollectionMapper = new SummonerSpellCollectionMapper(reader);
        setupSummonerSpellIdMapper();
    }

    private static void setupSummonerSpellIdMapper(){
        summonerSpellIdMapper = new SummonerSpellIdMapper(summonerSpellCollectionMapper.getCollection());
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
}
