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

    public static void main(String[] args) throws ParseException, InterruptedException {
        new File("logs/").mkdir();

        Options options = new Options();
        options.addOption("help", "Show the help menu");
        options.addOption("serverjar", true, "The path of the server jar file (Spigot / Bukkit)");
        options.addOption("plugins", true, "The path of the plugins/ folder");
        options.addOption("usePluginNameCheck", "Activate the plugin name check (can cause false positives)");

        CommandLine cmd = new DefaultParser().parse(options, args);
        
        if (cmd.hasOption("help")) {
            HelpFormatter help = new HelpFormatter();
            help.printHelp("java -jar AntiBackdoor.jar -serverjar <FILE> -plugins <FOLDER>", options);

            return;
        }
        
        if (cmd.hasOption("serverjar") || cmd.hasOption("plugins")) {
            if (!(cmd.hasOption("serverjar") && cmd.hasOption("plugins"))) {
                LOGGER.warn("Only scanning serverjar or only scanning plugins directory (Continuing in 5s...)");
                Thread.sleep(5000);
            }

            MainScanner searcher = new MainScanner(cmd.hasOption("usePluginNameCheck"));

            File serverJar = null;
            File pluginsFolder = null;

            if (cmd.hasOption("serverjar")) {
                serverJar = new File(cmd.getOptionValue("serverjar"));
                if (!serverJar.exists()) LOGGER.fatal("Specified server jar file does not exist.");
            }

            if (cmd.hasOption("plugins")) {
                pluginsFolder = new File(cmd.getOptionValue("plugins"));
                if (!pluginsFolder.exists() && pluginsFolder.isDirectory())
                    LOGGER.fatal("Specified plugins/ folder does not exist.");
            }

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

        } else {
            LOGGER.fatal("Nothing to scan provided");
        }
    }

}
