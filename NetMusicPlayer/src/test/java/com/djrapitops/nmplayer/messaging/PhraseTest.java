/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhraseTest {

    @Test
    public void testParse() {
        System.out.println("Test Phrase.parse (Replace method)");
        assertEquals("Selected Track: test", Phrase.SELECTED.parse("test"));
    }
}
