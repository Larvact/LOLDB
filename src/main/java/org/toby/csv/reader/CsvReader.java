package org.toby.csv.reader;

import java.util.Map;

public interface CsvReader<T> {

    void read();

    Map<Integer, T> getRowDetailsMap();
}
