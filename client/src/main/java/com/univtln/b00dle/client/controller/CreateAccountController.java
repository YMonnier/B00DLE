package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class CreateAccountController {

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneDashbord(){ ViewNavigator.loadVista(ViewNavigator.DASHBORD);}
}
