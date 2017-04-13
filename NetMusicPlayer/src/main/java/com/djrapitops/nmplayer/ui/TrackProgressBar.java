/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import javafx.beans.Observable;
import javafx.scene.control.Slider;

/**
 *
 * @author Risto
 */
public class TrackProgressBar extends Slider implements Updateable {

    public TrackProgressBar() {
        super.setPrefWidth(10000);
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        TrackProgressBar bar = this;
        super.valueProperty().addListener((Observable ov) -> {
            if (bar.isValueChanging()) {
                MusicPlayer.getInstance().setTrackPosition(bar.getValue()/100);
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
