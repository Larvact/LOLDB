package org.toby.database.delete;

import org.toby.database.LolDbConnector;

public abstract class Deletion {

    protected LolDbConnector connector;

    public Deletion(LolDbConnector connector) {
        this.connector = connector;
    }

    public abstract void delete();
}
