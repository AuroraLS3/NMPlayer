
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        mp.init();
    }

}
