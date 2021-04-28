package org.toby.properties;

import org.junit.Assert;
import org.junit.Test;

public class PropertyRetrieverTester {

    @Test
    public void ensureTheCorrectValueIsRetrievedFromPropertiesFileUsingTheKeyChampionIdOutputFileLocation(){
        Assert.assertEquals("D:\\Documents\\SQL Datasets\\Lol Datasets\\ChampionIdMapping.txt", PropertyRetriever.getProperty("championIdOutputFileLocation"));
    }

}
