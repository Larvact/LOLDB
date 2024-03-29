package org.toby.json.deserialisers.summonerspell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;
import org.toby.valueobject.jsondeserialise.SummonerSpell;
import org.toby.json.reader.LolJsonReader;
import org.toby.json.reader.Reader;
import java.io.IOException;

public class CollectionDeserializerTester {

    private static String json;
    private static Reader reader;
    private static ObjectMapper mapper;
    private final int expectedNumberOfSummonerSpells = 15;

    //Flash Expected
    private final static int expectedId = 4;
    private final static int expectedSummonerLevel = 8;
    private final static String expectedName = "Flash";
    private final static String expectedDescription = "Teleports your champion a short distance toward your cursors location.";

    @BeforeClass
    public static void setup(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.SUMMONER_SPELL_DATA_FILE_LOCATION.toString()));
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = reader.getReadData();
        setupMapper();
    }

    private static void setupMapper(){
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("SummonerSpellCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(SummonerSpellCollection.class, new CollectionDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void ensureSummonerSpellCollectionHas15SpellsAfterDeserialization(){
        SummonerSpellCollection summonerSpellCollection = null;
        try {
            summonerSpellCollection = mapper.readValue(json, SummonerSpellCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedNumberOfSummonerSpells, summonerSpellCollection.getSummonerSpells().size());
    }

    @Test
    public void ensureFlashSummonerSpellHasBeenParsedCorrectly(){
        SummonerSpellCollection summonerSpellCollection = null;
        try {
            summonerSpellCollection = mapper.readValue(json, SummonerSpellCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SummonerSpell flash = null;
        for(SummonerSpell summonerSpell : summonerSpellCollection.getSummonerSpells()){
            if(summonerSpell.getName().equals("Flash")){
                flash = summonerSpell;
                break;
            }
        }
        Assert.assertEquals(expectedId, flash.getId());
        Assert.assertEquals(expectedName, flash.getName());
        Assert.assertEquals(expectedDescription, flash.getDescription());
        Assert.assertEquals(expectedSummonerLevel, flash.getLevelRequired());
    }
}

