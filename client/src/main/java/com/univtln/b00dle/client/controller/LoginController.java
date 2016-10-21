package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Created by sbellange845 on 15/10/16.
 * Controller of login.fxml
 */
public class LoginController {

    /**
     * Variable static contain mail of Administrator recovered when connecting
     * Permit to get and load all poll administer by him
     */
    protected static String mail;

    /**
     * Controller can use Model -> MVC
     */
    private Model model;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;

    /**
     * Constructor
     * Create Model
     */
    public LoginController(){
        this.model = new Model();
    }

    /**
     * Event load createAccount.fxml
     */
    @FXML
    public void nextPaneCreateAccount() {
        ViewNavigator.loadFXMLFile(ViewNavigator.CREATE_ACCOUNT);
    }

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Event lors dashbord.fxml
     */
    @FXML
    public void nextPaneDashbord() {
        this.mail = mailField.getText();
        //send serveur mail and password for verification
        ViewNavigator.loadFXMLFile(ViewNavigator.DASHBORD);
    }


    /**
     * Return current mail
     * @return static String mail
     */
    public static String getMail(){
        return mail;
    }

}
