package org.example;

import org.json.JSONObject;

public class warning {

    /*
    severity will be mapped as severity
    description will be mapped as message
    solution will be mapped as description
    category will be mapped as category
     */
    public void warning(){}

    public String addWarning(String severity, String description, String solution, String category){

        String s = "{{"+severity+"}{"+description+"}{"+solution+"}{"+category+"}}";


        return s;
    }
}