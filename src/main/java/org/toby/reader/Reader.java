package org.toby.reader;

import java.io.IOException;

public abstract class Reader {

    protected StringBuilder readData;

    public abstract void read() throws IOException;

    public String getReadData() {
        return readData.toString();
    }
}
