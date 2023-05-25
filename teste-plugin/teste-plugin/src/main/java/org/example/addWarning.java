package org.example;

import edu.hm.hafner.analysis.IssueBuilder;
import net.minidev.json.parser.ParseException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;


import org.json.JSONTokener;




import java.io.*;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONArray;

@Mojo(name="test", defaultPhase = LifecyclePhase.COMPILE)
public class addWarning extends AbstractMojo {
    private String version="";
    private static FileReader reader;

    public void execute() {

        KeycloakParser parser = new KeycloakParser();

        /*
        try {
            reader = new FileReader("vulnerabilities.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONTokener tokener = new JSONTokener(reader);
        JSONArray root = new JSONArray(tokener);
       Iterator it = root.iterator();
       JSONObject obj;
        produceReport report = new produceReport();
        warning w = new warning();
       while(it.hasNext()){
           obj= (JSONObject) it.next();
           IssueBuilder issueBuilder = new IssueBuilder();
           issueBuilder.setMessage(obj.get("message").toString());
           issueBuilder.setDescription(obj.get("solution").toString());
           //report.add(w.addWarning(obj.get("severity").toString(), obj.get("message").toString(), obj.get("solution").toString(),  obj.get("date").toString()));

       }

*/

        //getRequest g = new getRequest();
        /*vulnerabilityTwo o = new vulnerabilityTwo();
        try {
            o.vulnerabilityTwo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        /*getCVE g = new getCVE();
        try {
            g.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


        /*try {version = g.getVersion();}
        catch (IOException e) { }
        //System.out.println("Versão: "+version);

        if (true){
            //System.out.println("Versão pode ter erros");
            warning w = new warning();
            produceReport report = new produceReport();
            report.add(w.addWarning("severity", "description", "solution",  "category"));
            report.add(w.addWarning("severity2", "description2", "solution2",  "category2"));
            report.add(w.addWarning("severity3", "description3", "solution3",  "category3"));

            getCVE cve = new getCVE();

            try {
               cve.get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(o);

        }*/




    }
}