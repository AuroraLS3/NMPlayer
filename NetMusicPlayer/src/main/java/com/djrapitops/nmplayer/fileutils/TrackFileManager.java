/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
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
        File regFile = new File(tracksFolder, "register.txt");
        if (!regFile.exists()) {
            try {
                regFile.createNewFile();
            } catch (IOException ex) {
                ErrorManager.toLog(TrackFileManager.class.getName(), ex);
            }
        }
        return tracksFolder;
    }

    public static List<Track> translateToTracks(List<String> playlist) {
        List<Track> tracks = new ArrayList<>();
        try {
            Files.lines(new File(getFolder(), "register.txt").toPath())
                    .map(line -> line.split(";"))
                    .forEach(splitLine -> {
                        if (splitLine.length >= 3) {
                            String trackPath = splitLine[2];
                            if (playlist.contains(trackPath)) {
                                tracks.add(new Track(splitLine[0], splitLine[1], trackPath));
                            }
                        }
                    });
        } catch (IOException ex) {
            ErrorManager.toLog(TrackFileManager.class.getName(), ex);
        }
        return tracks;
    }

    public static boolean saveRegisterFile(List<Track> tracks) {
        File tracksFolder = getFolder();
        File regFile = new File(tracksFolder, "register.txt");
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(regFile, false);
            pw = new PrintWriter(fw);
            for (Track track : tracks) {
                pw.println(track.getName() + ";" + track.getArtist() + ";" + track.getFilePath());
                pw.flush();
            }
        } catch (IOException e) {
            ErrorManager.toLog(TrackFileManager.class.getName(), e);
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    ErrorManager.toLog(TrackFileManager.class.getName(), ex);
                }
            }
        }
        return true;
    }

    public static Track download(String trackAddress) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String[] nNp = YoutubeMp3Downloader.getLink(trackAddress);
            if (nNp != null) {
                String name = nNp[1];
                URL downloadUrl = new URL(nNp[0]);
                inputStream = downloadUrl.openStream();
                File mp3File = new File(getFolder(), name.replace(" ", "") + ".mp3");
                outputStream = new FileOutputStream(mp3File);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                String fileName = mp3File.getName();
                return new Track(name, "Unknown", fileName);
            }
        } catch (IOException ex) {
            ErrorManager.toLog(TrackFileManager.class.getName(), ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                }
            }
        }
        return null;
    }
}
