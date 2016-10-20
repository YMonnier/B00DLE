package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Stéphen on 20/10/2016.
 */
public class AddPollController {

    @FXML
    private Stage mailsDialog;

    /** Run popup to add mails */
    @FXML
    public void addMails() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ViewNavigator.MAILS_DIALOG));
        Parent content = (Parent) loader.load();
        Dialog.build("Ajouter collaborateurs");
        Dialog.getStage().setScene(new Scene(content));
        Dialog.getStage().show();
    }

}
