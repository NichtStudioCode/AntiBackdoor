package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.AntiBackdoor;
import de.studiocode.antibackdoor.searcher.TestResult;
import de.studiocode.antibackdoor.searcher.backdoorfinder.BackdoorFinder;
import de.studiocode.antibackdoor.utils.HashUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class BackdoorHashComparer implements BackdoorFinder {
    
    private static HashMap<String, String> hashes = new HashMap<>();
    
    static {
        hashes.put("AE670E3A02D5F924D3943D748FB81037", "1.0 (1)");
        hashes.put("2184BF510BD0BCD1264191D60D6A9FF4", "1.0 (2)");

        hashes.put("381E03B3B642D0DA28C4349BED9DDE39", "2.0");
        hashes.put("C720291B193AE96E120953B6B1095D14", "2.1");
        hashes.put("CD8649E6430D8A2790E754BCB25E908D", "2.2");
        hashes.put("AD7C31EA0D5AF25DCA26271DD0BFEF19", "2.3");
        hashes.put("4EC3A3AD79B8DA8D7E690B0B6639CD60", "2.4");
        hashes.put("1A1F9BE32F258CA11DCF59D4EBB75FC1", "2.5");
        hashes.put("13EE4D58556D6040AD6B0314400D1D24", "2.6");
        hashes.put("D0340101B5956B9EAC79A037D0817102", "2.7");
        hashes.put("504B333AD58E5B8E9700F862C747B68B", "2.8");
        hashes.put("30997CF98E5C999751B0C326DCCFAD91", "2.9");
        hashes.put("1741B093D695A1FAF588C4A0D773C339", "2.10");
        
        hashes.put("DEF7BB41E3BFA67A9F583CB4E443363B", "3.7");
        hashes.put("3480D38FDB8853F5F9AA109699745B08", "3.8");
        hashes.put("240022AC4FCBEBC8318143674FFA2D3B", "3.8.1");
        hashes.put("DCD99BF889D25CE01F190684BD7F6A6E", "3.9");
        hashes.put("6E64110F058BCAAEB14493784904EF27", "3.10");
    }
    
    @Override
    public String getCoveredVersions() {
        return "1.0; 2.0 - 2.10; 3.7 - 3.10";
    }

    @Override
    public TestResult test(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            String hashText = HashUtils.createMD5HashText(in);
            in.close();
            
            boolean contains = hashes.containsKey(hashText);
            String data = contains ? hashes.get(hashText) : "Not in hash database";
            
            return new TestResult(contains, data + "; File hash: " + hashText);
        } catch (IOException e) {
            AntiBackdoor.LOGGER.error("An error occurred while checking the hash", e);
        }
        return null;
    }

}
