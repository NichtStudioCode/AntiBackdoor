package de.studiocode.antibackdoor.action.actions;

import de.studiocode.antibackdoor.action.RecommendedAction;

import java.io.File;

import static de.studiocode.antibackdoor.AntiBackdoor.LOGGER;

public class DeleteFile implements RecommendedAction {
    
    private File file;
    
    public DeleteFile(File file) {
        this.file = file;
    }
    
    @Override
    public void performAction() {
        LOGGER.info("Deleting file " + file.getAbsolutePath() + "...");
        if (!file.delete()) {
            LOGGER.error("The file" + file.getAbsolutePath() + " could not be deleted");
        } else {
            LOGGER.info("File successfully deleted");
        }
    }

    @Override
    public String getMessage() {
        return "Delete the file " + file.getAbsolutePath();
    }
}
