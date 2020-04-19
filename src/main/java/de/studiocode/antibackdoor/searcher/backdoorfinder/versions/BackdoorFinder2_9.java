package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.Base64Check;
import de.studiocode.antibackdoor.searcher.check.Check;

public class BackdoorFinder2_9 extends ZipCheckBackdoorFinder {

    private static final Check PLUGIN_YYML_CHECK = new Base64Check("plugin.yyml", false);
    
    public BackdoorFinder2_9() {
        super(PLUGIN_YYML_CHECK);
    }
    
    @Override
    public String getCoveredVersions() {
        return "2.9, 2.10";
    }
}
