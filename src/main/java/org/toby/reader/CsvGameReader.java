package org.toby.reader;

import org.toby.csv.deserializers.Deserializer;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CsvGameReader<T> implements CsvReader<T>{

    private Map<Integer, T> rowDetailsMap;
    private Path path;
    private Deserializer<T> deserializer;

    public CsvGameReader(String filePath, Deserializer<T> deserializer) {
        this.path = Paths.get(filePath);
        this.deserializer = deserializer;
        this.rowDetailsMap = new HashMap<>();
    }

    @Override
    public void read(){
        try(BufferedReader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8)){
            String currentLine;
            reader.readLine();
            int currentRow = 2;
            while ((currentLine = reader.readLine()) != null){
                this.deserializer.setCsvString(currentLine);
                T gameDetail = this.deserializer.deserialize();
                this.rowDetailsMap.put(currentRow, gameDetail);
                currentRow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, T> getRowDetailsMap() {
        return this.rowDetailsMap;
    }
}
