/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.utilities;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Risto
 */
public class TextUtilsTest {
    
    public TextUtilsTest() {
    }

    @Test
    public void testUppercaseFirst() {
        TextUtils t = new TextUtils();
        assertEquals("Testuppercasefirst", TextUtils.uppercaseFirst("tEStUppercaseFIRST"));
    }
    
}
