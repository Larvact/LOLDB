package org.toby.database.insert;

import org.toby.database.LolDbConnector;

public abstract class Insertion {

    protected LolDbConnector connection;

    public Insertion(LolDbConnector connection) {
        this.connection = connection;
    }

    public abstract void insertData();
}
