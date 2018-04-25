/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.messaging.Phrase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ErrorManagerTest {

    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(new File("Errors.txt").toPath());
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(new File("Errors.txt").toPath());
    }

    @Test
    public void testToLog_String_Collection() throws IOException {
        System.out.println("ErrorManager.toLog throwable (collection) test");
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        File errors = new File("Errors.txt");
        String source = "package.TestSource";
        Throwable e = new IllegalArgumentException("Test");
        Throwable e2 = new IllegalStateException("Test2");

        ErrorManager.toLog("Test");
        ErrorManager.toLog(source, Arrays.asList(e, e2));

        List<String> stacktraceE = Arrays.stream(e.getStackTrace()).map(s -> "  " + s).collect(Collectors.toList());

        List<String> errorLines = FileReader.lines(errors);

        assertTrue("Errors.txt doesn't exist", errors.exists());
        assertTrue("Errors.txt is empty-", errorLines.size() > 0);
        assertTrue("First line doesn't contain package.", errorLines.get(1).contains("package.TestSource"));
        assertTrue("First line doesn't contain exception name", errorLines.get(1).contains("IllegalArgumentException"));
        assertTrue("Second line doesn't exist", errorLines.get(2) != null);
        assertTrue("stacktrace not there", errorLines.get(2).contains(stacktraceE.get(0)));
        assertTrue("stacktrace not there", errorLines.get(3).contains(stacktraceE.get(1)));
        assertTrue("Didn't notify user", outContent.toString().contains(Phrase.ERROR + ""));

    }

    @Test
    public void testToLog_IOException() {
        ErrorManager t = new ErrorManager();
        File errors = new File("Errors.txt");
        errors.mkdir();
        ErrorManager.toLog("TestError");
        if (errors.exists()) {
            assertTrue("Made file", errors.isDirectory());
        }
    }

    @Test
    public void testFormatTimeStamp() {
        System.out.println("ErrorManager.formatTimeStamp test");
        long ms = 0L;
        String expResult = "Jan 01 02:00:00";
        String result = ErrorManager.formatTimeStamp(ms);
        assertEquals(expResult, result);
    }

}
