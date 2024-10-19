package com.example.project_2.model;

import java.util.ArrayList;

public class Game {


    private ArrayList<String>[] SudokuMatrix = new ArrayList[6];
    private int[] auxNumbers = new int[6];

    public Game() {
        for (int i = 0; i < 6; i++) {
            SudokuMatrix[i] = new ArrayList<>();
        }
        setSudokuMatrixValue();
        this.SudokuMatrix[0].set(1, "1");

    }


    public void setSudokuMatrixValue() {

        for (int i = 0; i < 6; i++) {
            this.SudokuMatrix[i].clear();
            for (int f = 0; f < 6; f++) {
                this.SudokuMatrix[i].add("0");
            }

        }
    }


    public void showMatrix() {
        for (int i = 0; i < this.SudokuMatrix.length; i++) {

            System.out.print(SudokuMatrix[i] + "\t");

            System.out.println();
        }
    }
    public void fillAuxNumbers() {for(int i=0; i<6; i++){auxNumbers[i]=i;}}
}



