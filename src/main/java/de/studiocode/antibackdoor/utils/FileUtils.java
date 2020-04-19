package de.studiocode.antibackdoor.utils;

import java.io.File;

public class FileUtils {
    
    public static File createTemporaryFile() {
        File temp;
        do {
            temp = new File(StringUtils.generateRandomString(10) + ".tmp");
        } while (temp.exists());
        return new File(temp.getAbsolutePath()); // otherwise there will be no parent in the path resulting in getParentFile() == null
    }
    
}
