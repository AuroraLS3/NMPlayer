
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
     * @param u A UI Component to update when the button is pressed.
     * @see MusicPlayer
     */
    public NextButton(Updateable u) {
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        super.setPrefWidth(30);
        super.setText("⏩");
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            MusicPlayer.getInstance().nextTrack();
            u.update();
        };
        super.setOnAction(h);
    }

}
