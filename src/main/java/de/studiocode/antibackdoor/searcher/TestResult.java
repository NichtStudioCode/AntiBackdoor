package de.studiocode.antibackdoor.searcher;

public class TestResult {
    
    private boolean result;
    private String additionalData;

    public TestResult(boolean result, String additionalData) {
        this.result = result;
        this.additionalData = additionalData;
    }

    public boolean getResult() {
        return result;
    }

    public String getAdditionalData() {
        return additionalData;
    }
}
