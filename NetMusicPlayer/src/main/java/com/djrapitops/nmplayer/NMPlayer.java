/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.UserInterface;

/**
 *
 * @author Risto
 */
public class NMPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MusicPlayer mp = MusicPlayer.getInstance();
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(UserInterface.class);
            }
        }.start();
        mp.init();
    }
    
}
