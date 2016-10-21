package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by sbellange845 on 15/10/16.
 * Controller of CreateAccount.fxml
 */
public class CreateAccountController {

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TextField nameField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordVerificationField;

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Event log administrator
     * Load dashbord.fxml
     */
    @FXML
    public void nextPaneDashbord(){
        String name = nameField.getText();
        String mail = nameField.getText();
        String password = passwordField.getText();
        String passwordVerification = passwordField.getText();
        //Server create account
        ViewNavigator.loadFXMLFile(ViewNavigator.DASHBORD);}

}
