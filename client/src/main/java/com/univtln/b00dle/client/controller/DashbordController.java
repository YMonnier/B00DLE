package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
     * Event load return on home
     * Disconnect administrator and load home.fxml
     */
    @FXML
    public void nextPaneLogout(){
        //Add logout in server
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Load in pane AddPollDashbord.fxml
     * @throws IOException
     */
    @FXML
    public void addPoll() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane newLoadedPane = loader.load(getClass().getResource(ViewNavigator.ADD_POLL));
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    /**
     * Load in pane ModifyPollDashbord.fxml
     * Show in form all informations about poll
     * @throws IOException
     */
    @FXML
    public void addPollInformations(MouseEvent arg) throws IOException {
        String link = listViewPoll.getSelectionModel().getSelectedItem().getLink();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.MODIFY_POLL));
        ModifyPollController m = new ModifyPollController(model.getPollByLink(link).getName(), model.getPollByLink(link).getDescription(), model.getPollByLink(link).getPlace());
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
