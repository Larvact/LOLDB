package org.toby.database.idmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.idmapper.ChampionIdMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.json.deserialisers.champion.CollectionDeserializer;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

import java.io.IOException;

public class ChampionIdMapperTester {

    private static String json;
    private static Reader reader;
    private static ObjectMapper mapper;
    private static ChampionCollection championCollection;
    private static ChampionIdMapper championIdMapper;
    private final int numberOfChampions = 138;


    @BeforeClass
    public static void setup(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = reader.getReadData();
        setupMapper();
        setupChampionCollection();
        setupChampionIdMapper();
    }

    private static void setupMapper(){
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ChampionCollection.class, new CollectionDeserializer());
        mapper.registerModule(module);
    }

    private static void setupChampionCollection() {
        try {
            championCollection = mapper.readValue(json, ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void setupChampionIdMapper(){
        championIdMapper = new ChampionIdMapper(championCollection);
        championIdMapper.map();
    }

    @Test
    public void ensureAatroxHasANewIdOf1AndIsMappedViaTheOldIdOf266(){
        Integer aatroxNewId = championIdMapper.getChampionIdMap().get(266);
        Assert.assertEquals(1,aatroxNewId.intValue());
    }

    @Test
    public void ensureCorrectNumberOfChampionsArePresentInTheMap(){
        Assert.assertEquals(numberOfChampions,championIdMapper.getChampionIdMap().size());
    }

}
