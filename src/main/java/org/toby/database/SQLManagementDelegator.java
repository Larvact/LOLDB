package org.toby.database;

import org.toby.database.managers.SQLManager;

public class SQLManagementDelegator {

    private SQLManager SQLManager;

    public SQLManagementDelegator(org.toby.database.managers.SQLManager SQLManager) {
        this.SQLManager = SQLManager;
    }

    public void setSQLManager(org.toby.database.managers.SQLManager SQLManager) {
        this.SQLManager = SQLManager;
    }

    public void insert(){
        SQLManager.insert();
    }

    public void delete(){
        SQLManager.delete();
    }
}
