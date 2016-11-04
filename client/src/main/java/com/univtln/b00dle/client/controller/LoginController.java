package com.univtln.b00dle.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.univtln.b00dle.client.model.OpinionPoll;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sbellange845 on 15/10/16.
 * Controller of login.fxml
 */
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    /**
     * Store the user email which will
     * allow to add it to the new opinion poll as invitation(send email).
     */
    protected static String mail;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;

    /**
     * Default constructor.
     */
    public LoginController() {
    }

    /**
     * Event load createAccount.fxml in stage
     */
    @FXML
    public void nextPaneCreateAccount() {
        LOGGER.info("viewCreateAccountAction:: run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.CREATE_ACCOUNT));

        try {
            MainApp.mainStage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Event which allows to go to the Home view.
     * Event load home.fxml in stage
     */
    @FXML
    public void nextPaneHome() {
        LOGGER.info("viewHomeAction:: run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        try {
            MainApp.mainStage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Event which checks if all data are valid and sent a http request to login.
     * This request return a token that will be used for the Authorization header.
     * Then, we fetch all user opinion polls for view on dashboard.
     */
    @FXML
    public void loginAction() {
        if (!this.mailField.getText().equals("") &&
                !this.passwordField.getText().equals("")) {
            try {
                mail = mailField.getText();
                String parameters = userParameters();
                HttpResponse response = API.post(API.LOGIN, parameters);
                int status = response.getStatusLine().getStatusCode();
                LOGGER.debug("HTTP Status code: " + status);
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                if (status == HttpStatus.SC_CREATED) {
                    API.authorization = jsonObject.get("jwt").getAsString();
                    LOGGER.info("Login successful. Go to the Dashboard view.");
                    LOGGER.debug("token: " + API.authorization);
                    LOGGER.debug("loginAction::run view dashbord");
                    //FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.DASHBORD));

                    List<OpinionPoll> opinionPolls = this.getOpinionPolls();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(ViewNavigator.DASHBORD));
                    DashboardController dashbordController = new DashboardController(opinionPolls);
                    loader.setController(dashbordController);

                    try {
                        MainApp.mainStage.setScene(new Scene(loader.load()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    MainApp.mainStage.show();
                } else {
                    LOGGER.warn("The HTTP status code is invalid: " + status);
                    Dialog.showAlert("Authentication",
                            "Oops! Please try again later.",
                            Alert.AlertType.WARNING);
                }
            } catch (IOException e) {
                LOGGER.debug("Error HTTP Request: " + e.getLocalizedMessage());
                Dialog.showAlert("Authentication",
                        "Oops! Please try again later.",
                        Alert.AlertType.WARNING);
            }
        } else {
            Dialog.showAlert("Authentication",
                    "Please check your input data.",
                    Alert.AlertType.WARNING);
        }
    }


    /**
     * Get all user opinion polls
     *
     * @return list of opinion polls.
     */
    private List<OpinionPoll> getOpinionPolls() {
        LOGGER.debug("Get all user opinion polls");
        List<OpinionPoll> opinionPolls = new ArrayList<>();
        try {
            HttpResponse response = API.get(API.Resources.OPINION_POLLS);
            int status = response.getStatusLine().getStatusCode();
            LOGGER.debug("HTTP Status code: " + status);
            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            if (status == HttpStatus.SC_OK) {
                LOGGER.debug("JSON: " + jsonObject);
                JsonObject data = jsonObject.get("data").getAsJsonObject();
                data.get("opinion_polls")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            OpinionPoll e = gson.fromJson(jsonElement, OpinionPoll.class);
                            LOGGER.debug("A new OpinionPoll: " + e);
                            opinionPolls.add(e);
                        });

                return opinionPolls;
            } else {
                LOGGER.warn("The HTTP status code is invalid: " + status);
                Dialog.showAlert("Authentication",
                        "Cannot fetch all your data.",
                        Alert.AlertType.WARNING);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse login parameters to JSON
     *
     * @return String json.
     */
    private String userParameters() {
        Gson gson = new Gson();
        Map<String, Map<String, Object>> root = new HashMap<>();
        root.put("auth", new HashMap<>());
        root.get("auth").put("email", mailField.getText());
        root.get("auth").put("password", passwordField.getText());
        return gson.toJson(root);
    }

    /**
     * Return current mail
     *
     * @return static Test mail
     */
    public static String getMail() {
        return mail;
    }

}
