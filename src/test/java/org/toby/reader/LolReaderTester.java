package org.toby.reader;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class LolReaderTester {

    private static String filePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private static Reader reader;

    @BeforeClass
    public static void setup(){
        reader = new LolJsonReader(filePath);
    }

    @Test
    public void testDariusIsInTheResultantString(){
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(reader.getReadData().contains("Darius"));
        System.out.println(reader.getReadData());
    }

    @Test
    public void testTheFirstElementIsNotTheStringNull(){
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(!reader.getReadData().substring(0,4).equals("null"));
    }





}
