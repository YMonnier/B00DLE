package com.univtln.b00dle.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univtln.b00dle.client.model.Answer;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.model.TimeSlot;
import com.univtln.b00dle.client.model.boodle.exception.PollNotFoundException;
import com.univtln.b00dle.client.utilities.network.api.API;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.MainApp;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stéphen on 11/10/2016.
 * Controller of home.fxml
 */
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

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
/*
    @FXML
    private Button loginButton;

    @FXML
    private Button viewOpinionButton;
*/

    /**
     * Event load login.fxml in stage
     */
    public void viewLoginAction() {
        LOGGER.debug("viewLoginAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.LOGIN));

        try {
            MainApp.mainStage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Instanciate link get by linkField
     * Event load viewPoll.fxml in stage
     */
    public void viewOpinionPollAction() {
        String link = this.linkField.getText();
        LOGGER.debug("viewOpinionPollAction: " + link);
        if (!link.isEmpty()) {
            try {
                HttpResponse response = API.get(API.Resources.OPINION_POLLS + "/" + link);
                int status = response.getStatusLine().getStatusCode();
                LOGGER.debug("HTTP Status code: " + status);
                Gson gson = new Gson();
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                if (status == HttpStatus.SC_OK) {
                    LOGGER.debug("JSON: " + jsonObject);
                    JsonObject data = jsonObject.get("data").getAsJsonObject();
                    OpinionPoll opinionPoll = gson.fromJson(data, OpinionPoll.class);
                    LOGGER.debug("JSON: " + opinionPoll);


                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                    ViewNavigator.VIEW_POLL
                            )
                    );
                    PollController m = new PollController(opinionPoll);
                    loader.setController(m);
                    try {
                        MainApp.mainStage.setScene(
                                new Scene(
                                        loader.load()
                                )
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    MainApp.mainStage.show();

                } else {
                    LOGGER.warn("The HTTP status code is invalid: " + status);
                    Dialog.showAlert("B00DLE App",
                            "Oops! Please try again later.",
                            Alert.AlertType.WARNING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Dialog.showAlert("B00DLE App",
                    "Oops! Please put your link.",
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void initialize() {
        LOGGER.info("Initialize HomeController");
        //this.loginButton.setOnAction(e -> this.viewLoginAction());
        //this.viewOpinionButton.setOnAction(e -> this.viewOpinionPollAction());
    }


    /**
     * Return current link
     *
     * @return Static Test
     * @throws PollNotFoundException
     */
    protected static String getLink() {
        return link;
    }

}
