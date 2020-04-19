package de.studiocode.antibackdoor.searcher.infectionfinder;

import java.io.File;

public class InfectionTestResult {

    private boolean infected;
    private File infectedFile;
    private String infection;

    protected InfectionTestResult() {
        this.infected = false;
    }
    
    public InfectionTestResult(File infectedFile, String infection) {
        this.infected = true;
        this.infectedFile = infectedFile;
        this.infection = infection;
    }

    public boolean isInfected() {
        return infected;
    }

    public File getInfectedFile() {
        return infectedFile;
    }

    public String getInfection() {
        return infection;
    }
}
