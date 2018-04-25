/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

import com.djrapitops.nmplayer.ui.TextConsole;
import com.sun.javafx.application.PlatformImpl;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Rsl1122
 */
public class MessageSenderTest {

    /**
     *
     */
    public MessageSenderTest() {
    }

    /**
     *
     */
    @Test
    public void testSend() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String exp = "Testmessage";
        MessageSender.getInstance().send(exp);
        assertTrue("Didn't contain test message", outContent.toString().contains(exp));
    }

    /**
     *
     */
    @Test
    public void testGetInstance() {
        assertTrue("Was null", MessageSender.getInstance() != null);
    }

    @Test
    public void testSetOutput() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String exp = "Testmessage";
        PlatformImpl.startup(() -> {
        });
        TextConsole textConsole = new TextConsole();
        MessageSender m = MessageSender.getInstance();
        m.setOutput(textConsole);
        m.send(exp);
        assertTrue("Sys out included test message", !outContent.toString().contains(exp));
        assertTrue("Console Didn't contain test message", textConsole.getText().contains(exp));
        assertTrue(textConsole.getScrollTop() == Double.MAX_VALUE);
        final String exp2 = "Testmessage2";
        m.send(exp2);
        assertTrue("Console Didn't contain test message", textConsole.getText().contains(exp2));
//        assertTrue("Console Didn't contain a line separator", textConsole.getText().contains(System.getProperty("line.separator")));
    }
}
