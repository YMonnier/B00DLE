package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Stéphen on 15/10/2016.
 * Controller of dashbord.fxml
 */

public class DashbordController {

    /**
     * Controller can use Model -> MVC
     */
    private Model model;

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
    private ListView<Poll> listViewPoll;

    /**
     * Constructor
     * Create Model
     */
    public DashbordController(){
        this.model = new Model();
    }

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Event load go back to the home view
     * Disconnect administrator and load home.fxml
     */
    @FXML
    public void logoutAction(){
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Load inside the pane AddPollDashbord.fxml
     * @throws IOException
     */
    @FXML
    public void viewPollFormAction() throws IOException {
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
        String link = listViewPoll.getSelectionModel().getSelectedItem().getLink();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.MODIFY_POLL));
        ModifyPollController m = new ModifyPollController(model.getPollByLink(link).getName(),
                model.getPollByLink(link).getDescription(),
                model.getPollByLink(link).getPlace());
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
        ObservableList<Poll> listPoll = FXCollections.observableArrayList(model.getPoll(LoginController.getMail()));
        listViewPoll.setItems(listPoll);
    }
}
