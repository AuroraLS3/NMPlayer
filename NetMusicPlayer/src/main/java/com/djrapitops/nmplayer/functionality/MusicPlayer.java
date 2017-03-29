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

    private Track currentTrack;
    private MediaPlayer mp;

    public MusicPlayer() {
        playlist = new PlaylistManager();
        msg = MessageSender.getInstance();
    }

    public void init() {
        playlist.setPlaylist(TrackFileManager.translateToTracks(PlaylistFileManager.load("playlist")));
        addTrackToPlaylist("https://www.youtube.com/watch?v=6RthZhyRmZ8");
        selectTrack(0);    
    }

    public void play() {
        mp.play();
        msg.send("Test msg for play");
    }

    public void pause() {
        mp.pause();        
    }

    public void stop() {
        mp.stop();
    }

    public void selectTrack(int i) {
        if (!playlist.getPlaylist().isEmpty()) {
            final String name = playlist.getPlaylist().get(0).getName();
            selectTrack(name);
            msg.send("Test msg: "+name);
        } else {
            msg.send(Phrase.PLAYLIST_EMPTY + "");
        }
    }

    public void selectTrack(String trackName) {
        Track track = playlist.getTrackByName(trackName);
        if (track != null) {
            String mp3FileName = track.getFilePath();
            File trackFile = new File(TrackFileManager.getFolder(), mp3FileName);
            Media play = new Media(trackFile.toURI().toString());
            mp = new MediaPlayer(play);
        }
    }

    public void addTrackToPlaylist(String trackAddress) {
        Track track = TrackFileManager.download(trackAddress);
        if (track != null) {
            playlist.addFilePathToPlaylist(track);
        }
        PlaylistFileManager.save(playlist.getPlaylist(), "playlist", true);
    }

    public MediaPlayer getMediaPlayer() {
        return mp;
    }
    
    public static MusicPlayer getInstance() {
        return MusicPlayerSingletonHolder.INSTANCE;
    }
    
    private static class MusicPlayerSingletonHolder {
        private static final MusicPlayer INSTANCE = new MusicPlayer();
    }
}
