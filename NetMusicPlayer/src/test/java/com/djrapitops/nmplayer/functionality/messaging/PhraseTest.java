/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.messaging;

import com.djrapitops.nmplayer.messaging.Phrase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Risto
 */
public class PhraseTest {
    
    public PhraseTest() {
    }    

    @Test
    public void testSetText() {
        System.out.println("Test Phrase.setText (Locale method)");
        String text = "Test";
        Phrase instance = Phrase.ERROR;
        instance.setText(text);
        assertEquals(Phrase.ERROR.toString(),"Test");
    }
}
