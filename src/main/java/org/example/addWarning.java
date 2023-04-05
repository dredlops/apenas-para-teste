package org.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.JSONObject;


@Mojo(name="test", defaultPhase = LifecyclePhase.COMPILE)
public class addWarning extends AbstractMojo {
    private String version="";
    public void execute() {
        getRequest g = new getRequest();
        try {version = g.getVersion();}
        catch (IOException e) { };
        System.out.println("Versão: "+version);
        if (version.equals("21.0.1") || true){
            //System.out.println("Versão pode ter erros");
            warning w = new warning();
            produceReport report = new produceReport();
            report.add(w.addWarning("severity", "description", "solution",  "category"));
            report.add(w.addWarning("severity2", "description2", "solution2",  "category2"));
            report.add(w.addWarning("severity3", "description3", "solution3",  "category3"));


            //System.out.println(o);

        }

    }
}