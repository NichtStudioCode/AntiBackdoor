package de.studiocode.antibackdoor.action.actions;

import de.studiocode.antibackdoor.action.RecommendedAction;
import de.studiocode.antibackdoor.searcher.infectionfinder.InfectionTestResult;
import net.lingala.zip4j.ZipFile;

import java.io.File;

import static de.studiocode.antibackdoor.AntiBackdoor.LOGGER;

public class DesinfectFile implements RecommendedAction {

    private File file;
    private String fileHeaderName;

    public DesinfectFile(File file, String fileHeaderName) {
        this.file = file;
        this.fileHeaderName = fileHeaderName;
    }

    public DesinfectFile(InfectionTestResult result) {
        this(result.getInfectedFile(), result.getInfection());
    }

    @Override
    public void performAction() {
        LOGGER.info("Desinfecting file " + file.getAbsolutePath() + "...");
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.removeFile(fileHeaderName);
            LOGGER.info("File successfully desinfected");
        } catch (Exception e) {
            LOGGER.error("An error occured while desinfecting the file " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public String getMessage() {
        return "Desinfect the file " + file.getAbsolutePath();
    }
}
