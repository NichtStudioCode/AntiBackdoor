package de.studiocode.antibackdoor.searcher.backdoorfinder.finder;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.checks.MD5HashCheck;

public class KotlinModuleFinder extends ZipCheckBackdoorFinder {

    private static final MD5HashCheck KOTLIN_MODULE_CHECK = new MD5HashCheck("META-INF/Backdoor.kotlin_module", false, "3C4A1AF86E603817E944911888D287E5");

    public KotlinModuleFinder() {
        super(KOTLIN_MODULE_CHECK);
    }

    @Override
    public String getCoveredVersions() {
        return "3.7";
    }
    
}
