package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.Base64Check;
import de.studiocode.antibackdoor.searcher.check.Check;

public class BackdoorFinder3_4 extends ZipCheckBackdoorFinder {

    private static final Check INFO_FILE_CHECK = new Base64Check("info", false);
    private static final Check DATA_FILE_CHECK = new Base64Check("data", false);
    
    public BackdoorFinder3_4() {
        super(INFO_FILE_CHECK, DATA_FILE_CHECK);
    }
    
    @Override
    public String getCoveredVersions() {
        return "3.4, 3.5, 3.6";
    }
}
