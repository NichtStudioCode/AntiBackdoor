package de.studiocode.antibackdoor;

import de.studiocode.antibackdoor.action.RecommendedAction;
import de.studiocode.antibackdoor.searcher.MainScanner;
import de.studiocode.antibackdoor.userinput.YesNoQuestion;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class AntiBackdoor {

    public static final Logger LOGGER = Logger.getLogger(AntiBackdoor.class);
    private boolean usePluginNameCheck;
    private File serverJar = null;
    private File pluginsFolder = null;

    public static void main(String[] args) throws ParseException {
        new File("logs/").mkdir();
        new AntiBackdoor(args);
    }

    public AntiBackdoor(String[] args) throws ParseException {
        if (parseArgs(args)) runSearch();
    }

    private boolean parseArgs(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help", "Show the help menu");
        options.addOption("serverJar", true, "The path of the server jar file (Spigot / Bukkit)");
        options.addOption("plugins", true, "The path of the plugins/ folder");
        options.addOption("usePluginNameCheck", "Activate the plugin name check (can cause false positives)");

        CommandLine commandLine = new DefaultParser().parse(options, args);

        if (commandLine.hasOption("help")) {
            HelpFormatter help = new HelpFormatter();
            help.printHelp("java -jar AntiBackdoor.jar -serverJar <FILE> -plugins <FOLDER>", options);

            return false;
        } else if (commandLine.hasOption("serverJar") || commandLine.hasOption("plugins")) {
            if (commandLine.hasOption("serverJar")) {
                serverJar = new File(commandLine.getOptionValue("serverJar"));
                if (!serverJar.exists()) {
                    LOGGER.fatal("Specified server jar file does not exist.");
                    return false;
                }
            }

            if (commandLine.hasOption("plugins")) {
                pluginsFolder = new File(commandLine.getOptionValue("plugins"));
                if (!pluginsFolder.exists() && pluginsFolder.isDirectory()) {
                    LOGGER.fatal("Specified plugins/ folder does not exist.");
                    return false;
                }
            }

            usePluginNameCheck = commandLine.hasOption("usePluginNameCheck");
        } else {
            LOGGER.fatal("Nothing to scan provided");
            return false;
        }

        return true;
    }

    private void runSearch() {
        MainScanner searcher = new MainScanner(usePluginNameCheck);

        List<RecommendedAction> recommendedActions = searcher.scanForBackdoor(serverJar, pluginsFolder);

        if (recommendedActions.size() > 0) {
            LOGGER.warn("There are " + recommendedActions.size() + " recommended actions: ");
            recommendedActions.forEach(a -> LOGGER.warn(a.getMessage()));

            if (new YesNoQuestion("Do you want to perform them now? (making a backup is recommended)").askQuestion()) {
                recommendedActions.forEach(RecommendedAction::performAction);
            }
            System.out.println("Goodbye");
        } else {
            LOGGER.info("There are no recommended actions. :)");
        }
    }

}
