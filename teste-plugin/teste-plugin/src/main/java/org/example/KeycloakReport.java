package org.example;

import edu.hm.hafner.analysis.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class KeycloakReport extends IssueParser {
    @Override
    public Report parse(ReaderFactory readerFactory) throws ParsingException, ParsingCanceledException {
        KeycloakParser parser = new KeycloakParser();

        try {
            return produceReport(parser.parse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Report produceReport(Iterator iterator){
        Report report = new Report();
        IssueBuilder builder = new IssueBuilder();
        JSONObject obj;
        while (iterator.hasNext()){
            obj= (JSONObject) iterator.next();
            builder.setSeverity(getSeverity(obj.get("severity").toString()));
            builder.setDescription(obj.get("message").toString());
            builder.setMessage(obj.get("solution").toString());
            JSONObject jsonObject = new JSONObject("{\"cve\": \""+obj.get("cve").toString()+"\", \"date\": \""+obj.get("date").toString()+"\"}");
            builder.setAdditionalProperties(jsonObject.toString());
            report.add(builder.buildAndClean());
        }
        return report;
    }

    private static Severity getSeverity(final String severity) {
        int nmr1 = Integer.parseInt(severity.split(".")[0]);
        int nmr2 = Integer.parseInt(severity.split(".")[1]);
        if (nmr1>=3 && nmr2>=5){
            if(nmr1>=6 && nmr2>=5)
                return Severity.WARNING_HIGH;
            else
                return Severity.WARNING_NORMAL;
        }else return Severity.WARNING_LOW;
    }


}
