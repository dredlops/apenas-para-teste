package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class getCVE {
    private static final String URL_CVE = "https://api.cvesearch.com/search?q=Keycloak";

    public void getCVE(){}
    public String get() throws IOException {
        URL url = new URL(URL_CVE);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        String response = getResponse(conn);
        //System.out.println(response);
        //JSONObject temp = new JSONObject(response);
        JSONObject json = new JSONObject(response);
        JSONArray temp = new JSONArray();
        //String cve =
        JSONObject cves = json.getJSONObject("response");
        Iterator it = cves.keys();
        //cria um vetor com as keys dos cves
        while(it.hasNext()){
            temp.put(it.next());
        }

        return temp.getString(0);
    }

    private String getResponse(HttpURLConnection conn) {
        BufferedReader in;
        String output;

        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuffer response = new StringBuffer();

        while (true) {
            try {
                if ((output = in.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(output);
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.toString();
    }
}
