package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Stéphen on 15/10/2016.
 */

public class DashbordController {

    @FXML
    private Stage mailsDialog;

    @FXML Pane pane;

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneLogout(){
        //Add logout in server
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void addPoll() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/fxml/addPollDashbord.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    /** Run popup to add mails */
    @FXML
    public void addMails() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ViewNavigator.MAILS_DIALOG));
        Parent content = (Parent) loader.load();
        Dialog.build("Ajouter collaborateurs");
        Dialog.getStage().setScene(new Scene(content));
        Dialog.getStage().show();
    }

    @FXML
    public void sendMails() throws IOException {
        //1. Send mails
        //2. Add poll in list
        Dialog.getStage().close();
        //4. Display in modify template poll
    }

}
