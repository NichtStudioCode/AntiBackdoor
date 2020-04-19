package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.Base64Check;
import de.studiocode.antibackdoor.searcher.check.BytesCheck;
import de.studiocode.antibackdoor.searcher.check.Check;
import de.studiocode.antibackdoor.utils.StringUtils;

import java.util.function.Predicate;

public class BackdoorFinder3_0 extends ZipCheckBackdoorFinder {

    private static final Predicate<byte[]> IS_WEIRD_ENCRYPTION = bytes -> {
        String content = new String(bytes);
        return StringUtils.containsOnly("0123456789.;", content);
    };

    private static final Check INFO_FILE_CHECK = new Base64Check("info", false);
    private static final Check DATA_FILE_CHECK = new BytesCheck("data", false, IS_WEIRD_ENCRYPTION);

    public BackdoorFinder3_0() {
        super(INFO_FILE_CHECK, DATA_FILE_CHECK);
    }

    @Override
    public String getCoveredVersions() {
        return "3.0 - 3.3";
    }
}
