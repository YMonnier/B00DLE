package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Invitation;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.view.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of dialogAddMails.fxml
 */
public class MailsDialogController {

    private ListView listView;

    private OpinionPoll opinionPoll;

    @FXML
    private Button add;

    @FXML
    private Button send;

    @FXML
    private ListView listViewMails;

    @FXML
    private TextField mailTextField;

    public MailsDialogController() {
    }

    public MailsDialogController(ListView listView, OpinionPoll opinionPoll) {
        this.listView = listView;
        this.opinionPoll = opinionPoll;
    }

    /**
     * Event add mails in ListView
     */
    @FXML
    public void addMailAction() {
        java.lang.String email = mailTextField.getText();
        if (!email.isEmpty()) {
            this.opinionPoll.getInvitations().add(email);
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
        //1. Send mails
        //2. Add poll in list
        Dialog.getStage().close();
        //4. Display in modify template poll
        listView.getItems().add(this.opinionPoll);
    }

    @FXML
    public void initialize() {
        add.setOnAction(e -> {
            addMailAction();
        });

        send.setOnAction(e -> {
            try {
                sendOpinionPollAction();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

}
