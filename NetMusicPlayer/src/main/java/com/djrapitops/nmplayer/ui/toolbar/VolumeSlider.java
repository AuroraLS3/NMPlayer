/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.beans.Observable;
import javafx.scene.control.Slider;

/**
 *
 * @author Risto
 */
public class VolumeSlider extends Slider implements Updateable{

    VolumeSlider bar = this;

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
