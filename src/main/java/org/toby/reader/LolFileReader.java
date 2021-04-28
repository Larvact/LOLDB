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
            this.readData = new StringBuilder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read()  {
        String currentLine;
        try{
            currentLine = reader.readLine();
            while (currentLine != null) {
                this.readData.append(currentLine.trim());
                currentLine = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
