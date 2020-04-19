package de.studiocode.antibackdoor.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class ZipUtils {

    public static byte[] toByteArray(ZipInputStream zin) throws IOException {
        byte[] bytes = IOUtils.toByteArray(zin);
        zin.close();
        return bytes;
    }
    
    public static byte[] toByteArray(ZipFile zipFile, FileHeader fileHeader) throws IOException {
        ZipInputStream zin = zipFile.getInputStream(fileHeader);
        return toByteArray(zin);
    }

}
