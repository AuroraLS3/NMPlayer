
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used to play/pause the currently playing track.
 *
 * @author Rsl1122
 * @see MusicPlayer
 */
public class PlayButton extends Button implements Updatable {

    /**
     * Constructor for the Button.
     * <p>
     * Sets the click event response to either pause or play the playback with
     * MusicPlayer and update the text on the button.
     *
     * @param u Updatable to call when the button is pressed.
     * @see MusicPlayer
     */
    public PlayButton(Updatable u) {
        setStyle("-fx-background-color: #8290ed; -fx-text-fill: White; -fx-font-weight: bold;");
        setPrefWidth(40);
        u.update();
        setOnAction(event -> {
            MusicPlayer musicPlayer = MusicPlayer.getInstance();
            if (!musicPlayer.isPlaying()) {
                musicPlayer.play();
            } else {
                musicPlayer.pause();
            }
            u.update();
        });
    }

    @Override
    public void update() {
        if (!MusicPlayer.getInstance().isPlaying()) {
            setText(" ▶ ");
        } else {
            setText("⏸");
        }
    }

}
