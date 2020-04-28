package de.studiocode.antibackdoor.searcher.backdoorfinder.finder;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.checks.MD5HashCheck;

public class JpicFinder extends ZipCheckBackdoorFinder {
    
    private static final MD5HashCheck JPIC_CHECK = new MD5HashCheck("jpic", false, "4F459A0CE7095AF0BB4CC28C46DD111B");
    
    public JpicFinder() {
        super(JPIC_CHECK);
    }
    
    @Override
    public String getCoveredVersions() {
        return "2.0 - 2.6";
    }
}
