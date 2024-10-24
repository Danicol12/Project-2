package com.example.project_2.controller;

import com.example.project_2.model.Game;
import com.example.project_2.view.GameStage;
import com.example.project_2.view.WelcomeStage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Random;

public class GameController {

    @FXML
    private ImageView gameImageView;
    @FXML
    private GridPane gridPane;
    private Game game;
    private TextField[][] txt;

    @FXML
    private Button returnButton;

    @FXML
    private ImageView livesImageView;

    @FXML
    private Label remainingLivesTxt;

    @FXML
    private Button hintButton;

    @FXML
    private Label messageLabel;

    private Random rand;
    int hintCounter = 0;
    int mistakesCounter = 0;


    public void initialize() {
        game=new Game();
        rand = new Random();
        txt=new TextField[6][6];
        Image gameImage = new Image(getClass().getResource("/com/example/project_2/images/game-bg.png").toExternalForm());
        gameImageView.setImage(gameImage);
        createTextField();
        Image returnImagePressed = new Image(getClass().getResource(("/com/example/project_2/images/return-button-pressed.png")).toExternalForm());
        Image returnImage = new Image(getClass().getResource(("/com/example/project_2/images/return-button.png")).toExternalForm());
        returnButton.setEffect(new ImageInput(returnImage));
        returnButton.setOnMousePressed(event -> {
            returnButton.setEffect(new ImageInput(returnImagePressed));
        });
        returnButton.setOnMouseReleased(event -> {
            returnButton.setEffect(new ImageInput(returnImage));
        });

        Image fiveLives = new Image(getClass().getResource("/com/example/project_2/images/5-lives.png").toExternalForm());
        livesImageView.setImage(fiveLives);

        remainingLivesTxt.setEffect(new ImageInput(new Image(getClass().getResource("/com/example/project_2/images/remaining-lives.png").toExternalForm())));

        Image hintImage = new Image(getClass().getResource("/com/example/project_2/images/hint-button.png").toExternalForm());
        Image hintPressed = new Image(getClass().getResource("/com/example/project_2/images/pressed-hint.png").toExternalForm());
        hintButton.setEffect(new ImageInput(hintImage));

        hintButton.setOnMousePressed(event -> {
            hintButton.setEffect(new ImageInput(hintPressed));
        });
        hintButton.setOnMouseReleased(event -> {
            hintButton.setEffect(new ImageInput(hintImage));
        });

        messageLabel.setWrapText(true);
    }
    public void createTextField(){
        //Rows
        for(int i =0; i<6;i++) {
            //Columns
            for (int f = 0; f < 6; f++) {
                txt[i][f] = new TextField();
                onKeyTxtPressed(txt[i][f],i,f);
                gridPane.add(txt[i][f],f,i);
                if(game.getInitialNumber(i, f)==1){
                    txt[i][f].setText(game.getNumber(i, f));
                    txt[i][f].setEditable(false);
                }
            }
        }


    }

private void onKeyTxtPressed(final TextField txt, final int row, final int col) {

        txt.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
            if(game.getRemainingLives()!=0 && !keyEvent.getText().isEmpty() && txt.isEditable()){
                if(game.numberComprobation(keyEvent.getText())) {
                    System.out.println(txt.getText());
                    System.out.println("La fila es" + row + "La columna es" + col);
                    if (game.isNumberCorrect(keyEvent.getText(), row, col)) {
                        System.out.println("Son iguales");
                        txt.setStyle("-fx-background-color: green;");
                        txt.setEditable(false);
                        game.setPoints(game.getPoints()+1);
                        System.out.println("Llevas "+game.getPoints());
                        game.checkWinCondition();
                        if(game.getGameStatus()==1){
                            System.out.println("Ganaste");
                        } else if (game.getGameStatus()==2) {
                            System.out.println("Perdiste");
                        }

                        }
                    else if ((!game.isNumberCorrect(keyEvent.getText(), row, col)) && game.numberComprobation(keyEvent.getText())){
                        System.out.println("NO son iguales");
                        txt.setStyle("-fx-background-color: red;");
                        game.setRemainingLives(game.getRemainingLives()-1);
                        System.out.println("Llevas vidas: "+game.getRemainingLives());
                        game.checkWinCondition();
                        if(game.getGameStatus()==1){
                            System.out.println("Ganaste");
                        } else if (game.getGameStatus()==2) {
                            System.out.println("Perdiste");
                        }

                    }



                }
                else if (!game.numberComprobation(keyEvent.getText())) {
                    System.out.println("No son  iguales");
                    txt.setText("");
                }




            }

            }
        });
}

    public void returnAction() throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    public void hintAction()  {
        hintCounter++;
        int rand1;
        int rand2;
        do {
            rand1 =rand.nextInt(6);
            rand2=rand.nextInt(6);
        }while (!txt[rand1][rand2].getText().isEmpty());
        txt[rand1][rand2].setText(game.getNumber(rand1, rand2));
        txt[rand1][rand2].setEditable(false);
        txt[rand1][rand2].setStyle("-fx-background-color: green;");
        if (hintCounter > 3) {
            messageLabel.setText("Ups... parece que no tienes más pistas");
        } else if (hintCounter == 3) {
            messageLabel.setText("Esa fué tu última pista :(");
        } else if (hintCounter == 2) {
            messageLabel.setText("Te queda solo una pista...");
        } else if (hintCounter == 1) {
            messageLabel.setText("Todavía tienes dos pistas");
        }


    }

}