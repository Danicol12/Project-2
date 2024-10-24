package com.example.project_2.view;

import com.example.project_2.Main;
import com.example.project_2.controller.WelcomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The WelcomeStage class represents the welcome window for the Sudoku game.
 * It extends the Stage class from JavaFX and initializes the welcome interface.
 */
public class WelcomeStage extends Stage {

    /** The controller for the welcome interface, handling interactions and transitions. */
    private WelcomeController welcomeController;

    /**
     * Constructor for the WelcomeStage class that initializes the welcome window.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public WelcomeStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml")); // Load the FXML file
        Parent root = fxmlLoader.load(); // Load the root node from the FXML
        Scene scene = new Scene(root); // Create a new scene with the loaded root
        setTitle("Sudoku melardo"); // Set the title of the window
        setScene(scene); // Set the scene for the stage
        show(); // Display the stage
        getIcons().add(new Image(getClass().getResource("/com/example/project_2/images/sudoku.png").toExternalForm())); // Set the window icon
        setWidth(800); // Set the width of the window
        setHeight(444); // Set the height of the window
        setResizable(false); // Make the window non-resizable
    }

    /**
     * Gets the WelcomeController associated with this WelcomeStage.
     *
     * @return the WelcomeController for the welcome stage.
     */
    public WelcomeController getGameController() {
        return welcomeController;
    }

    /**
     * Inner static class to hold the singleton instance of WelcomeStage.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE; // Singleton instance of WelcomeStage
    }

    /**
     * Closes the current instance of WelcomeStage and sets it to null.
     */
    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close(); // Close the stage
        WelcomeStageHolder.INSTANCE = null; // Clear the instance
    }

    /**
     * Gets the singleton instance of WelcomeStage, creating it if necessary.
     *
     * @return the singleton instance of WelcomeStage.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static WelcomeStage getInstance() throws IOException {
        WelcomeStage.WelcomeStageHolder.INSTANCE =
                WelcomeStage.WelcomeStageHolder.INSTANCE != null ? WelcomeStage.WelcomeStageHolder.INSTANCE : new WelcomeStage();
        return WelcomeStage.WelcomeStageHolder.INSTANCE;
    }
}
