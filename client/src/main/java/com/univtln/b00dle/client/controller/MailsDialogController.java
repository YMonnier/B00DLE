package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.view.Dialog;
import javafx.fxml.FXML;
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

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */

    @FXML
    private Button add;

    @FXML
    private Button send;

    @FXML
    private ListView listViewMails;

    @FXML
    private TextField mailTextField;

    public MailsDialogController(){}

    public MailsDialogController(ListView listView, OpinionPoll opinionPoll){
        this.listView = listView;
        this.opinionPoll = opinionPoll;
    }

    /**
     * Event add mails in ListView
     */
    @FXML
    public void addMailAction(){
        //Get mail of administrator
        // .....
        listViewMails.getItems().add(mailTextField.getText());
    }

    /**
     * Event load dialog dialogAddMails.fxml
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
    public void initialize(){
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
