/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NMPlayer");

//        MediaView view = new MediaView(MusicPlayer.getInstance().getMediaPlayer());

        BorderPane root = new BorderPane();
//        root.setCenter(view);
        root.setBottom(toolbar(primaryStage));
        root.setRight(console());

        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    private HBox console() {
        HBox console = new HBox();
        console.setStyle("-fx-background-color: Grey");
        console.getChildren().add(new TextConsole());
        return console;
    }

    private HBox toolbar(Stage stage) {
        HBox toolbar = new HBox();
        toolbar.setAlignment(Pos.CENTER);
        toolbar.alignmentProperty().isBound();
        toolbar.setSpacing(5);
        toolbar.setStyle("-fx-background-color: Black");
        final PlayButton play = new PlayButton();

        toolbar.getChildren().add(new AddTrackButton(play, stage));
        toolbar.getChildren().add(new PreviousButton(play));
        toolbar.getChildren().add(play);
        toolbar.getChildren().add(new StopButton(play));
        toolbar.getChildren().add(new NextButton(play));

        return toolbar;
    }

}
