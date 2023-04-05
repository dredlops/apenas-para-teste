package org.example;

import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.FileWriter;

public class produceReport {

    //JSONArray array = Json.createArrayBuilder();
    private static String[] array = new String[20];
    private static FileWriter writer;
    private static int counter;

    public void produceReport() {
        counter = 0;
    }

    public void add(String obj) {
        array[counter++] = obj;
        //array.add(obj);
        //array.put(obj);
        //JsonWriter writer = Json.createWriter(new FileOutputStream("/temp/output.json"));
        try {
            writer = new FileWriter("output.txt");
            for ( int i = 0;i < counter;i++)
                writer.write(array[i]);


            //writer.write(array.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}