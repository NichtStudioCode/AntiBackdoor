package de.studiocode.antibackdoor.searcher;

import de.studiocode.antibackdoor.action.RecommendedAction;
import de.studiocode.antibackdoor.action.actions.DeleteFile;
import de.studiocode.antibackdoor.action.actions.DisinfectFile;
import de.studiocode.antibackdoor.searcher.backdoorfinder.BackdoorFinderManager;
import de.studiocode.antibackdoor.searcher.infectionfinder.InfectionFinder;
import de.studiocode.antibackdoor.searcher.infectionfinder.InfectionTestResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.studiocode.antibackdoor.AntiBackdoor.LOGGER;

public class MainScanner {

    private BackdoorFinderManager backdoorFinderManager;
    private InfectionFinder infectionFinder;

    public MainScanner(boolean pluginNameCheck) {
        backdoorFinderManager = new BackdoorFinderManager(pluginNameCheck);
        infectionFinder = new InfectionFinder(backdoorFinderManager);
    }

    public List<RecommendedAction> scanForBackdoor(File serverJar, File pluginsDir) {
        List<RecommendedAction> recommendedActions = new ArrayList<>();
        
        if (serverJar != null) {
            InfectionTestResult result = infectionFinder.searchForInfections(serverJar);
            if (result.isInfected()) {
                LOGGER.warn("The server jar is infected with Backdoor!");
                recommendedActions.add(new DisinfectFile(result));
            }
        }
        
        if (pluginsDir != null) {
            Arrays.stream(pluginsDir.listFiles())
                    .filter(file -> !file.isDirectory() && file.getName().endsWith(".jar"))
                    .forEach(file -> {
                        try {
                            //check if file is backdoor
                            LOGGER.info("----Now scanning " + file.getName() + "----");
                            if (backdoorFinderManager.isFileBackdoor(file)) {
                                LOGGER.warn(file.getAbsolutePath() + " is Backdoor!");
                                recommendedActions.add(new DeleteFile(file));
                            } else {
                                LOGGER.info("----Now scanning " + file.getName() + " for infections----");
                                InfectionTestResult result = infectionFinder.searchForInfections(file);
                                if (result.isInfected()) {
                                    LOGGER.warn("The plugin " + file.getName() + " is infected with Backdoor!");
                                    recommendedActions.add(new DisinfectFile(result));
                                } else {
                                    LOGGER.info("No infections found.");
                                }
                            }
                        } catch (Exception e) {
                            LOGGER.error("An error occurred while scanning the file " + file.getAbsolutePath(), e);
                        }
                    });
        }
        return recommendedActions;
    }
}
