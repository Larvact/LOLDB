package org.toby.reader;

import java.util.Map;

public interface CsvReader<T> {

    void read();

    Map<Integer, T> getRowDetailsMap();
}
