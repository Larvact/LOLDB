package org.toby.database.delete;

import org.toby.database.LolDbConnector;

public abstract class Deletion {

    protected LolDbConnector connector;

    protected Deletion(LolDbConnector connector) {
        this.connector = connector;
    }

    public abstract void delete();
}
