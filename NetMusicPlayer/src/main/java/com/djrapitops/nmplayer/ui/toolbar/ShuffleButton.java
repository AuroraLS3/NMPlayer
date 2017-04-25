
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used toggle shuffle.
 *
 * @author Rsl1122
 * @see MusicPlayer
 */
public class ShuffleButton extends Button implements Updateable {

    /**
     * Constructor for the Button.
     * <p>
     * Sets the click event response to either toggle the random state of PlaylistManager and update the text on the button.
     *
     * @see MusicPlayer
     */
    public ShuffleButton() {
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        super.setPrefWidth(30);
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            PlaylistManager manager = MusicPlayer.getInstance().getPlaylistManager();
            manager.setRandom(!manager.isRandom());
            update();
        };
        super.setOnAction(h);
    }

    @Override
    public void update() {
        if (!MusicPlayer.getInstance().getPlaylistManager().isRandom()) {
            super.setText("🔁");
        } else {
            super.setText("🔀");
        }
    }

}
