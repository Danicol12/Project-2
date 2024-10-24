package com.example.project_2.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * The SudokuMatrix class represents a 6x6 Sudoku-like game matrix, providing methods for generating
 * the matrix, filling it with random values according to the game's rules, and displaying the matrix.
 */
public class SudokuMatrix extends AMethods {

    /** Array of ArrayLists to store the game matrix values. */
    private ArrayList<String>[] SudokuMatrix;

    /** Array to store auxiliary numbers used in the game. */
    private int[] auxNumbers;

    /** Random object to generate random numbers. */
    private Random rand;

    /** 2D array to store the initial values placed in the matrix. */
    private int intialNumbers[][];

    /**
     * Constructor for SudokuMatrix that initializes the matrix, fills it with initial numbers,
     * and populates it with random values according to Sudoku rules.
     */
    public SudokuMatrix() {
        SudokuMatrix = new ArrayList[6];
        auxNumbers = new int[6];
        intialNumbers = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                intialNumbers[i][j] = 0; // Initialize the initial numbers matrix with zeros
            }
        }
        rand = new Random(); // Initialize the random number generator
        for (int i = 0; i < 6; i++) {
            SudokuMatrix[i] = new ArrayList<>();
        }
        setInitialNumbers(); // Set initial numbers in the matrix
        printInitialNumbers(); // Print the initial matrix values
        fillAuxNumbers(); // Fill auxiliary numbers used for Sudoku
        setSudokuMatrixValue(); // Fill the Sudoku matrix with random values
        System.out.println("\n");
        showMatrix(); // Display the current state of the matrix
    }

    /**
     * Sets the initial numbers in the matrix, ensuring each block gets two '1's.
     */
    public void setInitialNumbers() {
        int valAux = 0;
        int rand1 = 0;
        int rand2 = 0;

        for (int i = 0; i < 6; i += 2) {
            for (int j = 0; j < 6; j += 3) {

                valAux = 0;
                while (valAux < 2) {
                    rand1 = i + rand.nextInt(2); // Generate random row within the 2x3 block
                    rand2 = j + rand.nextInt(3); // Generate random column within the 2x3 block

                    if (intialNumbers[rand1][rand2] == 0) { // If the cell is empty (zero)
                        intialNumbers[rand1][rand2] = 1;   // Place a '1'
                        valAux++;
                    }
                }
            }
        }
    }

    /**
     * Prints the initial number matrix to the console.
     */
    public void printInitialNumbers() {
        for (int i = 0; i < 6; i++) {
            System.out.println("\n");
            for (int j = 0; j < 6; j++) {
                System.out.print(intialNumbers[i][j] + " ");
            }
        }
    }

    /**
     * Sets the values in the Sudoku matrix by filling it with random numbers.
     * Uses the fillSudoku method to ensure the numbers follow Sudoku rules.
     */
    public void setSudokuMatrixValue() {
        for (int i = 0; i < 6; i++) {
            this.SudokuMatrix[i].clear();
            for (int j = 0; j < 6; j++) {
                this.SudokuMatrix[i].add("0"); // Initialize all cells to zero
            }
        }

        if (!fillSudoku(0, 0)) {
            System.out.println("Error al generar el Sudoku");
        }
    }

    /**
     * Recursively fills the Sudoku matrix by placing random numbers that meet Sudoku rules.
     *
     * @param row the current row.
     * @param col the current column.
     * @return true if the matrix was filled successfully, false otherwise.
     */
    private boolean fillSudoku(int row, int col) {
        if (row == 6) {
            return true;
        }
        int nextRow = (col == 5) ? row + 1 : row;
        int nextCol = (col == 5) ? 0 : col + 1;

        fillAuxNumbers();
        for (int i = 0; i < 6; i++) {
            int num = rand.nextInt(6) + 1;
            if (checkIfRightNumber(num, col, row)) {
                this.SudokuMatrix[row].set(col, Integer.toString(num));
                if (fillSudoku(nextRow, nextCol)) {
                    return true;
                }
                this.SudokuMatrix[row].set(col, "0");
            }
        }
        return false;
    }

    /**
     * Checks whether the number is valid to place in the given row and column, based on Sudoku rules.
     *
     * @param number the number to check.
     * @param column the column index.
     * @param row the row index.
     * @return true if the number is valid, false otherwise.
     */
    public boolean checkIfRightNumber(int number, int column, int row) {
        String numStr = Integer.toString(number);

        // Check if the number already exists in the row
        for (int i = 0; i < 6; i++) {
            if (Objects.equals(this.SudokuMatrix[row].get(i), numStr)) {
                return false;
            }
        }

        // Check if the number already exists in the column
        for (int i = 0; i < 6; i++) {
            if (Objects.equals(this.SudokuMatrix[i].get(column), numStr)) {
                return false;
            }
        }

        // Check if the number already exists in the 2x3 block
        int startRow = (row / 2) * 2;
        int startCol = (column / 3) * 3;
        for (int i = startRow; i < startRow + 2; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (Objects.equals(this.SudokuMatrix[i].get(j), numStr)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Displays the current state of the Sudoku matrix.
     */
    public void showMatrix() {
        for (int i = 0; i < this.SudokuMatrix.length; i++) {
            System.out.println(SudokuMatrix[i]);
        }
    }

    /**
     * Gets the number at the specified row and column in the matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the number as a string at the specified position.
     */
    public String getNumber(int row, int col) {
        return this.SudokuMatrix[row].get(col);
    }

    /**
     * Fills the auxiliary numbers array with numbers from 1 to 6.
     */
    public void fillAuxNumbers() {
        for (int i = 0; i < 6; i++) {
            auxNumbers[i] = i + 1;
        }
    }

    /**
     * Gets the initial number at the specified position in the initial matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the initial number at the specified position.
     */
    public int getInitialNumber(int row, int col) {
        return intialNumbers[row][col];
    }
}
