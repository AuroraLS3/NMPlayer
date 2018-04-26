
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used to change the currently playing track.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class NextButton extends Button {

    /**
     * Constructor for the button. Sets the click event response to play the
     * next track on the MusicPlayer.
     *
     * @param updatable A UI Component to update when the button is pressed.
     * @see MusicPlayer
     */
    public NextButton(Updatable updatable) {
        setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        setPrefWidth(30);
        setText("⏩");
        setOnAction(event -> {
            MusicPlayer.getInstance().nextTrack();
            updatable.update();
        });
    }

}
