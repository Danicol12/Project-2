package com.example.project_2.controller;

import com.example.project_2.view.GameStage;
import com.example.project_2.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The WelcomeController class manages the user interface for the welcome screen of the game.
 * It handles the initialization of the welcome image, the play button, and the transition
 * to the game stage when the play button is clicked.
 */
public class WelcomeController {

    @FXML
    private Button playButton;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private ImageView welcomeImageView;

    /**
     * Initializes the welcome screen UI components. It sets the background image for
     * the welcome screen and adds visual feedback for the play button when it is pressed or released.
     */
    public void initialize() {
        // Set the background image for the welcome screen
        Image welcomeImage = new Image(getClass().getResource("/com/example/project_2/images/welcome-bg.jpg").toExternalForm());
        welcomeImageView.setImage(welcomeImage);

        // Set the images for the play button (normal and pressed states)
        Image playButtonPressed = new Image(getClass().getResource("/com/example/project_2/images/play-button-pressed.png").toExternalForm());
        Image playButtonImage = new Image(getClass().getResource("/com/example/project_2/images/play-button.png").toExternalForm());
        playButton.setEffect(new ImageInput(playButtonImage));

        // Change the play button's image when pressed
        playButton.setOnMousePressed(event -> {
            playButton.setEffect(new ImageInput(playButtonPressed));
        });

        // Revert the play button's image when released
        playButton.setOnMouseReleased(event -> {
            playButton.setEffect(new ImageInput(playButtonImage));
        });
    }

    /**
     * Handles the action when the play button is clicked. It transitions from the welcome
     * screen to the game screen by creating the game stage and removing the welcome stage.
     *
     * @param actionEvent the event triggered by the play button click.
     * @throws IOException if an error occurs during stage transition.
     */
    public void handlePlay(ActionEvent actionEvent) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }
}
