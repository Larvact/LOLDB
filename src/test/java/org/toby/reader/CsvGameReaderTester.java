package org.toby.reader;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.valueobject.csvobjects.GameDetail;

public class CsvGameReaderTester {

    private static Deserializer<GameDetail> deserializer;
    private static CsvReader<GameDetail> csvReader;
    private static final String csvFilePath = "loldb/datafiles/games.csv";

    @BeforeClass
    public static void setup(){
        deserializer = new GameDetailDeserilizer("");
        csvReader = new CsvGameReader<>(csvFilePath, deserializer);
    }

    @Test
    public void ensureAllLinesInGamesCsvAreRead(){
        csvReader.read();
        Assert.assertEquals(51490, csvReader.getDetails().size());
    }

    @Test
    public void ensureValueIsCorrect(){
        csvReader.read();
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

}
