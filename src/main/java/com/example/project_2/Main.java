package com.example.project_2;

import com.example.project_2.model.Game;
import com.example.project_2.view.WelcomeStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class serves as the entry point for the Sudoku game application.
 * It extends the JavaFX Application class to launch the GUI.
 */
public class Main extends Application {

    /**
     * The main method to start the application.
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(); // Launch the JavaFX application
    }

    /**
     * The start method is called after the application has been launched.
     * It initializes the game and displays the welcome stage.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if an error occurs during the initialization of the stage.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game(); // Initialize the game logic
        WelcomeStage.getInstance(); // Display the welcome stage
    }
}
