package com.univtln.b00dle.client.view;

import com.univtln.b00dle.client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception{
        mainStage = stage;
        stage.setTitle("B00DLE");

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        ViewNavigator.HOME
                )
        );

        stage.setScene(
                new Scene(
                        loader.load()
                )
        );
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
