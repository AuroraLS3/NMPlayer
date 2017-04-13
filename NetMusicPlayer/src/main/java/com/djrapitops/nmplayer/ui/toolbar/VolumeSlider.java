package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.beans.Observable;
import javafx.scene.control.Slider;

/**
 * This element is used to change the volume of the playback.
 *
 * @author Risto
 * @see MusicPlayer
 */
public class VolumeSlider extends Slider implements Updateable {

    VolumeSlider bar = this;

    /**
     * Constructor.
     *
     * Will update the slider position to the current volume of the MusicPlayer.
     * Adds a listener for user input that changes the volume of the playback.
     */
    public VolumeSlider() {
        this.update();
        super.valueProperty().addListener((Observable ov) -> {
            if (bar.isValueChanging()) {
                MusicPlayer.getInstance().setVolume(bar.getValue() / 100);
            }
        });
    }

    @Override
    public void update() {
        bar.adjustValue(MusicPlayer.getInstance().getVolume() * 100);
    }
}
