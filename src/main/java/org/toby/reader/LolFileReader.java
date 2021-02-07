package org.toby.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LolFileReader extends Reader {

    BufferedReader reader;

    public LolFileReader(String filePath ) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.stringResult = "";
    }

    @Override
    public void read()  {
        String currentLine;
        try{
            currentLine = reader.readLine();
            while (currentLine != null) {
                this.stringResult += currentLine.trim();
                currentLine = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
