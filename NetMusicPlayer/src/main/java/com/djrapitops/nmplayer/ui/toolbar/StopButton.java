package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
     * @param u A UI Component to update when the button is pressed.
     * @see MusicPlayer
     */
    public StopButton(Updateable u) {
        super.setPrefWidth(30);
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        super.setText("■");
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            MusicPlayer.getInstance().stop();
            u.update();
        };
        super.setOnAction(h);
    }
}
