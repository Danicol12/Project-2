package com.example.project_2.controller;

import com.example.project_2.view.GameStage;
import com.example.project_2.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    private Label remainingLivesTxt;

    @FXML
    private Button hintButton;

    @FXML
    private Label messageLabel;

    int hintCounter = 0;
    int mistakesCounter = 0;


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

        remainingLivesTxt.setEffect(new ImageInput(new Image(getClass().getResource("/com/example/project_2/images/remaining-lives.png").toExternalForm())));

        Image hintImage = new Image(getClass().getResource("/com/example/project_2/images/hint-button.png").toExternalForm());
        Image hintPressed = new Image(getClass().getResource("/com/example/project_2/images/pressed-hint.png").toExternalForm());
        hintButton.setEffect(new ImageInput(hintImage));

        hintButton.setOnMousePressed(event -> {
            hintButton.setEffect(new ImageInput(hintPressed));
        });
        hintButton.setOnMouseReleased(event -> {
            hintButton.setEffect(new ImageInput(hintImage));
        });

        messageLabel.setWrapText(true);
    }

    public void returnAction() throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    public void hintAction()  {
        hintCounter++;
        if (hintCounter > 3) {
            messageLabel.setText("Ups... parece que no tienes más pistas");
        } else if (hintCounter == 3) {
            messageLabel.setText("Esa fué tu última pista :(");
        } else if (hintCounter == 2) {
            messageLabel.setText("Te queda solo una pista...");
        } else if (hintCounter == 1) {
            messageLabel.setText("Todavía tienes dos pistas");
        }


    }

}