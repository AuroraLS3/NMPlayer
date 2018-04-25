package com.djrapitops.nmplayer;

import com.djrapitops.nmplayer.ui.UserInterface;

/**
 * This is the Main class of the NetMusicPlayer.
 *
 * <p>
 * This class is used to start the logic of the Music Player, and to launch the
 * user interface.
 *
 * @author Rsl1122
 */
public class NMPlayer {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInterface.start(args);
    }

}
