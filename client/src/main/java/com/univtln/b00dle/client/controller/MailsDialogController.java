package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Invitation;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.view.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of dialogAddMails.fxml
 */
public class MailsDialogController {

    private static final Logger LOGGER = Logger.getLogger(AddPollController.class);

    /**
     * Reference ListView instanciate into DashBordController
     */
    private ListView listView;

    /**
     * Reference to Model of application
     */
    private OpinionPoll opinionPoll;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private Button add;

    @FXML
    private Button sendButton;

    @FXML
    private ListView listViewMails;

    @FXML
    private TextField mailTextField;

    /**
     * Constructor
     * @param listView reference of the ListView instance in DashbordController.
     * @param opinionPoll Reference of the model
     */
    public MailsDialogController(ListView listView, OpinionPoll opinionPoll) {
        this.listView = listView;
        this.opinionPoll = opinionPoll;
    }

    /**
     * Event add mails in ListView
     */
    @FXML
    public void addMailAction() {
        LOGGER.info("addMailsAction");
        String email = mailTextField.getText();
        if (!email.isEmpty()) {
            this.opinionPoll.getInvitations().add(new Invitation(email));
            this.listViewMails.getItems().add(email);
        } else {
            Dialog.showAlert("Opinion Poll",
                    "Oops! Please check your input data.",
                    Alert.AlertType.WARNING);
        }
    }

    /**
     * Event load dialog dialogAddMails.fxml
     *
     * @throws IOException
     */
    @FXML
    public void sendOpinionPollAction() throws IOException {
        LOGGER.info("sendOpinionPollAction");
        //1. Send mails
        //2. Add poll in list
        Dialog.getStage().close();
        //4. Display in modify template poll
        listView.getItems().add(this.opinionPoll);
    }

    /**
     * Fonction initialize when fxml file is load
     */
    @FXML
    public void initialize() {
        LOGGER.info("Initialize MailsDialogController");
        add.setOnAction(e -> {
            addMailAction();
        });

        sendButton.setOnAction(e -> {
            try {
                sendOpinionPollAction();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

}
