/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import java.util.Objects;

/**
 * This class is used to store information of mp3 file in a single object.
 *
 * @author Rsl1122
 */
public class Track {

    private final String name;
    private final String artist;
    private final String fileName;

    /**
     * Used to create a new Track object.
     *
     * @param name     Track name of the mp3.
     * @param artist   Artist of the mp3.
     * @param filePath The absolute filepath of the .mp3 file.
     */
    public Track(String name, String artist, String filePath) {
        this.name = name;
        this.artist = artist;
        this.fileName = filePath;
    }

    /**
     * Grabs the name contained in the Track object.
     *
     * @return Track name of the mp3.
     */
    public String getName() {
        return name;
    }

    /**
     * Grabs the artist contained in the Track object.
     *
     * @return Artist of the mp3.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Grabs the filepath contained in the Track object.
     *
     * @return The absolute filepath of the .mp3 file.
     */
    public String getFilePath() {
        return fileName;
    }

    @Override
    public String toString() {
        return artist + " - " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(name, track.name) &&
                Objects.equals(artist, track.artist) &&
                Objects.equals(fileName, track.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artist, fileName);
    }
}
