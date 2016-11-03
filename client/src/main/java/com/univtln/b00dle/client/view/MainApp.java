package com.univtln.b00dle.client.view;

import com.univtln.b00dle.client.controller.PollController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 * Created by Stéphen on 15/10/2016.
 * Main class of application
 * Display stage
 */

public class MainApp extends Application {

    private static final Logger LOGGER = Logger.getLogger(PollController.class);

    /**
     * Stage mainStage view content of application
     */
    public static Stage mainStage;

    /**
     * Start and run view home.fxml when main is run
     * @param stage stage of application
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        LOGGER.info("########### START APPLICATION ##############");
        mainStage = stage;
        stage.setTitle("B00DLE");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * The main method is where the view are create
     * @param args the original view
     */
    public static void main(String[] args) {
        LOGGER.info("B00DLE is run...");
        launch(args);
    }
}
