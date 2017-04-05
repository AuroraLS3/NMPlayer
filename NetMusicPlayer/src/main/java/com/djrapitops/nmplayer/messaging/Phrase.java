/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

/**
 *
 * @author Risto
 */
public enum Phrase {
    PLAYLIST_EMPTY("The selected playlist is empty!"),
    ERROR("An Error has occurred. It has been logged to Errors.txt"),
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

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String parse(String... p) {
        String returnValue = this.toString();
        for (int i = 0; i < p.length; i++) {
            returnValue = returnValue.replace("REPLACE" + i, p[i]);
        }
        return returnValue;
    }
}
