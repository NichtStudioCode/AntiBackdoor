package de.studiocode.antibackdoor.searcher.backdoorfinder;

import de.studiocode.antibackdoor.AntiBackdoor;
import de.studiocode.antibackdoor.searcher.TestResult;
import de.studiocode.antibackdoor.searcher.ZipCheckTester;
import de.studiocode.antibackdoor.searcher.check.Check;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.util.HashMap;

public abstract class ZipCheckBackdoorFinder extends ZipCheckTester implements BackdoorFinder {

    public ZipCheckBackdoorFinder(Check... checks) {
        super(checks);
    }

    @Override
    public TestResult test(File file) {
        try {
            ZipFile zipFile = new ZipFile(file);
            HashMap<Check, FileHeader> passedChecks = testChecks(zipFile);
            StringBuilder data = new StringBuilder((passedChecks.size() > 0 ? getCoveredVersions() : "No checks passed") + "; ");
            if (passedChecks.size() > 0) {
                data.append("passed ").append(passedChecks.size()).append("/").append(checks.length).append(" checks (");
                for (Check check : passedChecks.keySet()) {
                    data.append(passedChecks.get(check).getFileName()).append(", ");
                }
                data.append(")");
            }
            return new TestResult(passedChecks.size() > 0, data.toString());
        } catch (ZipException e) {
            AntiBackdoor.LOGGER.error("An error occurred", e);
            e.printStackTrace();
        }
        return null;
    }
}
