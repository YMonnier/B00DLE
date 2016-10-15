package com.univtln.b00dle.client.view;

import com.univtln.b00dle.client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("B00DLE");
        stage.setScene(createScene(loadMainPane()));
        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the vista switching ViewNavigator.
     * Load fxml file into main.fxml.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(ViewNavigator.MAIN));

        MainController mainController = loader.getController();

        ViewNavigator.setMainController(mainController);
        ViewNavigator.loadVista(ViewNavigator.HOME);

        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);

        //scene.getStylesheets().setAll(getClass().getResource("main/resources/styles/homeStyle.css").toExternalForm());

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
