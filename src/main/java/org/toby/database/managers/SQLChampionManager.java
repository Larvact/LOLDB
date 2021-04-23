package org.toby.database.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.content.champion.ChampionCollection;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.ChampionDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.json.deserialisers.champion.CollectionDeserializer;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.reader.LolFileReader;
import org.toby.reader.Reader;

import java.io.IOException;

public class SQLChampionManager extends SQLManager{

    private ChampionCollectionMapper mapper;
    private LolDbConnector connector;
    private Insertion insertion;
    private Deletion deletion;

    public SQLChampionManager(ChampionCollectionMapper mapper, LolDbConnector connector, Insertion insertion, Deletion deletion) {
        this.mapper = mapper;
        this.connector = connector;
        this.insertion = insertion;
        this.deletion = deletion;
    }

    @Override
    public void insert(){
        this.insertion.insertData();
    }

    @Override
    public void delete() {
        this.deletion.delete();
    }

}
