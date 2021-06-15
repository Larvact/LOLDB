package org.toby.database.idmapping.formatters;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.SummonerSpellDeletion;
import org.toby.database.idmapping.SummonerSpellIdMapper;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.SummonerSpellInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.json.dbobjectgenerators.SummonerSpellCollectionGenerator;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.csv.reader.CsvGameReader;
import org.toby.csv.reader.CsvReader;
import org.toby.json.reader.LolJsonReader;
import org.toby.json.reader.Reader;
import org.toby.valueobject.csvobjects.GameDetail;

public class SummonerSpellIdFormatterTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static Reader reader;
    private static SummonerSpellCollectionGenerator summonerSpellCollectionGenerator;
    private static SummonerSpellIdMapper summonerSpellIdMapper;
    private static Format summonerSpellIdFormatter;
    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setup() {
        readCsv();
        readJson();
        setupDataBaseData();
        setupSummonerSpellIdMapper();
        formatGameDetails();
    }

    private static void readCsv(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
    }

    private static void readJson(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.SUMMONER_SPELL_DATA_FILE_LOCATION.toString()));
        summonerSpellCollectionGenerator = new SummonerSpellCollectionGenerator(reader);
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new SummonerSpellInsertion(connector, summonerSpellCollectionGenerator.getCollection());
        deletion = new SummonerSpellDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupSummonerSpellIdMapper(){
        summonerSpellIdMapper = new SummonerSpellIdMapper(summonerSpellCollectionGenerator.getCollection(), connector);
        summonerSpellIdMapper.map();
    }

    private static void formatGameDetails(){
        summonerSpellIdFormatter = new SummonerSpellIdFormatter(csvReader.getRowDetailsMap(), summonerSpellIdMapper.getMapping());
        summonerSpellIdFormatter.format();
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ1_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(9164).getT1_champ1_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ1_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(1371).getT1_champ1_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ2_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(5, csvReader.getRowDetailsMap().get(13554).getT1_champ2_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ2_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(13, csvReader.getRowDetailsMap().get(14713).getT1_champ2_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ3_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(13, csvReader.getRowDetailsMap().get(15464).getT1_champ3_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ3_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(14, csvReader.getRowDetailsMap().get(14713).getT1_champ3_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ4_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(16322).getT1_champ4_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ4_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(14, csvReader.getRowDetailsMap().get(50794).getT1_champ4_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ5_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(5, csvReader.getRowDetailsMap().get(42931).getT1_champ5_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT1_champ5_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(13, csvReader.getRowDetailsMap().get(43542).getT1_champ5_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ1_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(8, csvReader.getRowDetailsMap().get(9887).getT2_champ1_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ1_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(13, csvReader.getRowDetailsMap().get(28858).getT2_champ1_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ2_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(13, csvReader.getRowDetailsMap().get(24763).getT2_champ2_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ2_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(8, csvReader.getRowDetailsMap().get(26113).getT2_champ2_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ3_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(48052).getT2_champ3_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ3_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(33784).getT2_champ3_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ4_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(8, csvReader.getRowDetailsMap().get(47658).getT2_champ4_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ4_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(14262).getT2_champ4_sum2());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ5_sum1HasBeenCorrectlyChanged(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(42083).getT2_champ5_sum1());
    }

    @Test
    public void ensureThatTheSummonerspellIdForT2_champ5_sum2HasBeenCorrectlyChanged(){
        Assert.assertEquals(13, csvReader.getRowDetailsMap().get(42298).getT2_champ5_sum2());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}