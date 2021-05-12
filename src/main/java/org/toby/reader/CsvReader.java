package org.toby.reader;

import java.util.List;

public interface CsvReader<T> {


    public abstract void read();

    public List<T> getDetails();
}
