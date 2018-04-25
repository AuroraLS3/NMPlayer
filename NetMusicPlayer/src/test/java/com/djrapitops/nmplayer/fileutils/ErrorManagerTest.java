/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
    public void testToLog_IOException() {
        ErrorManager t = new ErrorManager();
        File errors = new File("Errors.txt");
        errors.mkdir();
        ErrorManager.toLog("TestError");
        if (errors.exists()) {
            assertTrue("Made file", errors.isDirectory());
        }
    }
}
