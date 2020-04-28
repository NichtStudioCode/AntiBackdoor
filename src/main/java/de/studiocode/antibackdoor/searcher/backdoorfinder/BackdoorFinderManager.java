package de.studiocode.antibackdoor.searcher.backdoorfinder;

import de.studiocode.antibackdoor.searcher.TestResult;
import de.studiocode.antibackdoor.searcher.backdoorfinder.finder.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.studiocode.antibackdoor.AntiBackdoor.LOGGER;

public class BackdoorFinderManager {

    private List<BackdoorFinder> backdoorFinder = new ArrayList<>();

    public BackdoorFinderManager(boolean pluginNameCheck) {
        addBackdoorFinder(new BackdoorHashComparer(), new JpicFinder(), new PluginYymlFinder(),
                new InfoDataFinder1(), new InfoDataFinder2(), new KotlinModuleFinder(), new LanguageFileFinder());
        if (pluginNameCheck) addBackdoorFinder(new BackdoorPluginName());
    }

    private void addBackdoorFinder(BackdoorFinder... backdoorFinder) {
        Collections.addAll(this.backdoorFinder, backdoorFinder);
    }

    public boolean isFileBackdoor(File file) {
        for (BackdoorFinder finder : backdoorFinder) {
            String name = finder.getClass().getName().substring(finder.getClass().getName().lastIndexOf(".") + 1);
            LOGGER.info("Using " + name + " to test (covered versions: " + finder.getCoveredVersions() + ") ");

            TestResult result = finder.test(file);
            LOGGER.info("Result: " + result.getResult());
            LOGGER.info("Data: " + result.getAdditionalData());
            LOGGER.info("--");

            if (result.getResult()) return true;
        }
        return false;
    }

}
