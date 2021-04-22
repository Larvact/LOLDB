package org.toby.database.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.content.champion.ChampionCollection;
import org.toby.database.LolDbConnector;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.json.champion.CollectionDeserializer;
import org.toby.reader.LolFileReader;
import org.toby.reader.Reader;

import java.io.IOException;

public class SQLChampionManager extends SQLManager{

    private String json;
    private final String filePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private LolDbConnector connector;
    private Insertion championInsertion;
    private Reader reader;
    private ObjectMapper mapper;
    private ChampionCollection championCollection;

    @Override
    public void insert(){
        readChampionFile();
        setupChampionCollectionMapper();
        populateChampionCollection();
        this.connector = new LolDbConnector(this.lolDbConnectionString);
        this.championInsertion = new ChampionInsertion(this.connector, this.championCollection);
        this.championInsertion.insertData();
    }

    @Override
    public void delete() {
        //DO LATER
    }

    private void readChampionFile(){
        this.reader = new LolFileReader(this.filePath);
        try {
            this.reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.json = this.reader.getStringResult();
    }

    private void setupChampionCollectionMapper(){
        this.mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ChampionCollection.class, new CollectionDeserializer());
        this.mapper.registerModule(module);
    }

    private void populateChampionCollection(){
        try {
            this.championCollection = this.mapper.readValue(this.json, ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
