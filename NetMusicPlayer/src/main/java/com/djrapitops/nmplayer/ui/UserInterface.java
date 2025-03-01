package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.functionality.utilities.TextUtils;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.ui.playlist.AddTrackButton;
import com.djrapitops.nmplayer.ui.playlist.ChangePlaylistBox;
import com.djrapitops.nmplayer.ui.playlist.UIPlaylist;
import com.djrapitops.nmplayer.ui.toolbar.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFx Application used as the Interface for the user.
 *
 * When started, initialises multiple UI components and sets them to be on a
 * certain part of the program.
 *
 * @author Rsl1122
 * @see Application
 * @see Stage
 */
public class UserInterface extends Application implements Updatable {

    private final List<Updatable> updatableComponents;
    private Stage stage;
    private VBox toolbar;

    /**
     * Constructor that initializes updatableComponents List.
     */
    public UserInterface() {
        updatableComponents = new ArrayList<>();
    }

    public static void start(String[] args) {
        Application.launch(args);
    }

    private Node playlist() {
        HBox changePlaylistBox = new ChangePlaylistBox(this, new AddTrackButton(this, stage));
        VBox box = new VBox();
        VBox playlist = new UIPlaylist(this);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(playlist);
        scroll.fitToWidthProperty().set(true);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        box.getChildren().add(changePlaylistBox);
        box.getChildren().add(scroll);
        updatableComponents.add((Updatable) changePlaylistBox);
        updatableComponents.add((Updatable) playlist);
        return box;
    }

    private VBox console() {
        VBox box = new VBox();
        HBox console = new HBox();
        TextConsole con = new TextConsole();
        console.getChildren().add(con);
        MessageSender.getInstance().setOutput(con);
        box.getChildren().add(console);
        return box;
    }

    private VBox toolbar() {
        VBox box = new VBox();
        HBox controls = new HBox();
        controls.setAlignment(Pos.CENTER_RIGHT);
        controls.alignmentProperty().isBound();
        controls.setSpacing(5);
        controls.setStyle("-fx-background-color: White");
        final PlayButton play = new PlayButton(this);
        updatableComponents.add(play);
        ObservableList<Node> conponents = controls.getChildren();
        ShuffleButton shuffle = new ShuffleButton();
        conponents.add(shuffle);
        updatableComponents.add(shuffle);
        conponents.add(new PreviousButton(this));
        conponents.add(play);
        conponents.add(new StopButton(this));
        conponents.add(new NextButton(this));
        VolumeSlider volumeSlider = new VolumeSlider();
        updatableComponents.add(volumeSlider);
        conponents.add(volumeSlider);
        TrackProgressBar progress = new TrackProgressBar();
        updatableComponents.add(progress);
        box.getChildren().add(progress);
        box.getChildren().add(controls);
        return box;
    }

    @Override
    public void update() {
        for (Updatable u : updatableComponents) {
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

    /**
     * Used for Key press functionality processing.
     *
     * @param keyEvent Key pressed.
     * @throws IllegalStateException if JavaFx application not started.
     */
    private void handleKeyPress(KeyEvent keyEvent) {
        MusicPlayer musicPlayer = MusicPlayer.getInstance();
        KeyCode key = keyEvent.getCode();
        if (key == null) {
            return;
        } else {
            switch (key) {
                case TRACK_NEXT:
                case RIGHT:
                    musicPlayer.nextTrack();
                    break;
                case TRACK_PREV:
                case LEFT:
                    musicPlayer.previousTrack();
                    break;
                case PLAY:
                case PAUSE:
                case SPACE:
                    if (musicPlayer.isPlaying()) {
                        musicPlayer.pause();
                    } else {
                        musicPlayer.play();
                    }
                    break;
                case STOP:
                    musicPlayer.stop();
                    break;
                case DOWN:
                    musicPlayer.setVolume(musicPlayer.getVolume() - 0.1);
                    break;
                case UP:
                    musicPlayer.setVolume(musicPlayer.getVolume() + 0.1);
                    break;
                default:
                    return;
            }
        }
        update();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("NMPlayer");
        stage.getIcons().add(new Image("http://djrapitops.com/uploads/NMPlayer.png"));
        BorderPane root = new BorderPane();
        root.setCenter(playlist());
        toolbar = toolbar();
        root.setBottom(toolbar);
        root.setTop(console());

        primaryStage.setScene(new Scene(root, 400, 700));
        primaryStage.show();
        MusicPlayer musicPlayer = MusicPlayer.getInstance();
        root.setOnKeyPressed(this::handleKeyPress);
        try {
            musicPlayer.init();
        } catch (IllegalStateException e) {
            MessageSender.getInstance().send(Phrase.ERROR_JAVAFX + "");
        }
        update();
        musicPlayer.setEndOfMediaUpdate(this);
    }
}
