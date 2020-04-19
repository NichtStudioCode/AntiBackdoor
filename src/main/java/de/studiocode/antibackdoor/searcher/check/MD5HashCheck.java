package de.studiocode.antibackdoor.searcher.check;

import de.studiocode.antibackdoor.AntiBackdoor;
import de.studiocode.antibackdoor.utils.HashUtils;
import net.lingala.zip4j.io.inputstream.ZipInputStream;

import java.io.IOException;
import java.util.function.Predicate;

public class MD5HashCheck extends Check {
    
    public MD5HashCheck(String name, boolean ignoreCase, String hashText) {
        super(name, ignoreCase, createHashCheckPredicate(hashText));
    }
    
    private static Predicate<ZipInputStream> createHashCheckPredicate(String hashText) {
        return zin -> {
            try {
                boolean doesEqual = HashUtils.createMD5HashText(zin).equals(hashText);
                zin.close();
                return doesEqual;
            } catch (IOException e) {
                AntiBackdoor.LOGGER.error("An error occured while checking the hash", e);
            }
            return false;
        };
    }
    
}
