/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.ui.playlist.AddTrackButton;
import com.djrapitops.nmplayer.ui.playlist.ChangePlaylistBox;
import com.djrapitops.nmplayer.ui.playlist.UIPlaylist;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFx Application used as the Interface for the user.
 *
 * <p>
 * When started, initialises multiple UI components and sets them to be on a
 * certain part of the program.
 *
 * @author Rsl1122
 * @see Application
 * @see Stage
 */
public class UserInterface extends Application implements Updateable {

    private List<Updateable> updateableComponents;

    public UserInterface() {
        updateableComponents = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NMPlayer");

//        MediaView view = new MediaView(MusicPlayer.getInstance().getMediaPlayer());
        BorderPane root = new BorderPane();
        root.setCenter(playlist(primaryStage));
//        root.setCenter(view);
        root.setBottom(toolbar());
        root.setTop(console());

        primaryStage.setScene(new Scene(root, 400, 700));
        primaryStage.show();
        Thread.sleep(1000);
        update();
    }

    private VBox playlist(Stage stage) {
        HBox changePlaylistBox = new ChangePlaylistBox(this, new AddTrackButton(this, stage));
        VBox playlist = new UIPlaylist(changePlaylistBox, this);
        updateableComponents.add((Updateable) changePlaylistBox);
        updateableComponents.add((Updateable) playlist);
        return playlist;
    }

    private HBox console() {
        HBox console = new HBox();
        console.getChildren().add(new TextConsole());
        return console;
    }

    private HBox toolbar() {
        HBox toolbar = new HBox();
        toolbar.setAlignment(Pos.CENTER);
        toolbar.alignmentProperty().isBound();
        toolbar.setSpacing(5);
        toolbar.setStyle("-fx-background-color: Grey");
        final PlayButton play = new PlayButton();
        updateableComponents.add(play);
        toolbar.getChildren().add(new PreviousButton(this));
        toolbar.getChildren().add(play);
        toolbar.getChildren().add(new StopButton(this));
        toolbar.getChildren().add(new NextButton(this));

        return toolbar;
    }

    @Override
    public void update() {
        for (Updateable u : updateableComponents) {
            u.update();
        }
    }

}
