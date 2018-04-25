package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads lines of text files.
 *
 * @author Rsl1122
 */
public class FileReader {

    public static List<String> lines(File file) throws IOException {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Stream<String> fileStream = Files.lines(file.toPath(), Charset.forName("UTF-8"))) {
            return fileStream.collect(Collectors.toList());
        }
    }

}