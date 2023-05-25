package org.example;
import java.io.*;
import java.util.Iterator;

import org.json.JSONArray;

public class KeycloakParser {
    private final String FILE = "vulnerabilities.json";

    public KeycloakParser() {
    }

    //reads a json file and returns an iterator with a JSONObject for each vulnerability found
    public Iterator parse() throws IOException{

        FileReader f = new FileReader(FILE);
        BufferedReader in = new BufferedReader(f);;
        JSONArray array = new JSONArray();
        String output;
        StringBuffer response = new StringBuffer();

        while (true) {
            try {
                if ((output = in.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(output);
        }
        JSONArray jsonArray = new JSONArray(response.toString());
        return jsonArray.iterator();
    }
}