package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * JavaFx UI component, a Button used to change the current playlist.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class ChangePlaylistButton extends Button {

    /**
     * Constructor for the button. Sets the click event response to change the
     * playlist of MusicPlayer.
     *
     * @param t TextArea which text is used to read user input for playlist
     *          name.
     * @param u A UI Component to update when the button is pressed.
     * @see MusicPlayer
     * @see UITrack
     * @see PlaylistManager
     */
    public ChangePlaylistButton(TextField t, Updateable u) {
        super.setText("Change Playlist");
        super.setStyle("-fx-background-color: White");
        super.setAlignment(Pos.CENTER_RIGHT);
        super.setOnAction(event -> changePlaylist(t, u));
    }

    /**
     * Changes the playlist according to the text inside the textfield.
     * <p>
     * If the textfield is empty, MessageSender will be used to send the known
     * playlists.
     *
     * @param t TextField used to get the playlist name.
     * @param u Element to call .update() on after the change is complete. (If
     *          successful)
     * @throws IllegalStateException If JavaFx Application is not running.
     */
    public void changePlaylist(TextField t, Updateable u) {
        String newPlaylist = t.getText().toLowerCase().trim();
        if (newPlaylist.isEmpty()) {
            MessageSender.getInstance().send(Phrase.EMPTY_NAME.parse(PlaylistFileManager.getKnownPlaylists()));
            return;
        }
        MusicPlayer mp = MusicPlayer.getInstance();
        mp.selectPlaylist(newPlaylist);
        PlaylistManager playlistManager = mp.getPlaylistManager();
        if (playlistManager.isRandom()) {
            playlistManager.setRandom(true);
        }
        u.update();
    }

}
