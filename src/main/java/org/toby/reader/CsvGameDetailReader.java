package org.toby.reader;

import org.toby.csv.deserializers.Deserializer;
import org.toby.valueobject.csvobjects.GameDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class CsvGameDetailReader implements CsvReader<GameDetail>{

    private List<GameDetail> details;
    private Path path;
    private Deserializer<GameDetail> deserializer;

    public CsvGameDetailReader(String filePath, Deserializer<GameDetail> deserializer) {
        this.path = Paths.get(filePath);
        this.deserializer = deserializer;
        this.details = new ArrayList<>();
    }

    @Override
    public void read(){
        try(BufferedReader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8)){
            String currentLine;
            reader.readLine();
            while ((currentLine = reader.readLine()) != null){
                this.deserializer.setCsvString(currentLine);
                GameDetail gameDetail = this.deserializer.deserialize();
                this.details.add(gameDetail);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GameDetail> getDetails() {
        return this.details;
    }


}
