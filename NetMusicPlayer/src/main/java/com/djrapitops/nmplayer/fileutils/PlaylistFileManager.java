/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.functionality.utilities.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is used to write .txt files inside the /playlists/ folder.
 * <p>
 * The .txt files contain absolute file paths to the .mp3 files used by the
 * program.
 * <p>
 * All the methods are static.
 *
 * @author Rsl1122
 */
public class PlaylistFileManager {

    /**
     * Saves the {filepaths} list to a {name}.txt file.
     * <p>
     * If playlists folder doesn't exist it will be created.
     * <p>
     * If a file with the name exists inside the folder, it's contents will be overwritten.
     *
     * @param filepaths a List containing filepaths to .mp3 files inside the playlist.
     * @param name      Name of the playlist, and the name of the .txt file
     * @return Success of the save.
     */
    public static boolean save(List<String> filepaths, String name) {
        File playlistFolder = getPlaylistFolder();
        File playlistFile = new File(playlistFolder, name + ".txt");
        return FileUtility.writeFile(filepaths, playlistFile);
    }

    /**
     * Creates the playlists folder if it doesn't exist and returns it.
     *
     * @return The playlists folder.
     */
    public static File getPlaylistFolder() {
        File playlistFolder = new File("playlists");
        if (!playlistFolder.exists()) {
            playlistFolder.mkdir();
        }
        return playlistFolder;
    }

    /**
     * Reads the contents of {name}.txt and places them in a List.
     *
     * @param name Name of the playlist, and the name of the .txt file.
     * @return List containing all the lines inside the file.
     */
    public static List<String> load(String name) {
        if (name.equals("all")) {
            return loadAll();
        }
        ArrayList<String> playlist = new ArrayList<>();
        File playlistFolder = getPlaylistFolder();
        File playlistFile = new File(playlistFolder, name + ".txt");
        if (playlistFile.exists()) {
            try {
                List<String> lines = FileUtility.lines(playlistFile);
                if (!lines.isEmpty()) {
                    playlist.addAll(lines);
                }
            } catch (Exception ex) {
                ErrorManager.toLog(PlaylistFileManager.class, ex);
            }
        }
        return playlist;
    }

    /**
     * Used to load the All playlist, which includes tracks in tracks folder and
     * other playlists.
     *
     * @return List of filepaths to every track known by the player.
     */
    public static List<String> loadAll() {
        Set<String> playlist = new HashSet<>();
        File playlistFolder = getPlaylistFolder();
        File[] files = playlistFolder.listFiles();
        for (File file : files) {
            if (file.getName().equals("all.txt")) {
                try {
                    playlist.addAll(FileUtility.lines(file));
                } catch (IOException ex) {
                    ErrorManager.toLog(PlaylistFileManager.class, ex);
                }
            }
            if (file.isDirectory() || !file.canRead() || !file.getName().endsWith(".txt") || file.getName().equals("all.txt")) {
                continue;
            }
            playlist.addAll(load(TextUtils.removeExtension(file.getName())));
        }
        for (File trackF : TrackFileManager.getFolder().listFiles()) {
            boolean isSupportedFileType = TrackFileManager.isSupportedFileType(trackF);
            if (trackF.isDirectory() || !trackF.canRead() || !isSupportedFileType) {
                continue;
            }
            playlist.add(trackF.getAbsolutePath());
        }
        return new ArrayList<>(playlist);
    }

    /**
     * This method is used to translate a List containing Track objects into a
     * List containing absolute file paths as string before saving them to the
     * approppriate file.
     *
     * @param playlist List containing Track objects, which file paths are to be
     *                 saved.
     * @param name     Name of the playlist, and the name of the .txt file.
     * @return Success of the save.
     */
    public static boolean saveTracksAsPlaylist(List<Track> playlist, String name) {
        return save(playlist.stream().map(Track::getFilePath).collect(Collectors.toList()), name);
    }

    /**
     * A Method used to get a Comma separated list of playlist in the playlists
     * folder.
     *
     * @return Playlists, for example "All, TestPlaylist, Small"
     */
    public static String getKnownPlaylists() {
        StringBuilder playlists = new StringBuilder();
        File[] files = getPlaylistFolder().listFiles();
        for (File file : files) {
            if (file.isDirectory() || !file.canRead() || !file.getName().endsWith(".txt")) {
                continue;
            }
            int tracks = 0;
            try {
                tracks = FileUtility.lines(file).size();
            } catch (IOException ex) {
            }
            if (tracks > 0) {
                playlists.append(TextUtils.uppercaseFirst(TextUtils.removeExtension(file.getName()))).append(", ");
            }
        }
        String string = playlists.toString();
        if (string.length() >= 2) {
            return string.substring(0, string.length() - 2);
        }
        return "All";
    }
}
