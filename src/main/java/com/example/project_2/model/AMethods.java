package com.example.project_2.model;

import java.io.Serializable;

/**
 * The AMethods class provides empty or default implementations for the Methods interface.
 * This class serves as a base class or a placeholder for subclasses that will provide
 * concrete implementations of the various methods related to the game's logic, such as
 * points, game status, hints, remaining lives, and number validation.
 */
public class AMethods implements Methods {

    /**
     * Gets the current points in the game. This implementation always returns 0.
     *
     * @return the current points, always 0 in this case.
     */
    @Override
    public int getPoints() {
        return 0;
    }

    /**
     * Sets the current points in the game. This method does nothing in this implementation.
     *
     * @param points the points to be set.
     */
    @Override
    public void setPoints(int points) {}

    /**
     * Gets the current game status. This implementation always returns 0.
     *
     * @return the current game status, always 0 in this case.
     */
    @Override
    public int getGameStatus() {
        return 0;
    }

    /**
     * Gets the current number of hints used. This implementation always returns 0.
     *
     * @return the number of hints used, always 0 in this case.
     */
    @Override
    public int getHintNumber() {
        return 0;
    }

    /**
     * Gets the remaining lives of the player. This implementation always returns 0.
     *
     * @return the remaining lives, always 0 in this case.
     */
    @Override
    public int getRemainingLives() {
        return 0;
    }

    /**
     * Sets the remaining lives of the player. This method does nothing in this implementation.
     *
     * @param remainingLives the remaining lives to be set.
     */
    @Override
    public void setRemainingLives(int remainingLives) {}

    /**
     * Sets the number of hints used. This method does nothing in this implementation.
     *
     * @param hintNumber the number of hints to be set.
     */
    @Override
    public void setHintNumber(int hintNumber) {}

    /**
     * Gets the initial number from the game's matrix at the specified row and column.
     * This implementation always returns 0.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the initial number at the specified position, always 0 in this case.
     */
    @Override
    public int getInitialNumber(int row, int col) {
        return 0;
    }

    /**
     * Gets the number from the game's matrix at the specified row and column.
     * This implementation always returns an empty string.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the number at the specified position, always an empty string in this case.
     */
    @Override
    public String getNumber(int row, int col) {
        return "";
    }

    /**
     * Checks whether the provided number is correct for the specified row and column.
     * This implementation always returns false.
     *
     * @param number the number to be checked.
     * @param row the row index.
     * @param col the column index.
     * @return true if the number is correct, always false in this case.
     */
    @Override
    public boolean isNumberCorrect(String number, int row, int col) {
        return false;
    }

    /**
     * Checks the win condition for the game. This method does nothing in this implementation.
     */
    @Override
    public void checkWinCondition() {}

    /**
     * Validates if the provided string is a valid number for the game. This implementation always returns false.
     *
     * @param word the string to be checked.
     * @return true if the string is valid, always false in this case.
     */
    @Override
    public boolean numberComprobation(String word) {
        return false;
    }

    /**
     * Displays the game's matrix. This method does nothing in this implementation.
     */
    @Override
    public void showMatrix() {}

    /**
     * Fills auxiliary numbers in the game's matrix. This method does nothing in this implementation.
     */
    @Override
    public void fillAuxNumbers() {}
}
