/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Risto
 */
public class TrackFileManager {

    public static File getFolder() {
        File tracksFolder = new File("tracks");
        if (!tracksFolder.exists()) {
            tracksFolder.mkdir();
        }
        return tracksFolder;
    }

    public static List<Track> translateToTracks(List<String> filepaths) {
        List<Track> tracks = new ArrayList<>();
        for (String filepath : filepaths) {
            Track track = processFile(new File(filepath));
            if (track != null) {
                tracks.add(track);
            }
        }
        return tracks;
    }

    public static Track processFile(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        String fileName = file.getName();
        if (!fileName.contains(".mp3")) {
            MessageSender.getInstance().send(Phrase.WRONG_FILETYPE + "");
            return null;
        }
        Track track = new Track(getTrackName(file), getArtist(file), file.getAbsolutePath());
        MessageSender.getInstance().send(Phrase.ADDED_TRACK.parse(track.getArtist() + " - " + track.getName()));
        return track;
    }

    public static String getArtist(File file) {
        String artist = null;
        if (file.getName().contains(".mp3")) {
            try {
                Mp3File mp3 = new Mp3File(file);
                final ID3v2 id3v2Tag = mp3.getId3v2Tag();
                if (id3v2Tag != null) {
                    artist = id3v2Tag.getAlbumArtist();
                } else {
                    final ID3v1 id3v1Tag = mp3.getId3v1Tag();
                    if (id3v1Tag != null) {
                        artist = id3v1Tag.getArtist();
                    }
                }
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                ErrorManager.toLog("com.djrapitops.nmplayer.fileutils.TrackFileManager", ex);
            }
        }
        if (artist == null) {
            artist = "Artist";
        }
        return artist;
    }

    public static String getTrackName(File file) {
        String title = null;
        if (file.getName().contains(".mp3")) {
            try {
                Mp3File mp3 = new Mp3File(file);
                final ID3v2 id3v2Tag = mp3.getId3v2Tag();
                if (id3v2Tag != null) {
                    title = id3v2Tag.getTitle();
                } else {
                    final ID3v1 id3v1Tag = mp3.getId3v1Tag();
                    if (id3v1Tag != null) {
                        title = id3v1Tag.getTrack();
                    }
                }
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                ErrorManager.toLog("com.djrapitops.nmplayer.fileutils.TrackFileManager", ex);
            }
        }
        if (title == null) {
            title = "Track";
        }
        return title;
    }
}
