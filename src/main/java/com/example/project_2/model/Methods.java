package com.example.project_2.model;

public interface Methods {
    int  getPoints();
    void setPoints(int points);
    int getGameStatus() ;
    int getHintNumber();
    int getRemainingLives();
    void setRemainingLives(int remainingLives);
    void setHintNumber(int hintNumber);
    int getInitialNumber(int row, int col);
    String getNumber(int row, int col);
    boolean isNumberCorrect(String number, int row, int col );
    void checkWinCondition();
    boolean numberComprobation(String word);
    void showMatrix() ;
    void fillAuxNumbers();
}
