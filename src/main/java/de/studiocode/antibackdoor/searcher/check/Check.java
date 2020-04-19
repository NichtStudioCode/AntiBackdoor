package de.studiocode.antibackdoor.searcher.check;

import net.lingala.zip4j.io.inputstream.ZipInputStream;

import java.util.function.Predicate;

public class Check {
    
    private String name;
    private boolean ignoreCase;
    private Predicate<ZipInputStream> contentCheck;

    public Check(String name, boolean ignoreCase, Predicate<ZipInputStream> contentCheck) {
        this.name = name;
        this.ignoreCase = ignoreCase;
        this.contentCheck = contentCheck;
    }
    
    public boolean isName(String name) {
        return ignoreCase ? this.name.equalsIgnoreCase(name) : this.name.equals(name);
    }
    
    public boolean test(ZipInputStream zin) {
        return contentCheck.test(zin);
    }
}
