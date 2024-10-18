package com.example.project_2.model;

public class Game {

    private int[][] SudokuMatrix;

    public Game(){
        SudokuMatrix = new int[6][6];
    }
    public void showMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] +"\t");
            }
            System.out.println();
        }
    }
    public int[][] getSudokuMatrix(){
        return SudokuMatrix;
    }
}

