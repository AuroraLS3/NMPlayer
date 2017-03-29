/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import java.util.Objects;

/**
 *
 * @author Risto
 */
public class Track {
    private final String name;
    private final String artist;
    private final String fileName;

    public Track(String name, String artist, String filePath) {
        this.name = name;
        this.artist = artist;
        this.fileName = filePath;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilePath() {
        return fileName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Track other = (Track) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return artist+" - "+name;
    }
}
