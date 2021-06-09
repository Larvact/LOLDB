package org.toby.database.idmapping;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.ChampionDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.json.dbobjectgenerators.ChampionCollectionGenerator;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class ChampionIdMapperTester {

    private static Reader reader;
    private static ChampionCollectionGenerator championCollectionGenerator;
    private static ChampionIdMapper championIdMapper;

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    private final int expectedNumberOfChampions = 139;
    //change values as per specific champion. Get the old Id from the file and the new Id from the database after running the champion table insert. Currently using the champion Lissandra.
    private final int championOldId = 127;
    private final int expectedChampionNewId = 63;


    @BeforeClass
    public static void setup() {
        readJson();
        setupDataBaseData();
        setupChampionIdMapper();
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

    @Test
    public void ensureSpecificChampionHasCorrectNewIdMappedViaTheirOldId(){
        Integer newId = championIdMapper.getMapping().get(championOldId);
        Assert.assertEquals(expectedChampionNewId, newId.intValue());
    }

    @Test
    public void ensureCorrectNumberOfChampionsArePresentInTheMap(){
        Assert.assertEquals(expectedNumberOfChampions,championIdMapper.getMapping().size());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
