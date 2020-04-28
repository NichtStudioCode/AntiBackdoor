package de.studiocode.antibackdoor.searcher.backdoorfinder.finder;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.checks.Base64Check;
import de.studiocode.antibackdoor.searcher.check.Check;

public class PluginYymlFinder extends ZipCheckBackdoorFinder {

    private static final Check PLUGIN_YYML_CHECK = new Base64Check("plugin.yyml", false);
    
    public PluginYymlFinder() {
        super(PLUGIN_YYML_CHECK);
    }
    
    @Override
    public String getCoveredVersions() {
        return "2.9, 2.10";
    }
}
