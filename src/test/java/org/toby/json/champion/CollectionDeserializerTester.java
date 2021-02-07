package org.toby.json.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.content.champion.ChampionCollection;
import org.toby.reader.LolFileReader;
import org.toby.reader.Reader;

import java.io.IOException;


public class CollectionDeserializerTester {

    private static String json;

    private static String filePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private static Reader reader;

    @BeforeClass
    public static void setup(){
        reader = new LolFileReader(filePath);
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = reader.getStringResult();
    }

    @Test
    public void ensureChampionDeserializeMappingIsCorrect(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ChampionCollection.class, new CollectionDeserializer());
        mapper.registerModule(module);
        ChampionCollection championCollection = null;
        try {
            championCollection = mapper.readValue(json, ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


}
