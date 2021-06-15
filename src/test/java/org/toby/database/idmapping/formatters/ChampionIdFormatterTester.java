package org.toby.database.idmapping.formatters;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.ChampionDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.idmapping.ChampionIdMapper;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.json.dbobjectgenerators.ChampionCollectionGenerator;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.csv.reader.CsvGameReader;
import org.toby.csv.reader.CsvReader;
import org.toby.json.reader.LolJsonReader;
import org.toby.json.reader.Reader;
import org.toby.valueobject.csvobjects.GameDetail;

public class ChampionIdFormatterTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static Reader reader;
    private static ChampionCollectionGenerator championCollectionGenerator;
    private static ChampionIdMapper championIdMapper;
    private static Format championIdFormatter;
    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;


    @BeforeClass
    public static void setup() {
        readCsv();
        readJson();
        setupDataBaseData();
        setupChampionIdMapper();
        formatGameDetails();
    }

    private static void readCsv(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
    }

    private static void readJson(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        championCollectionGenerator = new ChampionCollectionGenerator(reader);
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new ChampionInsertion(connector, championCollectionGenerator.getCollection());
        deletion = new ChampionDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupChampionIdMapper(){
        championIdMapper = new ChampionIdMapper(championCollectionGenerator.getCollection(), connector);
        championIdMapper.map();
    }

    private static void formatGameDetails(){
        championIdFormatter = new ChampionIdFormatter(csvReader.getRowDetailsMap(), championIdMapper.getMapping());
        championIdFormatter.format();
    }

    @Test
    public void ensureThatTheChampionIdForT1_champ1idHasBeenCorrectlyChanged(){
        Assert.assertEquals(20, csvReader.getRowDetailsMap().get(24707).getT1_champ1id());
    }

    @Test
    public void ensureThatTheChampionIdForT1_champ2idHasBeenCorrectlyChanged(){
        Assert.assertEquals(74, csvReader.getRowDetailsMap().get(36609).getT1_champ2id());
    }

    @Test
    public void ensureThatTheChampionIdForT1_champ3idHasBeenCorrectlyChanged(){
        Assert.assertEquals(12, csvReader.getRowDetailsMap().get(9310).getT1_champ3id());
    }

    @Test
    public void ensureThatTheChampionIdForT1_champ4idHasBeenCorrectlyChanged(){
        Assert.assertEquals(10, csvReader.getRowDetailsMap().get(9416).getT1_champ4id());
    }

    @Test
    public void ensureThatTheChampionIdForT1_champ5idHasBeenCorrectlyChanged(){
        Assert.assertEquals(133, csvReader.getRowDetailsMap().get(22073).getT1_champ5id());
    }

    @Test
    public void ensureThatTheChampionIdForT1_ban1HasBeenCorrectlyChanged(){
        Assert.assertEquals(132, csvReader.getRowDetailsMap().get(35573).getT1_ban1());
    }

    @Test
    public void ensureThatTheChampionIdForT1_ban2HasBeenCorrectlyChanged(){
        Assert.assertEquals(54, csvReader.getRowDetailsMap().get(5499).getT1_ban2());
    }

    @Test
    public void ensureThatTheChampionIdForT1_ban3HasBeenCorrectlyChanged(){
        Assert.assertEquals(12, csvReader.getRowDetailsMap().get(11251).getT1_ban3());
    }

    @Test
    public void ensureThatTheChampionIdForT1_ban4HasBeenCorrectlyChanged(){
        Assert.assertEquals(113, csvReader.getRowDetailsMap().get(19975).getT1_ban4());
    }

    @Test
    public void ensureThatTheChampionIdForT1_ban5HasBeenCorrectlyChanged(){
        Assert.assertEquals(121, csvReader.getRowDetailsMap().get(8359).getT1_ban5());
    }

    @Test
    public void ensureThatTheChampionIdForT2_champ1idHasBeenCorrectlyChanged(){
        Assert.assertEquals(136, csvReader.getRowDetailsMap().get(47209).getT2_champ1id());
    }

    @Test
    public void ensureThatTheChampionIdForT2_champ2idHasBeenCorrectlyChanged(){
        Assert.assertEquals(12, csvReader.getRowDetailsMap().get(33984).getT2_champ2id());
    }

    @Test
    public void ensureThatTheChampionIdForT2_champ3idHasBeenCorrectlyChanged(){
        Assert.assertEquals(123, csvReader.getRowDetailsMap().get(17710).getT2_champ3id());
    }

    @Test
    public void ensureThatTheChampionIdForT2_champ4idHasBeenCorrectlyChanged(){
        Assert.assertEquals(7, csvReader.getRowDetailsMap().get(14525).getT2_champ4id());
    }

    @Test
    public void ensureThatTheChampionIdForT2_champ5idHasBeenCorrectlyChanged(){
        Assert.assertEquals(47, csvReader.getRowDetailsMap().get(4442).getT2_champ5id());
    }

    @Test
    public void ensureThatTheChampionIdForT2_ban1HasBeenCorrectlyChanged(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(24370).getT2_ban1());
    }

    @Test
    public void ensureThatTheChampionIdForT2_ban2HasBeenCorrectlyChanged(){
        Assert.assertEquals(128, csvReader.getRowDetailsMap().get(9237).getT2_ban2());
    }

    @Test
    public void ensureThatTheChampionIdForT2_ban3HasBeenCorrectlyChanged(){
        Assert.assertEquals(12, csvReader.getRowDetailsMap().get(17195).getT2_ban3());
    }

    @Test
    public void ensureThatTheChampionIdForT2_ban4HasBeenCorrectlyChanged(){
        Assert.assertEquals(117, csvReader.getRowDetailsMap().get(28201).getT2_ban4());
    }

    @Test
    public void ensureThatTheChampionIdForT2_ban5HasBeenCorrectlyChanged(){
        Assert.assertEquals(115, csvReader.getRowDetailsMap().get(1885).getT2_ban5());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
