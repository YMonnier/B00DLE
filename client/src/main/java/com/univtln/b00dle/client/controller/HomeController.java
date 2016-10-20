package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.boodle.exception.PollNotFoundException;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Stéphen on 11/10/2016.
 * Controller of home.fxml
 */
public class HomeController {

    /**
     * Variable static contain link of poll enter by user
     * Permit to get and load all informations about the poll
     */
    private static String link;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TextField linkField;

    /**
     * Event load login.fxml
     */
    @FXML
    public void nextPaneLogin() {
        ViewNavigator.loadFXMLFile(ViewNavigator.LOGIN);
    }

    /**
     * Instanciate link get by linkField
     * Event load viewPoll.fxml
     */
    @FXML
    public void nextPaneViewPoll(){
        this.link = linkField.getText();
        ViewNavigator.loadFXMLFile(ViewNavigator.VIEW_POLL);
    }

    /**
     * Return current link
     * @return Static String
     * @throws PollNotFoundException
     */
    protected static String getLink() {
        return link;
    }

}
