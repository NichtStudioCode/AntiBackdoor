package de.studiocode.antibackdoor.searcher.check.checks;

import java.util.Base64;

public class Base64Check extends BytesCheck {
    
    public Base64Check(String name, boolean ignoreCase) {
        super(name, ignoreCase, bytes -> {
            try {
                Base64.getDecoder().decode(bytes);
                return true;
            } catch (Exception ignored) {
            }
            return false;
        });
    }
    
}
