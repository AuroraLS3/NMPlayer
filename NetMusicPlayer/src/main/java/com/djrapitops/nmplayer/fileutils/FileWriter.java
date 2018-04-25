package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Appends lines to text files.
 *
 * @author Rsl1122
 */
public class FileWriter {

    public static boolean writeFile(List<String> toWrite, File file) {
        try {
            Files.write(file.toPath(), toWrite, Charset.forName("UTF-8"));
            return true;
        } catch (IOException e) {
            Logger.getGlobal().log(Level.WARNING, "Failed to write to " + file.getName(), e);
            return false;
        }
    }

    public static boolean appendToFile(List<String> toWrite, File file) {
        try {
            if (!file.exists() && !file.createNewFile()) {
                throw new IllegalStateException(file.getName() + " could not be created");
            }
            Files.write(file.toPath(), toWrite, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            Logger.getGlobal().log(Level.WARNING, "Failed to write to " + file.getName(), e);
            return false;
        }
    }

}