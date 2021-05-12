package org.toby.reader;

import java.util.List;

public interface CsvReader<T> {

    void read();

    List<T> getDetails();
}
