package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.beans.Observable;
import javafx.scene.control.Slider;

/**
 * This element is used to change the volume of the playback.
 *
 * @author Risto
 * @see MusicPlayer
 */
public class VolumeSlider extends Slider implements Updatable {

    /**
     * Constructor.
     *
     * Will update the slider position to the current volume of the MusicPlayer.
     * Adds a listener for user input that changes the volume of the playback.
     */
    public VolumeSlider() {
        this.update();

        super.valueProperty().addListener((Observable ov) -> {
            if (isValueChanging()) {
                MusicPlayer.getInstance().setVolume(getValue() / 100);
            }
        });
    }

    @Override
    public void update() {
        adjustValue(MusicPlayer.getInstance().getVolume() * 100);
    }
}
