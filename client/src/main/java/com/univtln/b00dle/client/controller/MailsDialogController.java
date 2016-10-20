package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.view.Dialog;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of dialogAddMails.fxml
 */
public class MailsDialogController {

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
