package de.studiocode.antibackdoor.searcher.backdoorfinder;

import de.studiocode.antibackdoor.searcher.TestResult;

import java.io.File;

public interface BackdoorFinder {
    
    String getCoveredVersions();
    TestResult test(File file);
    
}
