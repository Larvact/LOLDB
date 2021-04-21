package org.toby.writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.content.champion.ChampionCollection;
import org.toby.json.champion.CollectionDeserializer;
import org.toby.reader.LolFileReader;
import org.toby.reader.Reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChampionIdWriterTester {

    private static String json;
    private final static String filePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private static Reader reader;
    private static ObjectMapper mapper;
    private static ChampionCollection championCollection;
    private static Writer championFileWriter;

    private static BufferedReader generatedFileReader;
    private final static String generatedFilePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\ChampionIdMapping.txt";
    private static Map<String, String> generatedChampionIdMap;
    private final int numberOfChampions = 139;


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
        setupChampionCollection();
        championFileWriter = new ChampionIdWriter(championCollection);
        championFileWriter.write();
        setupGeneratedFileReader();
        readGeneratedFile();
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

    private static void setupGeneratedFileReader() {
        try {
            generatedFileReader = new BufferedReader(new FileReader(generatedFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readGeneratedFile(){
        generatedChampionIdMap = new HashMap<>();
        String currentLine;
        try {
            currentLine = generatedFileReader.readLine();
            currentLine = generatedFileReader.readLine();
            while (currentLine != null){
                String newId = currentLine.split("\\|")[0];
                String oldId = currentLine.split("\\|")[1];
                System.out.println(newId + "|" + oldId);
                generatedChampionIdMap.put(newId, oldId);
                currentLine = generatedFileReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void ensureAatroxHasANewIdOf1AndIsMappedToTheOldIdOf266(){
        String aatroxOldId = generatedChampionIdMap.get("1");
        Assert.assertEquals("266",aatroxOldId);
    }

    @Test
    public void ensureCorrectNumberOfChampionsHaveBeenWrittenToTextFile(){
        Assert.assertEquals(numberOfChampions,generatedChampionIdMap.size());
    }

}
