package org.toby.reader;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.valueobject.csvobjects.GameDetail;

public class CsvGameReaderTester {

    private static Deserializer<GameDetail> deserializer;
    private static CsvReader<GameDetail> csvReader;

    @BeforeClass
    public static void setup(){
        deserializer = new GameDetailDeserilizer("");
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), deserializer);
        csvReader.read();
    }

    @Test
    public void ensureAllLinesInGamesCsvAreRead(){
        Assert.assertEquals(51490, csvReader.getDetails().size());
    }

    @Test
    public void ensureValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

}
