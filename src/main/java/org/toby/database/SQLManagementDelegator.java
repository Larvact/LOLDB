package org.toby.database;

import org.toby.database.managers.SQLManager;

public class SQLManagementDelegator {

    private SQLManager sqlManager;

    public SQLManagementDelegator(org.toby.database.managers.SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public void setSqlManager(org.toby.database.managers.SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public void insert(){
        sqlManager.insert();
    }

    public void delete(){
        sqlManager.delete();
    }
}
