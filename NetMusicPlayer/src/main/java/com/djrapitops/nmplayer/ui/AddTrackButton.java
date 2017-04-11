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
 * JavaFx UI component, a Button used to add tracks to the playlist.
 * <p>
 * Pauses the playback when pressed, and opens a new file selection window.
 * <p>
 * Playbutton is needed to update the pause status text if this button is
 * pressed.
 *
 * @author ristolah
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class AddTrackButton extends Button {

    private final FileChooser fileChooser = new FileChooser();

    /**
     * Constructor for the button.
     * <p>
     * Sets the click event response to open a new FileChooser and add a new
     * track to the playlist that contains information of the file.
     *
     * @param play Already initialized PlayButton.
     * @param stage Stage used by the UserInterface.
     * @see FileChooser
     * @see UserInterface
     * @see Application
     */
    public AddTrackButton(PlayButton play, Stage stage) {
        super.setStyle("-fx-background-color: White");
        super.setText("Add Track");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("mp3", Arrays.asList(new String[]{".mp3"})));
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
