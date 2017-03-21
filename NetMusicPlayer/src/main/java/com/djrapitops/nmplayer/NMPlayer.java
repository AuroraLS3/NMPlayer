/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer;

import com.djrapitops.nmplayer.functionality.MusicPlayer;

/**
 *
 * @author Risto
 */
public class NMPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MusicPlayer mp = new MusicPlayer();
        mp.init();
    }
    
}
