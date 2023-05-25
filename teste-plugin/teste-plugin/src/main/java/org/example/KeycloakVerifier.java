package org.example;

import edu.hm.hafner.analysis.IssueParser;
import io.jenkins.plugins.analysis.core.model.ReportScanningTool;



public class KeycloakVerifier extends ReportScanningTool {
    private static final long serialVersionUID = 1L;
    static final String ID = "KeycloakVerifierID";
    @Override
    public IssueParser createParser() {
        return new KeycloakReport();
    }




}
