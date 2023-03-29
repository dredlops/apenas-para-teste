package org.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
@Mojo(name="getVersion", defaultPhase = LifecyclePhase.INITIALIZE)
public class getRequest extends AbstractMojo {
    private String URL_TOKEN = "http://localhost:8080/realms/master/protocol/openid-connect/token";
    private String URL_VERSION = "http://localhost:8080/admin/serverinfo";


    /**
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        try {
            String token = getToken();
            getVersion(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private String getToken() throws IOException {
        URL url = new URL(URL_TOKEN);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        String jsonInputString = "username=testuser&password=test&grant_type=password&client_id=admin-rest-client";

        OutputStream os = conn.getOutputStream();
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
        os.flush();
        os.close();

        String response = getResponse(conn);
        JSONObject obj = new JSONObject(response);
        String token = obj.getString("access_token");
        //System.out.println("Token: " + token);
        return token;
    }

    private String getVersion(String token) throws IOException {
        URL url = new URL(URL_VERSION);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        String response = getResponse(conn);
        //System.out.println(response);
        JSONObject json = new JSONObject(response);
        String version = json.getJSONObject("systemInfo").getString("version");
        System.out.println("Versão: "+version);

        return version;
    }

    private String getResponse(HttpURLConnection conn){
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
