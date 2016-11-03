package com.univtln.b00dle.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univtln.b00dle.client.model.Invitation;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.utilities.network.api.API;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.MainApp;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
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
        String email = mailTextField.getText();
        LOGGER.info("addMailsAction");
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
        LOGGER.info("sendOpinionPollAction");
        Gson gson = new Gson();
        String parameters = gson.toJson(this.opinionPoll);

        HttpResponse response = API.post(API.Resources.OPINION_POLLS, parameters);
        int status = response.getStatusLine().getStatusCode();
        LOGGER.debug("HTTP Status code: " + status);
        String json = EntityUtils.toString(response.getEntity(), "UTF-8");

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        if (status == HttpStatus.SC_CREATED) {
            JsonObject jsonDataObject = jsonObject.get("data").getAsJsonObject();
            LOGGER.info("Created: " + jsonDataObject);
            Dialog.showAlert("Opinion Poll",
                    "Opinion poll created!",
                    Alert.AlertType.INFORMATION);

            listView.getItems().add(this.opinionPoll);
            Dialog.getStage().close();
        } else {
            LOGGER.warn("The HTTP status code is invalid: " + status);
            Dialog.showAlert("Opinion Poll",
                    "Oops! Please try again later.",
                    Alert.AlertType.WARNING);
        }
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
