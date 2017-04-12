/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.utilities;

/**
 *
 * @author Risto
 */
public class TextUtils {

    public static String uppercaseFirst(String string) {
        return "" + string.toUpperCase().charAt(0) + string.toLowerCase().subSequence(1, string.length());
    }
}
