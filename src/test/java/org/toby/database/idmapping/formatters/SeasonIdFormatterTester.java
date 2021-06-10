package org.toby.database.idmapping.formatters;

import org.junit.*;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.SeasonDeletion;
import org.toby.database.idmapping.SeasonIdMapper;
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

import java.util.Random;

public class SeasonIdFormatterTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static SeasonCollection seasonCollection;
    private static SeasonIdMapper seasonIdMapper;
    private static Format seasonIdFormatter;
    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    private byte expectedSeasonId = 1;
    private int selectedCsvRow;

    @BeforeClass
    public static void setup() {
        readCsv();
        setupSeasonCollection();
        setupDataBaseData();
        setupSeasonIdMapper();
        formatGameDetails();
    }

    private static void readCsv(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
    }

    private static void setupSeasonCollection(){
        seasonCollection = new SeasonCollection(csvReader.getRowDetailsMap().values());
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new SeasonInsertion(connector, seasonCollection);
        deletion = new SeasonDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupSeasonIdMapper(){
        seasonIdMapper = new SeasonIdMapper(seasonCollection, connector);
        seasonIdMapper.map();
    }

    private static void formatGameDetails(){
        seasonIdFormatter = new SeasonIdFormatter(csvReader.getRowDetailsMap(), seasonIdMapper.getMapping());
        seasonIdFormatter.format();
    }

    @Before
    public void selectRandomCsvRow(){
        Random random = new Random();
        selectedCsvRow = random.nextInt(csvReader.getRowDetailsMap().size()) + 1;
    }

    @Test
    public void ensureThatTheSeasonIdBeenCorrectlyChanged(){
        Assert.assertEquals(expectedSeasonId, csvReader.getRowDetailsMap().get(selectedCsvRow).getSeasonId());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
