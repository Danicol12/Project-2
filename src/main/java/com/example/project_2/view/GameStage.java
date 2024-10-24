package com.example.project_2.view;

import com.example.project_2.Main;
import com.example.project_2.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The GameStage class represents the main game window for the Sudoku game.
 * It extends the Stage class from JavaFX and initializes the game interface.
 */
public class GameStage extends Stage {
    /** The controller for the game interface, handling the game logic and interactions. */
    private GameController gameController;

    /**
     * Constructor for the GameStage class that initializes the game window.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public GameStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
        Parent root = fxmlLoader.load(); // Load the FXML file for the game view
        gameController = fxmlLoader.getController(); // Get the GameController from the FXML loader
        Scene Scene2 = new Scene(root); // Create a new scene with the loaded root
        setScene(Scene2); // Set the scene for the stage
        show(); // Display the stage
        setTitle("Sudoku melardo"); // Set the title of the window
        getIcons().add(new Image(getClass().getResource("/com/example/project_2/images/sudoku.png").toExternalForm())); // Set the window icon
        setResizable(false); // Make the window non-resizable
    }

    /**
     * Gets the GameController associated with this GameStage.
     *
     * @return the GameController for the game stage.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Inner static class to hold the singleton instance of GameStage.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE; // Singleton instance of GameStage
    }

    /**
     * Gets the singleton instance of GameStage, creating it if necessary.
     *
     * @return the singleton instance of GameStage.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static GameStage getInstance() throws IOException {
        GameStage.GameStageHolder.INSTANCE =
                GameStage.GameStageHolder.INSTANCE != null ? GameStage.GameStageHolder.INSTANCE : new GameStage();
        return GameStage.GameStageHolder.INSTANCE;
    }

    /**
     * Closes the current instance of GameStage and sets it to null.
     */
    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close(); // Close the stage
        GameStage.GameStageHolder.INSTANCE = null; // Clear the instance
    }
}
