package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.view.MainApp;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Stéphen on 15/10/2016.
 * Controller of dashbord.fxml
 */

public class DashbordController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     * Pane is a area who can put other fxml file
     * Here AddPollDashbord.fxml and ModifyPollDashbord.fxml
     */
    @FXML
    private Pane pane;

    /**
     * ListView display poll who are create by administrator
     */
    @FXML
    private ListView<OpinionPoll> listViewPoll;

    /**
     * Default Constructor
     */
    public DashbordController(){}

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        LOGGER.debug("viewHomeAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        try {
            MainApp.mainStage.setScene(new Scene((Pane)loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Event load go back to the home view
     * Disconnect administrator and load home.fxml
     */
    @FXML
    public void logoutAction(){
        LOGGER.debug("viewLogoutAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        try {
            MainApp.mainStage.setScene(new Scene((Pane)loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Load inside the pane AddPollDashbord.fxml
     * @throws IOException
     */
    @FXML
    public void viewPollFormAction() throws IOException {
        LOGGER.info("viewPollFormAction:: add view in pane");
        AddPollController addPollController =
                new AddPollController(listViewPoll);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        ViewNavigator.ADD_POLL
                )
        );
        loader.setController(addPollController);
        pane.getChildren().clear();
        pane.getChildren().add(loader.load());
    }

    /**
     * Load inside the pane ModifyPollDashbord.fxml
     * Show allo information that you can edit and sent to the server.
     * @throws IOException
     */
    @FXML
    public void viewModifyPollAction(MouseEvent arg) throws IOException {
        LOGGER.info("viewModifyPollAction:: add view in pane");
        OpinionPoll opinionPoll = listViewPoll.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.MODIFY_POLL));
        ModifyPollController m = new ModifyPollController(opinionPoll);
        loader.setController(m);
        pane.getChildren().clear();
        pane.getChildren().add(loader.load());
    }

    /**
     * Fonction initialize when fxml file is load
     * Get and add poll in ListView behove administrator
     */
    @FXML
    public void initialize(){
        LOGGER.info("Initialize DashbordController");
        //ObservableList<Poll> listPoll = FXCollections.observableArrayList(model.getPoll(LoginController.getMail()));
        //listViewPoll.setItems(listPoll);
    }
}
