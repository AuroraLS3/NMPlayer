package com.djrapitops.nmplayer;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.UserInterface;

/**
 * This is the Main class of the NetMusicPlayer.
 *
 * <p>
 * This class is used to start the logic of the Music Player, and to launch the
 * user interface.
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        mp.init();
    }

}
