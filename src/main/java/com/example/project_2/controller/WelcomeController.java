package com.example.project_2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class WelcomeController {

    @FXML
    private Button playButton;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private ImageView welcomeImageView;

    @FXML
public void initialize() {

        Image welcomeImage = new Image(getClass().getResource("/com/example/project_2/images/welcome-bg.jpg").toExternalForm());
        welcomeImageView.setImage(welcomeImage);

        Image playButtonPressed = new Image(getClass().getResource("/com/example/project_2/images/play-button-pressed.png").toExternalForm());
        Image playButtonImage =new Image(getClass().getResource("/com/example/project_2/images/play-button.png").toExternalForm());
        playButton.setEffect(new ImageInput(playButtonImage));

        playButton.setOnMousePressed(event -> {
            playButton.setEffect(new ImageInput(playButtonPressed));
        });
        playButton.setOnMouseReleased(event -> {
            playButton.setEffect(new ImageInput(playButtonImage));
        });
    }

    public void handlePlay(ActionEvent actionEvent) {

    }
}