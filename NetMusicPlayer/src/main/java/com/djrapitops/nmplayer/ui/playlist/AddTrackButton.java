/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.ui.Updatable;
import com.djrapitops.nmplayer.ui.UserInterface;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaFx UI component, a Button used to add tracks to the playlist.
 * <p>
 * Pauses the playback when pressed, and opens a new file selection window.
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
     * @param ui    A UI Component to update when the button is pressed.
     * @param stage Stage used by the UserInterface.
     * @see FileChooser
     * @see UserInterface
     * @see Application
     */
    public AddTrackButton(Updatable ui, Stage stage) {
        setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        setText("Add Tracks");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("mp3", Arrays.asList(".mp3", ".wav")));
        fileChooser.setTitle("NMPlayer | Add File(s)");
        setOnAction(event -> {
            final MusicPlayer musicPlayer = MusicPlayer.getInstance();
            ui.update();
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
            if (selectedFiles == null) {
                return;
            }

            PlaylistManager playlistManager = musicPlayer.getPlaylistManager();

            addFilesAsTracks(musicPlayer, selectedFiles, playlistManager);

            if (playlistManager.isRandom()) {
                playlistManager.setRandom(true);
            }
            ui.update();
            if (musicPlayer.isPlaying()) {
                musicPlayer.play();
            }
        });
    }

    private void addFilesAsTracks(MusicPlayer musicPlayer, List<File> selectedFiles, PlaylistManager playlistManager) {
        List<Track> newTracks = selectedFiles.stream()
                .map(TrackFileManager::processFile)
                .filter(newTrack -> !playlistManager.hasTrack(newTrack))
                .collect(Collectors.toList());

        for (Track newTrack : newTracks) {
            musicPlayer.addTrackToPlaylist(newTrack);
            Track currentTrack = musicPlayer.getCurrentTrack();
            if (currentTrack != null) {
                musicPlayer.selectTrack(currentTrack);
            } else {
                musicPlayer.selectTrack(0);
            }
        }
    }

}
