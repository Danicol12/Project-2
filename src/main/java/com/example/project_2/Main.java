package com.example.project_2;

import com.example.project_2.model.Game;
import com.example.project_2.view.WelcomeStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        WelcomeStage.getInstance();
    }
}