package org.toby.json.deserialisers.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.valueobject.jsondeserialise.Champion;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CollectionDeserializerTester {

    private static String json;
    private static Reader reader;
    private static ObjectMapper mapper;
    private final int expectedNumberOfChampions = 139;

    //Darius Expected
    private final static int expectedId = 122;
    private final static String expectedName = "Darius";
    private final static String expectedTitle = "the Hand of Noxus";
    private static List<String> expectedRoles;

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
        setupExpectedResults();
    }

    private static void setupMapper(){
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ChampionCollection.class, new CollectionDeserializer());
        mapper.registerModule(module);
    }

    private static void setupExpectedResults(){
        expectedRoles = new ArrayList<>();
        expectedRoles.add("Fighter");
        expectedRoles.add("Tank");
    }

    @Test
    public void ensureChampionCollectionHas139ChampionsAfterDeserialization(){
        ChampionCollection championCollection = null;
        try {
            championCollection = mapper.readValue(json, ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedNumberOfChampions, championCollection.getChampions().size());
    }

    @Test
    public void ensureDariusChampionHasBeenParsedCorrectly(){
        ChampionCollection championCollection = null;
        try {
            championCollection = mapper.readValue(json, ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Champion darius = null;
        for(Champion champion : championCollection.getChampions()){
            if(champion.getName().equals("Darius")){
                darius = champion;
                break;
            }
        }
        Assert.assertEquals(expectedId, darius.getId());
        Assert.assertEquals(expectedName, darius.getName());
        Assert.assertEquals(expectedTitle, darius.getTitle());
        Assert.assertEquals(expectedRoles, darius.getRoles());
    }
}
