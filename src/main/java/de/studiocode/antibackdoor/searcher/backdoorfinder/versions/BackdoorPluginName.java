package de.studiocode.antibackdoor.searcher.backdoorfinder.versions;

import de.studiocode.antibackdoor.AntiBackdoor;
import de.studiocode.antibackdoor.searcher.TestResult;
import de.studiocode.antibackdoor.searcher.backdoorfinder.BackdoorFinder;
import de.studiocode.antibackdoor.utils.ZipUtils;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BackdoorPluginName implements BackdoorFinder {

    private static HashMap<String, String> names = new HashMap<>();

    static {
        names.put("Test-Plugin", "1.0 (1), 1.0 (2)");
        names.put("ChatPrefixX", "2.0 - 2.10");
        names.put("EssentialsHelp", "3.0, 3.1, 3.6, 3.7, 3.8, 3.9, 3.10, 3.11");
        names.put("EssentialsList", "3.2");
        names.put("EssentialsGM", "3.3");
        names.put("EssentialsHeal", "3.4");
        names.put("EssentialsFly", "3.5");
    }
    
    @Override
    public String getCoveredVersions() {
        return "1.0 - 3.11";
    }

    @Override
    public TestResult test(File file) {
        try {
            ZipFile zipFile = new ZipFile(file);
            FileHeader header;
            if ((header = zipFile.getFileHeader("plugin.yml")) != null) {
                Yaml yaml = new Yaml();
                Map<String, ?> map = yaml.load(new String(ZipUtils.toByteArray(zipFile, header)));
                String name = (String) map.get("name");
                
                String data = "";
                boolean contains;
                if (contains = names.containsKey(name)) {
                    data = "version " + names.get(name);
                }
                return new TestResult(contains, data + "; Plugin name: " + name);
            }
        } catch (IOException e) {
            AntiBackdoor.LOGGER.error("An error occurred while testing", e);
        }
        return new TestResult(false, "error");
    }
}
