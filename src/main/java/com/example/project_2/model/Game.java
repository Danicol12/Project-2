package com.example.project_2.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import com.example.project_2.model.SudokuMatrix;

public class Game {

    private SudokuMatrix sudokuMatrix;
    private int points=0;
    private int hintNumber=0;
    private int remainingLives=5;
    private int gameStatus=0; // 1 es ganar, 2 es perder




    public Game() {
        sudokuMatrix = new SudokuMatrix();
        System.out.println(sudokuMatrix.getNumber(1,0));
        System.out.println(isNumberCorrect("2",0,0));


    }

    public boolean isNumberCorrect(String number, int row, int col ) {
        return sudokuMatrix.getNumber(row, col).equals(number);
    }
    public void checkWinCondition() {
        if(points==24){gameStatus=1;}
    }
    public boolean numberComprobation(String word) {

        String regex = "^[1-6]+$";
        //munero correcto
        if (word.matches(regex)) {
            System.out.println("Es apto");return true;}
        //Caracter incorrecto, no cobrar vida
        else {
            System.out.println("no es apto"); return false;}

    }

    public int   getPoints(){return points;}
    public void setPoints(int points) {this.points = points;}
    public int getGameStatus() {return gameStatus;}
    public int getHintNumber() {return hintNumber;}
    public int getRemainingLives() {return remainingLives;}
    public void setRemainingLives(int remainingLives) {this.remainingLives = remainingLives;}
    public void setHintNumber(int hintNumber) {this.hintNumber = hintNumber;}
    public int getInitialNumber(int row, int col) {return sudokuMatrix.getInitialNumber(row, col);}
    public String getNumber(int row, int col){return sudokuMatrix.getNumber(row,col);}


}




