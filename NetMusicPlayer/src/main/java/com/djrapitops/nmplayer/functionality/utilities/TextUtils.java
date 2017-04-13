package com.djrapitops.nmplayer.functionality.utilities;

/**
 * This class contains various static methods to format String objects.
 *
 * @author Risto
 */
public class TextUtils {

    /**
     * Formats a string so that the first letter is upper-case and rest is
     * lowercase.
     *
     * @param string For example "tEsTthiS
     * @return "Testthis"
     */
    public static String uppercaseFirst(String string) {
        return "" + string.toUpperCase().charAt(0) + string.toLowerCase().subSequence(1, string.length());
    }

    /**
     * Removes the .wav, .mp3 or .txt extension from a string.
     *
     * @param s for example "Trackfile.mp3"
     * @return "Trackfile"
     */
    public static String removeExtension(String s) {
        return s.replace(".wav", "").replace(".mp3", "").replace(".txt", "");
    }
}
