package org.toby.database.idmapping.formatters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.idmapping.ChampionIdMapper;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.CsvGameReader;
import org.toby.reader.CsvReader;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;
import org.toby.valueobject.csvobjects.GameDetail;

public class ChampionIdFormatterTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static Reader reader;
    private static ChampionCollectionMapper championCollectionMapper;
    private static ChampionIdMapper championIdMapper;
    private static Format championIdFormatter;


    @BeforeClass
    public static void setup() {
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        championCollectionMapper = new ChampionCollectionMapper(reader);
        setupChampionIdMapper();
        championIdFormatter = new ChampionIdFormatter(csvReader.getRowDetailsMap(), championIdMapper.getMapping());
        championIdFormatter.format();
    }

    private static void setupChampionIdMapper(){
        championIdMapper = new ChampionIdMapper(championCollectionMapper.getCollection());
        championIdMapper.map();
    }

    @Test
    public void ensureThatTheChampionIdForT1_champ1idHasBeenCorrectlyChanged(){
        Assert.assertEquals(126, csvReader.getRowDetailsMap().get(2).getT1_champ1id());

    }

}
