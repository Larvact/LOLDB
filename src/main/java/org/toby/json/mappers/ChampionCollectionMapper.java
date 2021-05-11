package org.toby.json.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.valueobject.jsondeserialise.databasetransfer.champion.ChampionCollection;
import org.toby.json.deserialisers.champion.CollectionDeserializer;
import org.toby.reader.Reader;

import java.io.IOException;
import java.util.Collections;

public class ChampionCollectionMapper {

    private Reader reader;
    private ChampionCollection championCollection;
    private ObjectMapper mapper;

    public ChampionCollectionMapper(Reader reader) {
        this.reader = reader;
        readChampionFile();
        setupChampionCollectionMapper();
        populateChampionCollection();
        Collections.sort(championCollection.getChampions());
    }

    private void readChampionFile(){
        try {
            this.reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupChampionCollectionMapper(){
        this.mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionCollectionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ChampionCollection.class, new CollectionDeserializer());
        this.mapper.registerModule(module);
    }

    private void populateChampionCollection(){
        try {
            this.championCollection = this.mapper.readValue(this.reader.getReadData(), ChampionCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public ChampionCollection getCollection() {
        return championCollection;
    }

}
