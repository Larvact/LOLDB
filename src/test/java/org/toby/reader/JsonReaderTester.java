package org.toby.reader;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class JsonReaderTester {

    private static String filePath = "D:\\Documents\\SQL Datasets\\Lol Datasets\\champion_info_2.json";
    private static Reader reader;

    @BeforeClass
    public static void setup(){
        reader = new JsonFileReader(filePath);
    }

    @Test
    public void testRead(){
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
