/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.ui.Updateable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ristolah
 */
public final class UIPlaylist extends VBox implements Updateable {

    private List<HBox> tracks;
    private HBox changeBox;
    private Updateable ui;

    public UIPlaylist(HBox changeBox, Updateable ui) {
        this.ui = ui;
        this.changeBox = changeBox;
        super.setAlignment(Pos.TOP_LEFT);
        super.alignmentProperty().isBound();
        super.setSpacing(5);
        update();    
    }

    @Override
    public void update() {        
        ObservableList<Node> components = super.getChildren();
        components.clear();
        components.add(changeBox);
        tracks = getTrackElements();
        for (HBox track : tracks) {
            components.add(track);
        }   
    }

    private List<HBox> getTrackElements() {
        List<HBox> elements = new ArrayList<>();
        List<Track> playlist = MusicPlayer.getInstance().getPlaylist();
        for (Track track : playlist) {
            elements.add(new UITrack(track, ui));
        }
        return elements;
    }

}
