package org.toby.properties;

import org.junit.Assert;
import org.junit.Test;

public class PropertyRetrieverTester {

    @Test
    public void ensureTheCorrectValueIsRetrievedFromPropertiesFileUsingTheKeyChampionIdOutputFileLocation(){
        Assert.assertEquals("loldb/datafiles/champion_info_2.json", PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
    }

}
