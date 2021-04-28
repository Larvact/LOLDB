package org.toby.database.delete;

import org.toby.database.LolDbConnector;

public abstract class Deletion {

    protected LolDbConnector connection;

    protected Deletion(LolDbConnector connection) {
        this.connection = connection;
    }

    public abstract void delete();
}
