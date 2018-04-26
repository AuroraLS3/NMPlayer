package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility for reading and writing to text files.
 *
 * @author Rsl1122
 */
public class FileUtility {

    private FileUtility() {
        /* Hide constructor. */
    }

    public static List<String> lines(File file) throws IOException {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Stream<String> fileStream = Files.lines(file.toPath(), Charset.forName("UTF-8"))) {
            return fileStream.collect(Collectors.toList());
        }
    }

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