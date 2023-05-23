package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

public class getCVE {
    private static final String URL_CVE = "https://api.cvesearch.com/search?q=keycloak";
    private Iterator cves;
    private Response resp;

    public getCVE() {
        resp=new Response();
    }

    public String get() throws IOException {
        URL url = new URL(URL_CVE);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //conn.setConnectTimeout(120000);
        String response =resp.getResponse(conn);
        JSONObject json = new JSONObject(response);
        JSONObject cvesJson = json.getJSONObject("response");
        cves = cvesJson.keys();
        String[] temp = getAffectedVersions(cvesJson);
        Iterator it= Arrays.stream(temp).iterator();
        while (it.hasNext())
            System.out.println(it.next());
        return temp.toString();
    }

    private String[] getAffectedVersions(JSONObject response) {
        String cve;
        Iterator temp;
        String product = "";
        String [] versions = new String[100];
        int counter=0;

        while (cves.hasNext()) {
            cve = cves.next().toString();
            temp = response.getJSONObject(cve).getJSONArray("affected_products").iterator();
            while (temp.hasNext()) {
                product = temp.next().toString();
                String[] a = product.split(":");
                String producer = a[a.length - 3];
                String prod = a[a.length - 2];
                String vers = a[a.length - 1];

                //System.out.println(product);
                if (producer.equals("redhat") || producer.equals("keycloak")) {
                    if(prod.equals("keycloak"))
                        versions[counter++]=product;
                }
            }
        }
        return versions;
    }

    /*private String getResponse(HttpURLConnection conn) {
        BufferedReader in;
        String output;
        int counter =0;

        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
                return "";
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
    }*/

}