package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import javafx.beans.Observable;
import javafx.scene.control.Slider;

/**
 * This UI Component displays the playback position and can be used to change
 * the playback position.
 *
 * @author Risto
 */
public class TrackProgressBar extends Slider implements Updatable {

    /**
     * Constructor.
     *
     * Sets a listener for user induced changes, that changes the playback
     * position to a new position set by the user.
     */
    public TrackProgressBar() {
        super.setPrefWidth(10000);
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        TrackProgressBar bar = this;
        super.valueProperty().addListener((Observable ov) -> {
            if (bar.isValueChanging()) {
                MusicPlayer.getInstance().setTrackPosition(bar.getValue() / 100);
                bar.disableProperty().set(true);
                bar.update();
            }
        });
        MusicPlayer.getInstance().setProgressBar(this);
    }

    @Override
    public void update() {
        disableProperty().set(false);
        if (!super.isValueChanging()) {
            super.adjustValue(MusicPlayer.getInstance().getCurrentTrackProgress() * 100);
        }
    }

}
