package org.toby.writer;


import org.toby.content.champion.ChampionCollection;
import org.toby.lolobject.Champion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChampionIdWriter implements Writer {

    private ChampionCollection championCollection;
    private BufferedWriter fileWriter;
    private static final String OUTPUTFILELOCATION = "D:\\Documents\\SQL Datasets\\Lol Datasets\\ChampionIdMapping.txt";

    public ChampionIdWriter(ChampionCollection championCollection) {
        deleteChampionIdMappingFile();
        this.championCollection = championCollection;
        try {
            fileWriter = new BufferedWriter(new FileWriter(OUTPUTFILELOCATION));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void write() {
        Collections.sort(this.championCollection.getChampions());
        int newIndex = 1;
        try {
            fileWriter.write("New|Old");
            fileWriter.newLine();
            for (Champion champion : this.championCollection.getChampions()) {
                fileWriter.write(newIndex + "|" + champion.getId());
                fileWriter.newLine();
                newIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void deleteChampionIdMappingFile(){
        Path path = Paths.get(OUTPUTFILELOCATION);
        try{
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
