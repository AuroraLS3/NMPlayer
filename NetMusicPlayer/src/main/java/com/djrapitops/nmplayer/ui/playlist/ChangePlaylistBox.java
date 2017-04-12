/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.ui.Updateable;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author ristolah
 */
public class ChangePlaylistBox extends HBox implements Updateable {

    private TextField selector;
    
    public ChangePlaylistBox(Updateable u, AddTrackButton addTrackButton) {
        super.setAlignment(Pos.CENTER);
        super.alignmentProperty().isBound();
        super.setSpacing(5);
        selector = new TextField();
        selector.setPromptText("Enter Playlist Name");
        selector.setDisable(true);
//        selector.setPrefWidth(10000);
        ObservableList<Node> components = super.getChildren();
        components.add(selector);
        components.add(new ChangePlaylistButton(selector, u));
        components.add(addTrackButton);
    }

    @Override
    public void update() {
        selector.clear();
        selector.setDisable(false);
    }

}
