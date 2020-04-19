package de.studiocode.antibackdoor.searcher;

import de.studiocode.antibackdoor.searcher.check.Check;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public abstract class ZipCheckTester {

    protected Check[] checks;

    public ZipCheckTester(Check... checks) {
        this.checks = checks;
    }

    public HashMap<Check, FileHeader> testChecks(ZipFile zipFile) throws ZipException {
        HashMap<Check, FileHeader> passedChecks = new HashMap<>();
        zipFile.getFileHeaders().forEach(fileHeader -> {
            String fileName = fileHeader.getFileName();
            Check check = Arrays.stream(checks).filter(c -> c.isName(fileName)).findFirst().orElse(null);
            if (check != null) {
                try {
                    ZipInputStream zin = zipFile.getInputStream(fileHeader);
                    if (check.test(zin)) passedChecks.put(check, fileHeader);
                    zin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        
        return passedChecks;
    }

}
