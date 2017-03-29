/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.messaging.MessageSender;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author ristolah
 */
public class UserInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NMPlayer");
        
        MediaView view = new MediaView(MusicPlayer.getInstance().getMediaPlayer());
        
        BorderPane root = new BorderPane();
        root.setCenter(view);
        root.setBottom(toolbar());
        root.setTop(console());
        
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
    
    private HBox console() {
        HBox console = new HBox();
        console.setStyle("-fx-background-color: Grey");
        console.getChildren().add(new TextConsole());        
        return console;
    }
    
    private HBox toolbar() {
        HBox toolbar = new HBox();
        toolbar.setAlignment(Pos.CENTER);
        toolbar.setSpacing(5);
        toolbar.setStyle("-fx-background-color: Black");
        
        toolbar.getChildren().add(new PlayButton());
        
        return toolbar;
    }
    
}
