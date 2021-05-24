package org.toby.database.insert;

import org.toby.database.LolDbConnector;


public abstract class Insertion {

    protected LolDbConnector connector;

    protected Insertion(LolDbConnector connector) {
        this.connector = connector;
    }

    public abstract void insertData();
}
