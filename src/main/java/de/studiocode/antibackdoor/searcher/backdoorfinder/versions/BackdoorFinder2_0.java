package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.MD5HashCheck;

public class BackdoorFinder2_0 extends ZipCheckBackdoorFinder {
    
    private static final MD5HashCheck JPIC_CHECK = new MD5HashCheck("jpic", false, "4F459A0CE7095AF0BB4CC28C46DD111B");
    
    public BackdoorFinder2_0() {
        super(JPIC_CHECK);
    }
    
    @Override
    public String getCoveredVersions() {
        return "2.0 - 2.6";
    }
}
