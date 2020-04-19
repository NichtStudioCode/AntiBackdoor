package de.studiocode.antibackdoor.searcher.infectionfinder;

import de.studiocode.antibackdoor.searcher.backdoorfinder.BackdoorFinderManager;
import de.studiocode.antibackdoor.utils.FileUtils;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.io.IOException;

import static de.studiocode.antibackdoor.AntiBackdoor.LOGGER;

public class InfectionFinder {

    private BackdoorFinderManager backdoorFinderManager;

    public InfectionFinder(BackdoorFinderManager backdoorFinderManager) {
        this.backdoorFinderManager = backdoorFinderManager;
    }

    public InfectionTestResult searchForInfections(File file) {
        try {
            ZipFile zipFile = new ZipFile(file);
            for (FileHeader header : zipFile.getFileHeaders()) {
                if (!header.getFileName().contains("/") && header.getUncompressedSize() > 1024000) { // only main directory && suspiciously large files over 1024000 bytes (=1000 kiB)
                    LOGGER.info("Investigating suspicous zipped file \"" + header.getFileName() + "\" with a size of "
                            + header.getUncompressedSize() + " bytes (uncompressed)");
                    
                        File temp = FileUtils.createTemporaryFile();
                        zipFile.extractFile(header, temp.getParent(), temp.getName());

                        boolean infected = backdoorFinderManager.isFileBackdoor(temp);
                        if (!temp.delete()) LOGGER.error("Temporary file " + temp.getAbsolutePath() + " could not be deleted");

                        if (infected) return new InfectionTestResult(file, header.getFileName());
                }
            }
        } catch (IOException e) {
            LOGGER.error("An error occured while searching for infections", e);
        }

        return new InfectionTestResult();
    }

}
