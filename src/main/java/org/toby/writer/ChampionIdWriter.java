package org.toby.writer;


import org.toby.content.champion.ChampionCollection;
import org.toby.lolobject.Champion;

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ChampionIdWriter implements Writer {

    private ChampionCollection championCollection;
    private BufferedWriter fileWriter;
    private final String outputFileLocation = "D:\\Documents\\SQL Datasets\\Lol Datasets\\ChampionIdMapping.txt";
    private File championIdMappingFile;

    public ChampionIdWriter(ChampionCollection championCollection) {
        deleteChampionIdMappingFile();
        this.championCollection = championCollection;
        try {
            fileWriter = new BufferedWriter(new FileWriter(outputFileLocation));
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
        championIdMappingFile = new File(outputFileLocation);
        if(championIdMappingFile.exists()){
            championIdMappingFile.delete();
        }
    }
}
