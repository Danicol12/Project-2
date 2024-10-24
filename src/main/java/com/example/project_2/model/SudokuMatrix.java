package com.example.project_2.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SudokuMatrix extends AMethods{

    private ArrayList<String>[] SudokuMatrix;
    private int[] auxNumbers;
    private Random rand;
    private int intialNumbers[][];

    public SudokuMatrix() {
        SudokuMatrix = new ArrayList[6];
        auxNumbers = new int[6];
        intialNumbers = new int[6][6];
        for (int i=0; i<6; i++) {
            for (int j=0; j<6; j++) {
                intialNumbers[i][j] = 0;
            }
        }
        rand = new Random();
        for (int i = 0; i < 6; i++) {
            SudokuMatrix[i] = new ArrayList<>();
        }
        setInitialNumbers();
        printInitialNumbers();
        fillAuxNumbers();
        setSudokuMatrixValue();
        System.out.println("\n");
        showMatrix();
    }

    public void setInitialNumbers() {
        int valAux=0;
        int rand1=0;
        int rand2=0;

        for (int i = 0; i < 6; i += 2) {
            for (int j = 0; j < 6; j += 3) {

                valAux = 0;
                while (valAux < 2) {
                    rand1 = i + rand.nextInt(2); // Genera fila aleatoria dentro del bloque 2x3
                    rand2 = j + rand.nextInt(3); // Genera columna aleatoria dentro del bloque 2x3

                    if (intialNumbers[rand1][rand2] == 0) { // Si la casilla está vacía (es cero)
                        intialNumbers[rand1][rand2]= 1;   // Coloca un '1'
                        valAux++;
                    }
                }
            }
        }
    }
    public void printInitialNumbers() {
        for (int i = 0; i < 6; i++) {
            System.out.println("\n");
            for (int j = 0; j < 6; j++) {
                System.out.print(intialNumbers[i][j] + " ");
            }
        }
    }

    public void setSudokuMatrixValue() {
        String stringAux;
        int intAux;


        for (int i = 0; i < 6; i++) {
            this.SudokuMatrix[i].clear();
            for (int j = 0; j < 6; j++) {
                this.SudokuMatrix[i].add("0");
            }
        }


        if (!fillSudoku(0, 0)) {
            System.out.println("Error al generar el Sudoku");
        }
    }



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

    public boolean checkIfRightNumber(int number, int column, int row) {
        String numStr = Integer.toString(number);
        for (int i = 0; i < 6; i++) {
            if (Objects.equals(this.SudokuMatrix[row].get(i), numStr)) {
                return false;
            }
        }


        for (int i = 0; i < 6; i++) {
            if (Objects.equals(this.SudokuMatrix[i].get(column), numStr)) {
                return false;}}
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

    public void showMatrix() {
        for (int i = 0; i < this.SudokuMatrix.length; i++) {
            System.out.println(SudokuMatrix[i]);
        }
    }

    public String getNumber(int row, int col) {return this.SudokuMatrix[row].get(col);}


    public void fillAuxNumbers() {
        for (int i = 0; i < 6; i++) {
            auxNumbers[i] = i + 1;
        }
    }
    public int getInitialNumber(int row, int col) {return intialNumbers[row][col];}
}

