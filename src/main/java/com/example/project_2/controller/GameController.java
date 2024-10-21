package com.example.project_2.controller;

import com.example.project_2.view.GameStage;
import com.example.project_2.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class GameController {

    @FXML
    private ImageView gameImageView;

    @FXML
    private Button returnButton;

    @FXML
    private ImageView livesImageView;

    public void initialize() {
        Image gameImage = new Image(getClass().getResource("/com/example/project_2/images/game-bg.png").toExternalForm());
        gameImageView.setImage(gameImage);

        Image returnImagePressed = new Image(getClass().getResource(("/com/example/project_2/images/return-button-pressed.png")).toExternalForm());
        Image returnImage = new Image(getClass().getResource(("/com/example/project_2/images/return-button.png")).toExternalForm());
        returnButton.setEffect(new ImageInput(returnImage));
        returnButton.setOnMousePressed(event -> {
            returnButton.setEffect(new ImageInput(returnImagePressed));
        });
        returnButton.setOnMouseReleased(event -> {
            returnButton.setEffect(new ImageInput(returnImage));
        });

        Image fiveLives = new Image(getClass().getResource("/com/example/project_2/images/5-lives.png").toExternalForm());
        livesImageView.setImage(fiveLives);
    }

    public void returnAction() throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

}