/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Risto
 */
public class PlaylistFileManager {

    public static boolean save(List<String> links, String name) {
        File playlistFolder = getPlaylistFolder();
        File playlistFile = new File(playlistFolder, name + ".txt");
        try {
            if (!playlistFile.exists()) {
                playlistFile.createNewFile();
            }
            FileWriter fw = new FileWriter(playlistFile, false);
            try (PrintWriter pw = new PrintWriter(fw)) {
                for (String link : links) {
                    pw.println(link);
                    pw.flush();
                }
            }
        } catch (IOException e) {
            ErrorManager.toLog("com.djrapitops.nmplayer.fileutils.PlaylistFileManager", e);
            return false;
        }
        return true;
    }

    public static File getPlaylistFolder() {
        File playlistFolder = new File("playlists");
        if (!playlistFolder.exists()) {
            playlistFolder.mkdir();
        }
        return playlistFolder;
    }

    public static List<String> load(String name) {
        ArrayList<String> playlist = new ArrayList<>();
        File playlistFolder = getPlaylistFolder();
        File playlistFile = new File(playlistFolder, name + ".txt");
        if (playlistFile.exists()) {
            try {
                playlist.addAll(Files.lines(playlistFile.toPath()).collect(Collectors.toList()));
            } catch (IOException ex) {
                ErrorManager.toLog("com.djrapitops.nmplayer.fileutils.PlaylistFileManager", ex);
            }
        }
        return playlist;
    }
}
