package com.example.project_2.model;

import java.io.Serializable;

public class AMethods implements Methods{
    @Override
    public int  getPoints(){return 0;}
    @Override
    public void setPoints(int points){}
    @Override
    public int getGameStatus(){return 0;}
    @Override
    public int getHintNumber(){return 0;}
    @Override
    public int getRemainingLives(){return 0;}
    @Override
    public void setRemainingLives(int remainingLives){}
    @Override
    public void setHintNumber(int hintNumber){}
    @Override
    public int getInitialNumber(int row, int col){return 0;}
    @Override
    public String getNumber(int row, int col){return "";}
    @Override
    public boolean isNumberCorrect(String number, int row, int col ){return false;}
    @Override
    public void checkWinCondition(){}
    @Override
    public boolean numberComprobation(String word){return false;}
    @Override
    public void showMatrix(){}
    @Override
    public void fillAuxNumbers(){}
}
