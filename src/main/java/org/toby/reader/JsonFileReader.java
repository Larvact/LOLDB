package org.toby.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader implements Reader{

    BufferedReader reader;
    String jsonResult;

    public JsonFileReader(String filePath ) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        jsonResult = "";
    }

    @Override
    public void read()  {
        String currentLine;
        try{
            currentLine = reader.readLine();
            while (currentLine != null) {
                System.out.println(currentLine);
                this.jsonResult += currentLine;
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
