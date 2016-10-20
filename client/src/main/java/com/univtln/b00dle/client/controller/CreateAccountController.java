package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;

/**
 * Created by sbellange845 on 15/10/16.
 * Controller of CreateAccount.fxml
 */
public class CreateAccountController {

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Event load dashbord.fxml
     */
    @FXML
    public void nextPaneDashbord(){ ViewNavigator.loadFXMLFile(ViewNavigator.DASHBORD);}
}
