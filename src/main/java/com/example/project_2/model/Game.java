package com.example.project_2.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import com.example.project_2.model.SudokuMatrix;

/**
 * The Game class manages the core logic and state of the Sudoku game, extending the AMethods class.
 * It tracks the player's points, number of hints used, remaining lives, and the overall game status
 * (win or lose). The game uses a 6x6 Sudoku matrix and offers methods to check for correct numbers,
 * validate input, and update the game status.
 */
public class Game extends AMethods {

    private SudokuMatrix sudokuMatrix; // Represents the game's Sudoku matrix
    private int points = 0; // Player's current points
    private int hintNumber = 0; // Number of hints used by the player
    private int remainingLives = 5; // Player's remaining lives
    private int gameStatus = 0; // 1 means win, 2 means lose

    /**
     * Constructs a new Game instance, initializing the Sudoku matrix and displaying
     * a number for debugging purposes.
     */
    public Game() {
        sudokuMatrix = new SudokuMatrix();
        System.out.println(sudokuMatrix.getNumber(1, 0));
        System.out.println(isNumberCorrect("2", 0, 0));
    }

    /**
     * Checks if the provided number is correct for the given position (row, column)
     * in the Sudoku matrix.
     *
     * @param number the number to be checked.
     * @param row the row index.
     * @param col the column index.
     * @return true if the number is correct, false otherwise.
     */
    public boolean isNumberCorrect(String number, int row, int col) {
        return sudokuMatrix.getNumber(row, col).equals(number);
    }

    /**
     * Checks the current win or lose condition of the game.
     * Sets the game status to 1 (win) if the player has accumulated 24 points,
     * or 2 (lose) if the player has no remaining lives.
     */
    public void checkWinCondition() {
        if (points == 24) {
            gameStatus = 1;
        } else if (remainingLives == 0) {
            gameStatus = 2;
        }
    }

    /**
     * Validates if the provided input is a valid number for the game,
     * specifically checking if the input is a number between 1 and 6.
     *
     * @param word the string input to be validated.
     * @return true if the input is a valid number, false otherwise.
     */
    public boolean numberComprobation(String word) {
        String regex = "^[1-6]+$";
        if (word.matches(regex)) {
            System.out.println("Es apto");
            return true;
        } else {
            System.out.println("no es apto");
            return false;
        }
    }

    /**
     * Gets the player's current points.
     *
     * @return the current points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the player's current points.
     *
     * @param points the points to be set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the current game status.
     *
     * @return the game status, where 1 means win and 2 means lose.
     */
    public int getGameStatus() {
        return gameStatus;
    }

    /**
     * Gets the number of hints used by the player.
     *
     * @return the number of hints used.
     */
    public int getHintNumber() {
        return hintNumber;
    }

    /**
     * Gets the remaining lives of the player.
     *
     * @return the remaining lives.
     */
    public int getRemainingLives() {
        return remainingLives;
    }

    /**
     * Sets the remaining lives of the player.
     *
     * @param remainingLives the number of remaining lives to be set.
     */
    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    /**
     * Sets the number of hints used by the player.
     *
     * @param hintNumber the number of hints to be set.
     */
    public void setHintNumber(int hintNumber) {
        this.hintNumber = hintNumber;
    }

    /**
     * Gets the initial number at the specified position (row, column) in the Sudoku matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the initial number at the specified position.
     */
    public int getInitialNumber(int row, int col) {
        return sudokuMatrix.getInitialNumber(row, col);
    }

    /**
     * Gets the current number at the specified position (row, column) in the Sudoku matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the number at the specified position.
     */
    public String getNumber(int row, int col) {
        return sudokuMatrix.getNumber(row, col);
    }
}




