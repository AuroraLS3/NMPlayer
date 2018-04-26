/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;

/**
 * This Interface is used by the UI Components to update various contents.
 *
 * @author Risto
 */
public interface Updatable {

    /**
     * Updates the UI Component according to the status of MusicPlayer.
     *
     * @see MusicPlayer
     */
    void update();
}
