package org.toby.database.idmapping;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.SeasonDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.SeasonInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.CsvGameReader;
import org.toby.reader.CsvReader;
import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.season.SeasonCollection;

public class SeasonIdMapperTester {


    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static SeasonCollection seasonCollection;
    private static SeasonIdMapper seasonIdMapper;

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    private final int expectedNumberOfSeasons = 1;
    private final byte seasonOldId = 9;
    private final byte expectedSeasonNewId = 1;


    @BeforeClass
    public static void setUpData(){
        readCsv();
        setupSeasonCollection();
        setupDataBaseData();
        setupSeasonIdMapper();
    }

    private static void readCsv(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new SeasonInsertion(connector, new SeasonCollection(csvReader.getRowDetailsMap().values()));
        deletion = new SeasonDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupSeasonCollection(){
        seasonCollection = new SeasonCollection(csvReader.getRowDetailsMap().values());
    }

    private static void setupSeasonIdMapper(){
        seasonIdMapper = new SeasonIdMapper(seasonCollection, connector);
        seasonIdMapper.map();
    }

    @Test
    public void ensureSpecificSeasonHasCorrectNewIdMappedViaTheirOldId(){
        byte newId = seasonIdMapper.getMapping().get(seasonOldId);
        Assert.assertEquals(expectedSeasonNewId, newId);
    }

    @Test
    public void ensureCorrectNumberOfChampionsArePresentInTheMap(){
        Assert.assertEquals(expectedNumberOfSeasons, seasonIdMapper.getMapping().size());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
