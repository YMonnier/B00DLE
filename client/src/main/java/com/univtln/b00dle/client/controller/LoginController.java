package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class LoginController {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML
    public void nextPaneCreateAccount() {
        ViewNavigator.loadVista(ViewNavigator.CREATE_ACCOUNT);
    }

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneDashbord() {
        ViewNavigator.loadVista(ViewNavigator.DASHBORD);
    }

}
