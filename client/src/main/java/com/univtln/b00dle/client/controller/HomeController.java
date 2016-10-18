package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import com.univtln.b00dle.client.view.ViewPoll;
import javafx.fxml.FXML;
import javafx.stage.Stage;

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
    public void nextPaneLogin() {
        ViewNavigator.loadVista(ViewNavigator.LOGIN);
    }

    @FXML
    public void nextPaneViewPoll(){
        ViewNavigator.loadVista(ViewNavigator.VIEW_POLL);
    }

}
