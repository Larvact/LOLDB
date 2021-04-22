package org.toby.database;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.managers.SQLChampionManager;
import org.toby.database.managers.SQLManager;

public class ChampionDeletionTester {

    private static SQLManagementDelegator delegator;
    private static SQLManager sqlManager;

    @BeforeClass
    public static void setUpChampionManager(){
        sqlManager = new SQLChampionManager();
        delegator = new SQLManagementDelegator(sqlManager);
    }

    @Before
    public void setupChampionData(){
        delegator.insert();
    }

    @Test
    public void deleteDataFromChampionTable(){
        delegator.delete();
    };
}
