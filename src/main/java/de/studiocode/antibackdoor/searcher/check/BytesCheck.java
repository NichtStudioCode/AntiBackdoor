package de.studiocode.antibackdoor.searcher.check;

import de.studiocode.antibackdoor.utils.ZipUtils;

import java.io.IOException;
import java.util.function.Predicate;

public class BytesCheck extends Check {
    
    public BytesCheck(String name, boolean ignoreCase, Predicate<byte[]> contentCheck) {
        super(name, ignoreCase, zin -> {
            try {
                return contentCheck.test(ZipUtils.toByteArray(zin));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
    
}
