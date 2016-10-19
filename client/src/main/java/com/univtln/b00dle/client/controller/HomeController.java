package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.boodle.exception.PollNotFoundException;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Stéphen on 11/10/2016.
 */
public class HomeController {

    @FXML
    TextField linkField;

    private static String link;

    @FXML
    public void nextPaneLogin() {
        ViewNavigator.loadFXMLFile(ViewNavigator.LOGIN);
    }

    @FXML
    public void nextPaneViewPoll(){
        this.link = linkField.getText();
        ViewNavigator.loadFXMLFile(ViewNavigator.VIEW_POLL);
    }

    protected static String getLink() throws PollNotFoundException {
        return link;
    }

}
