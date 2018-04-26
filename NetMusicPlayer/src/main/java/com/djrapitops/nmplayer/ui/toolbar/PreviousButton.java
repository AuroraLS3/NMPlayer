/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.toolbar;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.ui.Updatable;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used to change the currently playing track.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class PreviousButton extends Button {

    /**
     * Constructor for the button.
     * <p>
     * Sets the click event response to play the previous track on the
     * MusicPlayer.
     *
     * @param u A UI Component to update when the button is pressed.
     * @see MusicPlayer
     */
    public PreviousButton(Updatable u) {
        setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        setPrefWidth(30);
        setText("⏪");
        setOnAction(event -> {
            MusicPlayer.getInstance().previousTrack();
            u.update();
        });
    }

}
