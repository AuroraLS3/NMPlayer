/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to write .txt files inside the /playlists/ folder.
 *
 * <p>
 * The .txt files contain absolute file paths to the .mp3 files used by the
 * program.
 *
 * <p>
 * All the methods are static.
 *
 * @author Risto
 */
public class PlaylistFileManager {

    /**
     * Saves the {filepaths} list to a {name}.txt file.
     *
     * <p>
     * If playlists folder doesn't exist it will be created.
     *
     * <p>
     * If a file with the name exists inside the folder, it's contents will be
     * overwritten.
     *
     * @param filepaths a List containing filepaths to .mp3 files inside the
     * playlist.
     * @param name Name of the playlist, and the name of the .txt file
     * @return Success of the save.
     */
    public static boolean save(List<String> filepaths, String name) {
        File playlistFolder = getPlaylistFolder();
        File playlistFile = new File(playlistFolder, name + ".txt");
        try {
            playlistFile.createNewFile();
            FileWriter fw = new FileWriter(playlistFile, false);
            try (PrintWriter pw = new PrintWriter(fw)) {
                for (String filepath : filepaths) {
                    pw.println(filepath);
                    pw.flush();
                }
            }
        } catch (IOException e) {
            ErrorManager.toLog("com.djrapitops.nmplayer.fileutils.PlaylistFileManager", e);
            return false;
        }
        return true;
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
        ArrayList<String> playlist = new ArrayList<>();
        File playlistFolder = getPlaylistFolder();
        File playlistFile = new File(playlistFolder, name + ".txt");
        if (playlistFile.exists()) {
            try {
                playlist.addAll(Files.lines(playlistFile.toPath(), Charset.defaultCharset()).collect(Collectors.toList()));
            } catch (Exception ex) {
                ErrorManager.toLog("com.djrapitops.nmplayer.fileutils.PlaylistFileManager", ex);
            }
        }
        return playlist;
    }

    /**
     * This method is used to translate a List containing Track objects into a
     * List containing absolute file paths as string before saving them to the
     * approppriate file.
     *
     * @param playlist List containing Track objects, which file paths are to be
     * saved.
     * @param name Name of the playlist, and the name of the .txt file.
     * @param ok A value used to separate the method from the other save method.
     * @return Success of the save.
     */
    public static boolean save(List<Track> playlist, String name, boolean ok) {
        return save(playlist.stream().map(track -> track.getFilePath()).collect(Collectors.toList()), name);
    }
}
