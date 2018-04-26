
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used toggle shuffle.
 *
 * @author Rsl1122
 * @see MusicPlayer
 */
public class ShuffleButton extends Button implements Updatable {

    /**
     * Constructor for the Button.
     * <p>
     * Sets the click event response to either toggle the random state of PlaylistManager and update the text on the button.
     *
     * @see MusicPlayer
     */
    public ShuffleButton() {
        setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        setPrefWidth(30);
        setOnAction(event -> {
            PlaylistManager manager = MusicPlayer.getInstance().getPlaylistManager();
            manager.setRandom(!manager.isRandom());
            update();
        });
    }

    @Override
    public void update() {
        if (!MusicPlayer.getInstance().getPlaylistManager().isRandom()) {
            setText("🔁");
        } else {
            setText("🔀");
        }
    }

}
