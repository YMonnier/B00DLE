package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;

/**
 * Created by Stéphen on 15/10/2016.
 */
public class DashbordController {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneLogout(){
        //Add logout
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void addPoll(){
        //Replace borderPane.setCenter() by empty poll template
    }

    @FXML
    public void addMails(){
        //Run popup to add mails
    }

}
