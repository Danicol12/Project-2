package com.example.project_2.controller;

import com.example.project_2.model.Game;
import com.example.project_2.view.GameStage;
import com.example.project_2.view.WelcomeStage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * The GameController class handles the user interface and game logic for a Sudoku-like game.
 * It manages the game's display, user input, remaining lives, and game state, such as
 * win or lose conditions. The controller initializes the game's UI elements and interacts
 * with the model to perform various game-related operations.
 */
public class GameController {

    @FXML
    private ImageView gameImageView;
    @FXML
    private GridPane gridPane;
    private Game game;
    private TextField[][] txt;

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

    @FXML
    private ImageView sudokuBg;

    private Random rand;

    /**
     * Initializes the game controller, setting up the game UI, images, and event listeners.
     * It creates the text fields for the game grid, initializes game images, and sets up
     * button behaviors for hint and return actions.
     */
    public void initialize() {
        game = new Game();
        rand = new Random();
        txt = new TextField[6][6];
        Image gameImage = new Image(getClass().getResource("/com/example/project_2/images/game-bg.png").toExternalForm());
        gameImageView.setImage(gameImage);
        createTextField();

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

        sudokuBg.setImage(new Image(getClass().getResource("/com/example/project_2/images/sudoku-bg.png").toExternalForm()));
    }

    /**
     * Creates a grid of TextFields to represent the game board. Each TextField is
     * initialized with styling and event listeners for user input. If a number is predefined
     * on the board, the TextField is populated and set to non-editable.
     */
    public void createTextField() {
        for (int i = 0; i < 6; i++) {
            for (int f = 0; f < 6; f++) {
                txt[i][f] = new TextField();
                txt[i][f].setStyle("-fx-background-color: transparent; -fx-text-fill: black;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                onKeyTxtPressed(txt[i][f], i, f);
                focusedProperty(txt[i][f], i, f);
                gridPane.add(txt[i][f], f, i);
                if (game.getInitialNumber(i, f) == 1) {
                    txt[i][f].setText(game.getNumber(i, f));
                    txt[i][f].setStyle("-fx-background-color: transparent; -fx-text-fill: #00ff00;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                    txt[i][f].setEditable(false);
                }
            }
        }
    }

    /**
     * Handles the return button action to return to the welcome stage.
     * Deletes the current game stage instance and reverts to the welcome screen.
     *
     * @throws IOException if the stage fails to load.
     */
    public void returnAction() throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    /**
     * Updates the life indicators in the UI based on the player's remaining lives.
     * Changes the displayed image to reflect the current number of lives.
     */
    public void heartsChange() {
        if (game.getRemainingLives() == 4) {
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/4-lives.png").toExternalForm()));
        }
        if (game.getRemainingLives() == 3) {
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/3-lives.png").toExternalForm()));
        }
        if (game.getRemainingLives() == 2) {
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/2-lives.png").toExternalForm()));
        }
        if (game.getRemainingLives() == 1) {
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/1-live.png").toExternalForm()));
        }
        if (game.getRemainingLives() == 0) {
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/0-lives.png").toExternalForm()));
        }
    }

    /**
     * Event handler that processes key input in the game grid. It validates the input,
     * checks if the entered number is correct, and updates the game state accordingly.
     * The game checks if the player has won or lost after each input.
     *
     * @param txt the TextField being modified.
     * @param row the row index of the TextField.
     * @param col the column index of the TextField.
     */
    private void onKeyTxtPressed(final TextField txt, final int row, final int col) {
        txt.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                int length = txt.getText().length();
                if (game.getRemainingLives() != 0 && !keyEvent.getText().isEmpty() && txt.isEditable()) {
                    if (length != 1) {
                        messageLabel.setText("Ingresa solo un caracter");
                        txt.setText("");
                        return;
                    }
                    if (game.numberComprobation(keyEvent.getText())) {
                        if (game.isNumberCorrect(keyEvent.getText(), row, col)) {
                            txt.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #00ff00;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                            txt.setEditable(false);
                            game.setPoints(game.getPoints() + 1);
                            game.checkWinCondition();
                            setWinOrLose();
                        } else {
                            txt.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #ff0000;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                            game.setRemainingLives(game.getRemainingLives() - 1);
                            heartsChange();
                            game.checkWinCondition();
                            if (game.getGameStatus() == 2) {
                                blockAllTxt();
                            }
                            setWinOrLose();
                        }
                    } else {
                        txt.setText("");
                    }
                }
            }
        });
    }

    /**
     * Adds a listener to handle focus events for each TextField. Highlights the row and
     * column of the currently selected TextField.
     *
     * @param textField the TextField being monitored.
     * @param fil the row index.
     * @param col the column index.
     */
    public void focusedProperty(TextField textField, int fil, int col) {
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                showRelatedSquares(fil, col);
            }
        });
    }

    /**
     * Highlights the row and column related to the selected TextField.
     *
     * @param row the row index.
     * @param col the column index.
     */
    public void showRelatedSquares(int row, int col) {
        for (int i = 0; i < 6; i++) {
            for (int f = 0; f < 6; f++) {
                txt[i][f].setBackground(new Background(new BackgroundFill(Color.rgb(199, 181, 175, 0.0), CornerRadii.EMPTY, null)));
            }
        }
        for (int i = 0; i < 6; i++) {
            txt[row][i].setBackground(new Background(new BackgroundFill(Color.rgb(199, 181, 175, 0.4), CornerRadii.EMPTY, null)));
        }
        for (int i = 0; i < 6; i++) {
            txt[i][col].setBackground(new Background(new BackgroundFill(Color.rgb(199, 181, 175, 0.4), CornerRadii.EMPTY, null)));
        }
    }

    /**
     * Displays a win or lose message depending on the game status. If the player wins,
     * a trophy image is displayed. If the player loses, a losing message is shown.
     */
    public void setWinOrLose() {
        if (game.getGameStatus() == 1) {
            messageLabel.setText("Ganaste el juego!!!");
            livesImageView.setFitHeight(157);
            livesImageView.setFitWidth(200);
            remainingLivesTxt.setEffect(null);
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/trophy.png").toExternalForm()));
            messageLabel.setLayoutY(295);
        } else if (game.getGameStatus() == 2) {
            messageLabel.setText("Perdiste :(");
        }
    }

    /**
     * Disables all TextFields on the game board, preventing further input when the game
     * is lost.
     */
    public void blockAllTxt() {
        for (int i = 0; i < 6; i++) {
            for (int f = 0; f < 6; f++) {
                txt[i][f].setEditable(false);
            }
        }
    }

    /**
     * Provides a hint to the player by revealing one random correct number. Players are
     * allowed up to three hints. The game tracks the number of hints used.
     */
    public void hintAction() {
        if (game.getGameStatus() != 1 && game.getGameStatus() != 2) {
            int rand1, rand2;
            if (game.getHintNumber() < 3) {
                game.setPoints(game.getPoints() + 1);
                do {
                    rand1 = rand.nextInt(6);
                    rand2 = rand.nextInt(6);
                } while (!txt[rand1][rand2].getText().isEmpty());
                txt[rand1][rand2].setText(game.getNumber(rand1, rand2));
                txt[rand1][rand2].setEditable(false);
                txt[rand1][rand2].setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #00ff00;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                game.setHintNumber(game.getHintNumber() + 1);
                switch (game.getHintNumber()) {
                    case 1:
                        messageLabel.setText("Todavía tienes dos pistas");
                        break;
                    case 2:
                        messageLabel.setText("Te queda solo una pista...");
                        break;
                    case 3:
                        messageLabel.setText("Esa fue tu última pista :(");
                        break;
                }
            } else {
                messageLabel.setText("Ups... Parece que no te quedan más pistas");
            }
        }
    }
}






