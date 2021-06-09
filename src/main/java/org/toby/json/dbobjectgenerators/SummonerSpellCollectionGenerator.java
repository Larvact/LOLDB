package org.toby.json.dbobjectgenerators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.json.deserialisers.summonerspell.CollectionDeserializer;
import org.toby.reader.Reader;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;

import java.io.IOException;
import java.util.Collections;

public class SummonerSpellCollectionGenerator {

    private Reader reader;
    private SummonerSpellCollection summonerSpellCollection;
    private ObjectMapper mapper;

    public SummonerSpellCollectionGenerator(Reader reader) {
        this.reader = reader;
        readSummonerSpellFile();
        setupSummonerSpellCollectionMapper();
        populateSummonerSpellCollection();
        Collections.sort(summonerSpellCollection.getSummonerSpells());
    }

    private void readSummonerSpellFile(){
        try {
            this.reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupSummonerSpellCollectionMapper(){
        this.mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("SummonerSpellDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(SummonerSpellCollection.class, new CollectionDeserializer());
        this.mapper.registerModule(module);
    }

    private void populateSummonerSpellCollection(){
        try {
            this.summonerSpellCollection = this.mapper.readValue(this.reader.getReadData(), SummonerSpellCollection.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public SummonerSpellCollection getCollection() {
        return this.summonerSpellCollection;
    }
}
