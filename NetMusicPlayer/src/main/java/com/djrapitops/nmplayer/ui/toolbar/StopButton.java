package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used to stop playing the currently playing
 * track.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class StopButton extends Button {

    /**
     * Constructor for the button.
     * <p>
     * Sets the click event response to stop playback on MusicPlayer.
     *
     * @param updatable A UI Component to update when the button is pressed.
     * @see MusicPlayer
     */
    public StopButton(Updatable updatable) {
        setPrefWidth(30);
        setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        setText("■");
        setOnAction(event -> {
            MusicPlayer.getInstance().stop();
            updatable.update();
        });
    }
}
