package org.example;

import hudson.Extension;
//import io.jenkins.plugins.analysis.core.model.ReportScanningTool;
import org.kohsuke.stapler.DataBoundConstructor;
import io.jenkins.plugins.analysis.core.model.*;
import javax.annotation.Nonnull;



public class KeycloakVerifier extends ReportScanningTool {
    private static final long serialVersionUID = 1L;
    static final String ID = "KeycloakVerifierID";
    @DataBoundConstructor
    public KeycloakVerifier(){
        super();
    }

    @Override
    public KeycloakReport createParser() {
        return new KeycloakReport();
    }


    @Extension
    public static class Descriptor extends ReportScanningToolDescriptor {

        public Descriptor() {
            super(ID);
        }
        @Nonnull
        @Override
        public String getDisplayName() {
            return "KeycloakParser";
        }
    }


}





