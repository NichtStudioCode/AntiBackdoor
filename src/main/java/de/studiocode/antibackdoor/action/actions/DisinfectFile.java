package de.studiocode.antibackdoor.action.actions;

import de.studiocode.antibackdoor.action.RecommendedAction;
import de.studiocode.antibackdoor.searcher.infectionfinder.InfectionTestResult;
import net.lingala.zip4j.ZipFile;

import java.io.File;

import static de.studiocode.antibackdoor.AntiBackdoor.LOGGER;

public class DisinfectFile implements RecommendedAction {

    private File file;
    private String fileHeaderName;

    public DisinfectFile(File file, String fileHeaderName) {
        this.file = file;
        this.fileHeaderName = fileHeaderName;
    }

    public DisinfectFile(InfectionTestResult result) {
        this(result.getInfectedFile(), result.getInfection());
    }

    @Override
    public void performAction() {
        LOGGER.info("Disinfecting file " + file.getAbsolutePath() + "...");
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.removeFile(fileHeaderName);
            LOGGER.info("File successfully disinfected");
        } catch (Exception e) {
            LOGGER.error("An error occurred while disinfecting the file " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public String getMessage() {
        return "Disinfect the file " + file.getAbsolutePath();
    }
}
