package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * This element represents a Track object. Contains button to swap to this
 * track, or remove it from the playlist.
 *
 * @author ristolah
 */
public class UITrack extends HBox implements Updatable {

    private final Track track;
    private final Updatable u;

    /**
     * Constructor.
     *
     * Requires a Track object to get the text for the element and for removing
     * it from the playlist. Updatable will be called if either of the buttons
     * is pressed.
     *
     * @param track Track this element represents
     * @param u Updatable to call when a button is pressed.
     */
    public UITrack(Track track, Updatable u) {
        this.u = u;
        this.track = track;
        super.setAlignment(Pos.CENTER_LEFT);
        super.alignmentProperty().isBound();
        super.setSpacing(5);
        super.setStyle("-fx-background-color: Lightgrey");
        super.getChildren().add(new SelectButton(this));
        super.getChildren().add(new RemoveButton(this));
    }

    /**
     * Used to get the Track object this element represents.
     *
     * @return the track.
     */
    public Track getTrack() {
        return track;
    }

    @Override
    public void update() {
        u.update();
    }
}
