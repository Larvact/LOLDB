package org.toby.reader;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.json.reader.LolJsonReader;
import org.toby.json.reader.Reader;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;

import java.io.IOException;

public class LolReaderTester {

    private static Reader reader;

    @BeforeClass
    public static void setup(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDariusIsInTheResultantString(){
        Assert.assertTrue(reader.getReadData().contains("Darius"));
    }

    @Test
    public void testTheFirstElementIsNotTheStringNull(){
        Assert.assertFalse(reader.getReadData().substring(0, 4).equals("null"));
    }
}