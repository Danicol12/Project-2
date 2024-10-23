package com.example.project_2.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import com.example.project_2.model.SudokuMatrix;

public class Game {

    private SudokuMatrix sudokuMatrix;
    private int points=0;


    public Game() {
        sudokuMatrix = new SudokuMatrix();
        System.out.println(sudokuMatrix.getNumber(1,0));

    }

    public boolean isNumberCorrect(String number, int row, int col ) {
        return sudokuMatrix.getNumber(row, col).equals(number);
    }


}




