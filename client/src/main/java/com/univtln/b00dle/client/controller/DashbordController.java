package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
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
 */

public class DashbordController {

    @FXML
    private Pane pane;

    @FXML
    private ListView listViewPoll;

    private Model model;

    public DashbordController(){
        this.model = new Model();
    }

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneLogout(){
        //Add logout in server
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    @FXML
    public void addPoll() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane newLoadedPane = loader.load(getClass().getResource(ViewNavigator.ADD_POLL));
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    public void initialize(){
        ObservableList<Object> listPoll = FXCollections.observableArrayList(model.getPoll(LoginController.getMail()));
        listViewPoll.setItems(listPoll);
    }

    @FXML
    public void addPollInformations() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.MODIFY_POLL));
        ModifyPollController m = new ModifyPollController("test", "test", "test");
        loader.setController(m);
        pane.getChildren().clear();
        pane.getChildren().add(loader.load());

    }
}
