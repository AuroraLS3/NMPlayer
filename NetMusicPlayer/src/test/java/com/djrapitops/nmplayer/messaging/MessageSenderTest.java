/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ristolah
 */
public class MessageSenderTest {

    public MessageSenderTest() {
    }

    @Test
    public void testSend() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 
        System.setOut(new PrintStream(outContent));
        final String exp = "Testmessage";
        MessageSender.getInstance().send(exp);
        assertTrue("Didn't contain test message",outContent.toString().contains(exp));
    }

    @Test
    public void testGetInstance() {
        assertTrue("Was null", MessageSender.getInstance() != null);
    }
}
