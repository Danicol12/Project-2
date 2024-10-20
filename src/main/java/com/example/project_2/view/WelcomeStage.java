package com.example.project_2.view;

import com.example.project_2.Main;
import com.example.project_2.controller.WelcomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {

    private WelcomeController welcomeController;

    public WelcomeStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        setTitle("Sudoku melardo");
        setScene(scene);
        show();
        getIcons().add(new Image(getClass().getResource("/com/example/project_2/images/sudoku.png").toExternalForm()));
        setWidth(800);
        setHeight(444);
        setResizable(false);
    }

    public WelcomeController getGameController() {
        return welcomeController;
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    public static void deleteInstance(){
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }

    public static WelcomeStage getInstance() throws IOException{
        WelcomeStage.WelcomeStageHolder.INSTANCE =
                WelcomeStage.WelcomeStageHolder.INSTANCE != null ? WelcomeStage.WelcomeStageHolder.INSTANCE : new WelcomeStage();
        return WelcomeStage.WelcomeStageHolder.INSTANCE;
    }


}
