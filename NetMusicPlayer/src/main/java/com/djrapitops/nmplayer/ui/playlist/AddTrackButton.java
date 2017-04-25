/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.ui.Updateable;
import com.djrapitops.nmplayer.ui.UserInterface;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * JavaFx UI component, a Button used to add tracks to the playlist.
 * <p>
 * Pauses the playback when pressed, and opens a new file selection window.F
 *
 * @author Rsl1122
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
     * @param ui A UI Component to update when the button is pressed.
     * @param stage Stage used by the UserInterface.
     * @see FileChooser
     * @see UserInterface
     * @see Application
     */
    public AddTrackButton(Updateable ui, Stage stage) {
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        super.setText("Add Tracks");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("mp3", Arrays.asList(new String[]{".mp3", ".wav"})));
        fileChooser.setTitle("NMPlayer | Add File(s)");
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            final MusicPlayer musicPlayer = MusicPlayer.getInstance();
            ui.update();
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
            if (selectedFiles != null) {
                for (File file : selectedFiles) {
                    Track newTrack = TrackFileManager.processFile(file);
                    if (!MusicPlayer.getInstance().getPlaylistManager().hasTrack(newTrack)) {
                        musicPlayer.addTrackToPlaylist(newTrack);
                        Track currentTrack = musicPlayer.getCurrentTrack();
                        if (currentTrack != null) {
                            musicPlayer.selectTrack(currentTrack);
                        } else {
                            musicPlayer.selectTrack(0);
                        }
                        PlaylistManager playlistManager = musicPlayer.getPlaylistManager();
                        if (playlistManager.isRandom()) {
                            playlistManager.setRandom(true);
                        }
                    } else {
                        MessageSender.getInstance().send(Phrase.ALREADY_HAS_TRACK.parse(newTrack.toString()));
                    }
                    File directory = file.getParentFile();
                    if (directory != null && directory.isDirectory()) {
                        fileChooser.setInitialDirectory(directory);
                    }
                }
                ui.update();
                if (musicPlayer.isPlaying()) {
                    musicPlayer.play();
                }
            }
        };
        super.setOnAction(h);
    }

}
