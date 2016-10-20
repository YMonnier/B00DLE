package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.io.IOException;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of dialogAddMails.fxml
 */
public class MailsDialogController {

    /**
     * Link into dialog and dashbord
     */
    private DashbordController dashbordController;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */

    @FXML
    private ListView listViewMails;

    @FXML
    private TextField mailTextField;

    public MailsDialogController(){
        this.dashbordController = new DashbordController();
    }

    /**
     * Event add mails in ListView
     */
    @FXML
    public void addMails(){
        //Get mail of administrator
        // .....
        listViewMails.getItems().add(mailTextField.getText());
    }

    /**
     * Event load dialog dialogAddMails.fxml
     * @throws IOException
     */
    @FXML
    public void sendMails() throws IOException {
        //1. Send mails
        //2. Add poll in list
        Dialog.getStage().close();
        //4. Display in modify template poll
    }

}
