package com.example.project_2.model;

/**
 * The Methods interface defines a contract for managing the core mechanics of a Sudoku-like game.
 * It includes methods for handling game points, status, hints, lives, and the game matrix.
 */
public interface Methods {

    /**
     * Gets the player's current points.
     *
     * @return the player's points.
     */
    int getPoints();

    /**
     * Sets the player's current points.
     *
     * @param points the points to be set.
     */
    void setPoints(int points);

    /**
     * Gets the current game status, where 1 represents a win, and 2 represents a loss.
     *
     * @return the current game status.
     */
    int getGameStatus();

    /**
     * Gets the number of hints used by the player.
     *
     * @return the number of hints used.
     */
    int getHintNumber();

    /**
     * Gets the number of remaining lives of the player.
     *
     * @return the remaining lives.
     */
    int getRemainingLives();

    /**
     * Sets the number of remaining lives for the player.
     *
     * @param remainingLives the remaining lives to be set.
     */
    void setRemainingLives(int remainingLives);

    /**
     * Sets the number of hints used by the player.
     *
     * @param hintNumber the number of hints to be set.
     */
    void setHintNumber(int hintNumber);

    /**
     * Gets the initial number at the specified position (row, column) in the game matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the initial number at the specified position.
     */
    int getInitialNumber(int row, int col);

    /**
     * Gets the number at the specified position (row, column) in the game matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the number at the specified position.
     */
    String getNumber(int row, int col);

    /**
     * Checks if the provided number is correct for the given position (row, column).
     *
     * @param number the number to be checked.
     * @param row the row index.
     * @param col the column index.
     * @return true if the number is correct, false otherwise.
     */
    boolean isNumberCorrect(String number, int row, int col);

    /**
     * Checks if the player has met the win or lose conditions and updates the game status accordingly.
     */
    void checkWinCondition();

    /**
     * Validates if the provided input is a valid number for the game.
     *
     * @param word the string input to be validated.
     * @return true if the input is a valid number, false otherwise.
     */
    boolean numberComprobation(String word);

    /**
     * Displays the current game matrix in a human-readable format.
     */
    void showMatrix();

    /**
     * Fills the auxiliary numbers used in the game matrix, possibly for game logic or hints.
     */
    void fillAuxNumbers();
}
