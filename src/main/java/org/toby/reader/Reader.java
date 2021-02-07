package org.toby.reader;

import java.io.IOException;

public abstract class Reader {

    protected String stringResult;

    public abstract void read() throws IOException;

    public String getStringResult() {
        return stringResult;
    }
}
