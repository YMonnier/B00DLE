package com.univtln.b00dle.client.controller;

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
    private Poll poll;

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

    public MailsDialogController(ListView listView, Poll poll){
        this.listView = listView;
        this.poll = poll;
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
        //poll.setMails();
        //1. Send mails
        //2. Add poll in list
        Dialog.getStage().close();
        //4. Display in modify template poll
        listView.getItems().add(poll);
    }

    @FXML
    public void initialize(){
        add.setOnAction(e -> {
            addMails();
        });

        send.setOnAction(e -> {
            try {
                sendMails();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

}
