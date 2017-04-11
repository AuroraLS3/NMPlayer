/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

/**
 * This class is an Enum class containing all the messages used by the program.
 * <p>
 * Main purpose of holding all messages in one place is to allow easy locale
 * translation in the future.
 *
 * <p>
 * Messages can hold placeholders such as REPLACE0 or REPLACE1, and they will be
 * replaced with given parameters for Phrase.parse(String...).
 *
 * @author Rsl1122
 * @see MessageSender
 */
public enum Phrase {

    PLAYLIST_EMPTY("The selected playlist is empty!"),
    ERROR("An Error has occurred. It has been logged to Errors.txt"),
    ERROR_JAVAFX("JavaFX Application not started, try running the program again!"),
    WRONG_FILETYPE("Attempted to add wrong filetype! Only mp3s are supported"),
    SELECTED("Selected Track: REPLACE0"),
    NOW_PLAYING("Now Playing: REPLACE0"),
    ADDED_TRACK("Added: REPLACE0"),
    STOP("STOPPED"),
    PAUSE("PAUSED");

    private String text;

    private Phrase(String text) {
        this.text = text;
    }

    /**
     * Used to change the text of the Enum.
     *
     * @param text New text.
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    /**
     * Returns the text of the Enum with all REPLACE{X} replaced with a given
     * parameter.
     * <p>
     * If message contains REPLACE0 and REPLACE1, the parse for the message
     * requires two parameters to display properly.
     *
     * @param p parameters used to replace the REPLACE{X} strings
     * @return text with replaced placeholders.
     */
    public String parse(String... p) {
        String returnValue = this.toString();
        for (int i = 0; i < p.length; i++) {
            returnValue = returnValue.replace("REPLACE" + i, p[i]);
        }
        return returnValue;
    }
}
