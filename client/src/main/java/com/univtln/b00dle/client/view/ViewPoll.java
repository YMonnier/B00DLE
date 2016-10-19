package com.univtln.b00dle.client.view;

import com.univtln.b00dle.client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by sbellange845 on 18/10/16.
 */
public class ViewPoll extends Application {
    @FXML
    TableView tableView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("B00DLE");
        primaryStage.setScene(createScene(loadMainPane()));
        primaryStage.show();

        tableView.setEditable(true);
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(ViewNavigator.MAIN));

        MainController mainController = loader.getController();

        ViewNavigator.setMainController(mainController);
        ViewNavigator.loadFXMLFile(ViewNavigator.VIEW_POLL);

        return mainPane;
    }


    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);

        //scene.getStylesheets().setAll(getClass().getResource("main/resources/styles/homeStyle.css").toExternalForm());

        return scene;
    }
}
