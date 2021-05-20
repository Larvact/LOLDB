package org.toby.csv.deserializers;

public abstract class Deserializer<T> {

    protected String csvString;

    public abstract T deserialize();

    public void setCsvString(String csvString) {
        this.csvString = csvString;
    }
}
