package org.toby.database.idmapping;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

public class ChampionIdMapperTester {

    private static Reader reader;
    private static ChampionCollectionMapper championCollectionMapper;
    private static ChampionIdMapper championIdMapper;

    private final int expectedNumberOfChampions = 138;
    //change values as per specific champion. Get the old Id from the file and the new Id from the database after running the champion table insert. Currently using the champion Riven.
    private final int championOldId = 92;
    private final int expectedChampionNewId = 91;


    @BeforeClass
    public static void setup() {
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        championCollectionMapper = new ChampionCollectionMapper(reader);
        setupChampionIdMapper();
    }

    private static void setupChampionIdMapper(){
        championIdMapper = new ChampionIdMapper(championCollectionMapper.getCollection());
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
}
