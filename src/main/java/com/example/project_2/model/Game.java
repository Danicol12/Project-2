package com.example.project_2.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import com.example.project_2.model.SudokuMatrix;

public class Game {

    private SudokuMatrix sudokuMatrix;
    private ArrayList<String>[] SudokuMatrix = new ArrayList[6];
    private int[] auxNumbers = new int[6];
    private Random rand = new Random();

    public Game() {
        sudokuMatrix = new SudokuMatrix();

    }
}




