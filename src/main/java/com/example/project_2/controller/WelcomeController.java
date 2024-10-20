package com.example.project_2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class WelcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
public void initialize() {

        Image welcomeImage = new Image(getClass().getResource("/com/example/project_2/images/welcome-bg.jpg").toExternalForm());
        welcomePane.setEffect(new ImageInput(welcomeImage));
    }

    public void handlePlay(ActionEvent actionEvent) {

    }
}