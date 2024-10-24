package com.example.project_2.controller;

import com.example.project_2.model.Game;
import com.example.project_2.view.GameStage;
import com.example.project_2.view.WelcomeStage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.*;
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

    @FXML
    private ImageView sudokuBg;

    private Random rand;

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

        sudokuBg.setImage(new Image(getClass().getResource("/com/example/project_2/images/sudoku-bg.png").toExternalForm()));
    }
    public void createTextField(){
        //Rows
        for(int i =0; i<6;i++) {
            //Columns
            for (int f = 0; f < 6; f++) {
                txt[i][f] = new TextField();
                txt[i][f].setStyle("-fx-background-color: transparent; -fx-text-fill: black;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                onKeyTxtPressed(txt[i][f],i,f);
                focusedProperty(txt[i][f],i,f);
                gridPane.add(txt[i][f],f,i);
                if(game.getInitialNumber(i, f)==1){
                    txt[i][f].setText(game.getNumber(i, f));
                    txt[i][f].setEditable(false);
                }
            }
        }


    }
    public void returnAction() throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }








    public void heartsChange(){
        if(game.getRemainingLives()== 4){
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/4-lives.png").toExternalForm()));
        }
        if(game.getRemainingLives()== 3){
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/3-lives.png").toExternalForm()));
        }
        if(game.getRemainingLives()== 2){
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/2-lives.png").toExternalForm()));
        }
        if(game.getRemainingLives()== 1){
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/1-live.png").toExternalForm()));
        }
        if(game.getRemainingLives()== 0){
            livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/0-lives.png").toExternalForm()));
        }
    }








private void onKeyTxtPressed(final TextField txt, final int row, final int col) {

    txt.setOnKeyReleased(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent keyEvent) {
            int lenght = txt.getText().length();
            if (game.getRemainingLives() != 0 && !keyEvent.getText().isEmpty() && txt.isEditable()) {
                if (lenght != 1) {
                    messageLabel.setText("Ingresa solo un caracter");
                    txt.setText("");
                }
                if (game.numberComprobation(keyEvent.getText())) {
                    System.out.println(txt.getText());
                    System.out.println("La fila es" + row + "La columna es" + col);
                    if (game.isNumberCorrect(keyEvent.getText(), row, col)) {
                        System.out.println("Son iguales");
                        txt.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #00ff00;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                        txt.setEditable(false);
                        game.setPoints(game.getPoints() + 1);
                        System.out.println("Llevas " + game.getPoints());
                        game.checkWinCondition();
                        if (game.getGameStatus() == 1) {
                            System.out.println("Ganaste");
                        }
                        setWinOrLose();

                    } else if ((!game.isNumberCorrect(keyEvent.getText(), row, col)) && game.numberComprobation(keyEvent.getText())) {
                        System.out.println("NO son iguales");
                        txt.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #ff0000;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
                        game.setRemainingLives(game.getRemainingLives() - 1);
                        System.out.println("Llevas vidas: " + game.getRemainingLives());
                        heartsChange();
                        game.checkWinCondition();
                        if (game.getGameStatus() == 2) {
                            System.out.println("Perdiste");
                        }
                        setWinOrLose();

                    }


                } else if (!game.numberComprobation(keyEvent.getText())) {
                    System.out.println("No son  iguales");
                      txt.setText("");
                }



            }
        }


    });
}
public void focusedProperty(TextField textField, int fil, int col) {
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            showRelatedSquares(fil, col);
            }
        });
}
public void showRelatedSquares(int row,int col){

    for(int i =0; i<6;i++) {
        //Columns
        for (int f = 0; f < 6; f++) {
            txt[i][f].setBackground((new Background(new BackgroundFill(Color.rgb(199, 181, 175, 0.0), CornerRadii.EMPTY, null))));
        }
    }
        for(int i=0;i<6;i++){
           //txt[i][row].setStyle("-fx-background-color: red;");
            txt[row][i].setBackground((new Background(new BackgroundFill(Color.rgb(199, 181, 175, 0.4), CornerRadii.EMPTY, null))));
        }
    for(int i=0;i<6;i++){
        txt[i][col].setBackground((new Background(new BackgroundFill(Color.rgb(199, 181, 175, 0.4), CornerRadii.EMPTY, null))));
    }


}
public void setWinOrLose(){
        if (game.getGameStatus() == 1) {
        messageLabel.setText("Ganaste el juego ");
        livesImageView.setFitHeight(157);
        livesImageView.setFitWidth(200);
        remainingLivesTxt.setEffect(null);
        livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/trophy.png").toExternalForm()));
        messageLabel.setLayoutY(295);
    }else if (game.getGameStatus() == 2) {
        messageLabel.setText("Perdiste el juego :(");
    }
}


    public void hintAction() {

        System.out.println(game.getHintNumber());
        int rand1 = 0;
        int rand2 = 0;
        if (game.getHintNumber()<3) {
            game.setPoints(game.getPoints() + 1);
            System.out.println("Puntos: "+game.getPoints());
            do {
                rand1 = rand.nextInt(6);
                rand2 = rand.nextInt(6);
            } while (!txt[rand1][rand2].getText().isEmpty());
            txt[rand1][rand2].setText(game.getNumber(rand1, rand2));
            txt[rand1][rand2].setEditable(false);
            txt[rand1][rand2].setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #00ff00;-fx-font-size: 23; -fx-alignment: center; -fx-font: Old English Text MT");
            game.setHintNumber(game.getHintNumber()+1);
            if (game.getHintNumber() == 3) {
                messageLabel.setText("Esa fué tu última pista :(");
            } else if (game.getHintNumber() == 2) {
                messageLabel.setText("Te queda solo una pista...");
            } else if (game.getHintNumber() == 1) {
                messageLabel.setText("Todavía tienes dos pistas");
            }

        }
        if (game.getHintNumber()>=3) {
            messageLabel.setText("Ups... parece que no tienes más pistas");
        }
    }







}






