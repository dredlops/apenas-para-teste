package org.example;

import netscape.javascript.JSObject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

/**
 * An example Maven Mojo that resolves the current project's git revision and adds that a new {@code exampleVersion}
 * property to the current, Maven project.
 */
@Mojo(name = "version", defaultPhase = LifecyclePhase.INITIALIZE)
public class GetVersionMojo extends AbstractMojo {

    @Parameter(property = "name.to.call", defaultValue = "git rev-parse --short HEAD")
    private String command;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        String version = null;
        try {
            version = getVersion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        project.getProperties().put("exampleVersion", version);
        getLog().info("Keycloak Version: "+version);
    }

    public String getVersion() throws MojoExecutionException, IOException {
        StringBuilder builder = new StringBuilder();

        URL url = new URL("http://localhost:8080/admin/serverinfo/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            //System.out.println(con.getOutputStream());

            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) con.getContent()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            //JSObject json =  new JSONObject(url.getContent());
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

        //Process process = Runtime.getRuntime().exec(command);
        //Executors.newSingleThreadExecutor().submit(() -> new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(builder::append));

        int exitCode = 0;
        // process.waitFor();

        if(exitCode!=0){
            throw new MojoExecutionException("Execution of command'" +command+ "'failed with exit code: " + exitCode);
        }
        return builder.toString();
    }
}
