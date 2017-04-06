/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import java.io.File;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author ristolah
 */
public class AddTrackButton extends Button {

    private final FileChooser fileChooser = new FileChooser();

    public AddTrackButton(PlayButton play, Stage stage) {
        super.setStyle("-fx-background-color: White");
        super.setText("Add Track");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("mp3 and wav", Arrays.asList(new String[]{"mp3", "wav"})));
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            final MusicPlayer musicPlayer = MusicPlayer.getInstance();

            if (musicPlayer.isPlaying()) {
                musicPlayer.pause();
                play.update();
            }

            File selectedFile = fileChooser.showOpenDialog(stage);
            musicPlayer.addTrackToPlaylist(TrackFileManager.processFile(selectedFile));
        };
        super.setOnAction(h);
    }

}
