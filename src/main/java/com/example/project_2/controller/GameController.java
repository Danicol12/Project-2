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



    public void initialize() {
        game=new Game();
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
              int lenght = txt.getText().length();
              if(lenght <= 1) {
                  if (game.getRemainingLives() != 0 && !keyEvent.getText().isEmpty() && txt.isEditable() && game.getGameStatus() ==0) {
                      System.out.println("peneeeeeeeeeeeeeeeeeeee");
                      System.out.println(game.getGameStatus());
                      if (game.numberComprobation(keyEvent.getText())) {
                          System.out.println(txt.getText());
                          System.out.println("La fila es" + row + "La columna es" + col);
                          if (game.isNumberCorrect(keyEvent.getText(), row, col)) {
                              System.out.println("Son iguales");
                              txt.setStyle("-fx-background-color: green;");
                              txt.setEditable(false);


                              game.setPoints(game.getPoints() + 1);
                              System.out.println("Llevas " + game.getPoints());
                          } else if (!game.isNumberCorrect(keyEvent.getText(), row, col)) {
                              game.setRemainingLives(game.getRemainingLives() - 1);
                              System.out.println("No son  iguales");
                              heartsChange();
                              messageLabel.setText("Ups... Parece que te equivocaste");
                          }
                      }


                  } else if (game.getGameStatus() == 1) {
                      messageLabel.setText("Ganaste el juego ");
                      livesImageView.setFitHeight(157);
                      livesImageView.setFitWidth(200);
                      remainingLivesTxt.setText("");
                      livesImageView.setImage(new Image(getClass().getResource("/com/example/project_2/images/trophy.png").toExternalForm()));
                  } else if (game.getGameStatus() == 2) {
                      messageLabel.setText("Perdiste el juego :(");
                  }
              } else if (lenght != 1) {
                  messageLabel.setText("Ingresa solo un caracter");
                  txt.setText("");
              }
                game.checkWinCondition();
            }
        });
}

    public void returnAction() throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    public void hintAction()  {
        game.setHintNumber(game.getHintNumber()+1);
        if (game.getHintNumber() > 3) {
            messageLabel.setText("Ups... parece que no tienes más pistas");
        } else if (game.getHintNumber() == 3) {
            messageLabel.setText("Esa fué tu última pista :(");
        } else if (game.getHintNumber() == 2) {
            messageLabel.setText("Te queda solo una pista...");
        } else if (game.getHintNumber() == 1) {
            messageLabel.setText("Todavía tienes dos pistas");
        }


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

}