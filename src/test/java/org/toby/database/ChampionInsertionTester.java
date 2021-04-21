package org.toby.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.content.champion.ChampionCollection;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.json.champion.CollectionDeserializer;
import org.toby.reader.LolFileReader;
import org.toby.reader.Reader;

import java.io.IOException;

public class ChampionInsertionTester {

    private static String json;
    private final static String filePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private final static String lolDbConnectionString = "jdbc:sqlserver://localhost:1434;"  + "databaseName=loldb;" + "integratedSecurity=true;";
    private static LolDbConnector connector;
    private static Insertion championInsertion;
    private static Reader reader;
    private static ObjectMapper mapper;
    private static ChampionCollection championCollection;

    @BeforeClass
    public static void setup(){
        reader = new LolFileReader(filePath);
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = reader.getStringResult();
        setupMapper();
        populateChampionCollection();
        connector = new LolDbConnector(lolDbConnectionString);
        championInsertion = new ChampionInsertion(connector, championCollection);
        championInsertion.insertData();
    }

    private static void setupMapper(){
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ChampionCollection.class, new CollectionDeserializer());
        mapper.registerModule(module);
    }

    private static void populateChampionCollection(){
        try {
            championCollection = mapper.readValue(json, ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void populateChampionTable(){

    };
}
