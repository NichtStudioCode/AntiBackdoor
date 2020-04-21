package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.searcher.backdoorfinder.ZipCheckBackdoorFinder;
import de.studiocode.antibackdoor.searcher.check.BytesCheck;
import de.studiocode.antibackdoor.searcher.check.Check;
import de.studiocode.antibackdoor.utils.CryptoUtils;

import java.security.Key;
import java.util.function.Predicate;

public class LanguageFileFinder extends ZipCheckBackdoorFinder {

    private static final Key KEY = CryptoUtils.getAESKey("vDHTIslRrnpQGeAN"); // the hardcoded key used to encrypt the language file
    private static final Predicate<byte[]> TRY_DECRYPT = bytes -> CryptoUtils.tryDecryptAES(bytes, KEY);
    private static final Check LANGUAGE_FILE_GERMAN_CHECK = new BytesCheck("ldede.class", false, TRY_DECRYPT);
    private static final Check LANGUAGE_FILE_ENGLISH_CHECK = new BytesCheck("lenus.class", false, TRY_DECRYPT);

    public LanguageFileFinder() {
        super(LANGUAGE_FILE_GERMAN_CHECK, LANGUAGE_FILE_ENGLISH_CHECK);
    }
    
    @Override
    public String getCoveredVersions() {
        return "3.11";
    }
}
