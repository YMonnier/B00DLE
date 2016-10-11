package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;

/**
 * Created by Stéphen on 11/10/2016.
 */
public class HomeController {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML
    public void nextPane() {
        ViewNavigator.loadVista(ViewNavigator.CREATE_ACCOUNT);
    }

}
