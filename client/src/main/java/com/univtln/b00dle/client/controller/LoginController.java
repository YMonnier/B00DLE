package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class LoginController {

    protected static String mail;

    @FXML
    TextField mailField;

    @FXML
    ListView listViewPoll;

    private Model model;

    public LoginController(){
        this.model = new Model();
    }

    @FXML
    public void nextPaneCreateAccount() {
        ViewNavigator.loadFXMLFile(ViewNavigator.CREATE_ACCOUNT);
    }

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneDashbord() {
        this.mail = mailField.getText();
        ViewNavigator.loadFXMLFile(ViewNavigator.DASHBORD);
    }

    public static String getMail(){
        return mail;
    }

}
