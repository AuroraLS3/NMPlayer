/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Risto
 */
public class ErrorManagerTest {

    public ErrorManagerTest() {
    }    

    @Test
    public void testToLog_String_Collection() throws IOException {
        System.out.println("ErrorManager.toLog throwable (collection) test");
        Files.deleteIfExists(new File("Errors.txt").toPath());
        File errors = new File("Errors.txt");
        String source = "package.TestSource";
        Throwable e = new IllegalArgumentException("Test");
        Throwable e2 = new IllegalStateException("Test2");
        ErrorManager.toLog(source, Arrays.asList(new Throwable[]{e, e2}));
        List<String> errorlines = new ArrayList<>();
        Scanner s = new Scanner(errors);
        while(s.hasNextLine()) {
            errorlines.add(s.nextLine());
        }
//        List<String> errorlines = Files.lines(errors.toPath()).collect(Collectors.toList());
        assertTrue("Errors.txt is empty-", errorlines.size() > 0);
        assertTrue("First line doesn't contain package.", errorlines.get(0).contains("package.TestSource"));
        assertTrue("First line doesn't contain exception name", errorlines.get(0).contains("IllegalArgumentException"));
        assertTrue("Second line doesn't exist", errorlines.get(1) != null);
        Files.deleteIfExists(new File("Errors.txt").toPath());
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
