/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.functionality.utilities.TextUtils;
import com.djrapitops.nmplayer.ui.playlist.AddTrackButton;
import com.djrapitops.nmplayer.ui.playlist.ChangePlaylistBox;
import com.djrapitops.nmplayer.ui.playlist.UIPlaylist;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
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
    private Stage stage;
    private VBox toolbar;

    public UserInterface() {
        updateableComponents = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("NMPlayer");
        stage.getIcons().add(new Image("http://djrapitops.com/uploads/NMPlayer.png"));
//        MediaView view = new MediaView(MusicPlayer.getInstance().getMediaPlayer());
        BorderPane root = new BorderPane();
        root.setCenter(playlist());
        toolbar = toolbar();
//        root.setCenter(view);
        root.setBottom(toolbar);
        root.setTop(console());

        primaryStage.setScene(new Scene(root, 400, 700));
        primaryStage.show();
        Thread.sleep(1000);
        update();
        MusicPlayer.getInstance().setEndOfMediaUpdate(this);
    }

    private Node playlist() {
        HBox changePlaylistBox = new ChangePlaylistBox(this, new AddTrackButton(this, stage));

        VBox playlist = new UIPlaylist(changePlaylistBox, this);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(playlist);
        scroll.fitToWidthProperty().set(true);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        updateableComponents.add((Updateable) changePlaylistBox);
        updateableComponents.add((Updateable) playlist);
        return scroll;
    }

    private VBox console() {
        VBox box = new VBox();
//        box.setSpacing(5);
        HBox console = new HBox();
        console.getChildren().add(new TextConsole());
        box.getChildren().add(console);
//        box.getChildren().add(toolbar());
        return box;
    }

    private VBox toolbar() {
        VBox box = new VBox();
        HBox toolbar = new HBox();
        toolbar.setAlignment(Pos.CENTER);
        toolbar.alignmentProperty().isBound();
        toolbar.setSpacing(5);
        toolbar.setStyle("-fx-background-color: White");
        final PlayButton play = new PlayButton(this);
        updateableComponents.add(play);
        toolbar.getChildren().add(new PreviousButton(this));
        toolbar.getChildren().add(play);
        toolbar.getChildren().add(new StopButton(this));
        toolbar.getChildren().add(new NextButton(this));
        TrackProgressBar progress = new TrackProgressBar();
        updateableComponents.add(progress);
        box.getChildren().add(progress);
        box.getChildren().add(toolbar);
        return box;
    }

    @Override
    public void update() {
        for (Updateable u : updateableComponents) {
            u.update();
        }
        MusicPlayer mp = MusicPlayer.getInstance();
        Track currentTrack = mp.getCurrentTrack();
        String track = "None";
        if (currentTrack != null) {
            track = currentTrack.toString();
        }
        if (toolbar != null) {
            toolbar.requestFocus();
        }
        stage.setTitle((mp.isPlaying() ? "▶" : "") + " NMPlayer | " + TextUtils.uppercaseFirst(mp.getSelectedPlaylist()) + " | " + track);
    }

}
