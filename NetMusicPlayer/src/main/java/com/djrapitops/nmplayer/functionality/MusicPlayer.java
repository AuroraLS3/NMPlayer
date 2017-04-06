/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.functionality.playlist.PlaylistManager;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Risto
 */
public class MusicPlayer {

    private final PlaylistManager playlist;
    private final MessageSender msg;
    private MediaPlayer mp;

    private Track currentTrack;
    private int currentTrackIndex;    
    private String selectedPlaylist;
    private boolean playing;

    public MusicPlayer() {
        playlist = new PlaylistManager();
        msg = MessageSender.getInstance();
    }

    public void init() {
        selectPlaylist("all");
        playing = false;
    }

    public void selectPlaylist(String playlistName) {
        selectedPlaylist = playlistName;
        playlist.setPlaylist(TrackFileManager.translateToTracks(PlaylistFileManager.load(selectedPlaylist)));
        selectTrack(0);
    }

    public void nextTrack() {
        if (currentTrack != null) {
            mp.stop();
            playing = false;
            selectTrack(currentTrackIndex + 1);
            play();
        }
    }

    public void previousTrack() {
        if (currentTrack != null) {
            mp.stop();
            playing = false;
            selectTrack(currentTrackIndex - 1);
            play();
        }
    }

    public void play() {
        if (mp != null) {
            playing = true;
            mp.play();
            msg.send(Phrase.NOW_PLAYING.parse(currentTrack.toString()));
        }
    }

    public void pause() {
        if (mp != null && playing) {
            playing = false;
            mp.pause();
            msg.send(Phrase.PAUSE + "");
        }
    }

    public void stop() {
        if (mp != null && playing) {
            playing = false;
            mp.stop();
            msg.send(Phrase.STOP + "");
        }
    }

    public void selectTrack(int i) {
        final int tracks = playlist.getPlaylist().size();
        if (playlist.isEmpty()) {
            msg.send(Phrase.PLAYLIST_EMPTY + "");
        } else if (i == -1) {
            selectTrack(tracks - 1);
        } else if (tracks > i && i >= 0) {
            final String name = playlist.getPlaylist().get(i).getName();
            currentTrackIndex = i;
            selectTrack(name);
        } else {
            selectTrack(0);
        }
    }

    public void selectTrack(String trackName) {
        Track track = playlist.getTrackByName(trackName);
        if (track != null) {
            String mp3FilePath = track.getFilePath();
            File trackFile = new File(mp3FilePath);
            if (!trackFile.exists()) {
                MessageSender.getInstance().send("File doesn't exist! " + track);
                return;
            }
            currentTrackIndex = playlist.getPlaylist().indexOf(track);
            currentTrack = track;
            msg.send(Phrase.SELECTED.parse(currentTrack.toString()));
            Media play = new Media(trackFile.toURI().toString());
            mp = new MediaPlayer(play);
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    nextTrack();
                }
            });
        }
    }

    public void addTrackToPlaylist(Track track) {
        if (track == null) {
            return;
        }
        playlist.addFilePathToPlaylist(track);
        PlaylistFileManager.save(playlist.getPlaylist(), selectedPlaylist, true);
        selectTrack(track.getName());
    }

    public MediaPlayer getMediaPlayer() {
        return mp;
    }

    public boolean isPlaying() {
        return playing;
    }
    
    public static MusicPlayer getInstance() {
        return MusicPlayerSingletonHolder.INSTANCE;
    }

    private static class MusicPlayerSingletonHolder {

        private static final MusicPlayer INSTANCE = new MusicPlayer();
    }
}
