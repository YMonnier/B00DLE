package com.univtln.b00dle.client.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.univtln.b00dle.client.utilities.network.api.API;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by sbellange845 on 15/10/16.
 * Controller of CreateAccount.fxml
 */
public class CreateAccountController {

    private static final Logger LOGGER = Logger.getLogger(CreateAccountController.class);

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TextField nameField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordVerificationField;

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Event which check if all data are valid and
     * send a POST request to create a new User.
     * Load dashbord.fxml
     */
    @FXML
    public void nextPaneDashbord() {
        if (!nameField.getText().equals("") &&
                !mailField.getText().equals("") &&
                !passwordField.getText().equals("") &&
                !passwordVerificationField.getText().equals("")) {
            try {
                String parameters = userParameters();
                HttpResponse response = API.post(API.Resources.USERS, parameters);
                int status = response.getStatusLine().getStatusCode();

                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                if (status == HttpStatus.SC_CREATED) {
                    JsonObject data = jsonObject.get("data").getAsJsonObject();

                    assert data.get("name").getAsString().equals(nameField.getText());
                    assert data.get("email").getAsString().equals(mailField.getText());

                    LOGGER.info("Creating user done. Go to the Dashboard view.");
                    Dialog.showAlert("Inscription",
                            "Votre inscription a bien été prise en compte. Vous pouvez maintenant vous connecter.",
                            Alert.AlertType.INFORMATION);

                    ViewNavigator.loadFXMLFile(ViewNavigator.LOGIN);
                } else {
                    String errors = jsonObject.get("errors").toString();
                    LOGGER.warn("The HTTP status code is invalid: " + status);
                    LOGGER.warn("Errors: " + errors);

                    Dialog.showAlert("Inscription",
                            "Un problème est survenu ! Veuillez ressayer plus tard.\n" + errors,
                            Alert.AlertType.WARNING);
                }
            } catch (IOException e) {
                LOGGER.debug("Error HTTP Request: " + e.getLocalizedMessage());
                Dialog.showAlert("Inscription",
                        "Un problème est survenu ! Veuillez ressayer plus tard.",
                        Alert.AlertType.WARNING);
            }
        } else {
            Dialog.showAlert("Inscription",
                    "Veuillez vérifier vos données saisies.",
                    Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Parse parameters to JSON
     * @return String json.
     */
    private String userParameters() {
        Gson gson = new Gson();
        Map<String, Object> params = new HashMap<>();
        params.put("name", nameField.getText());
        params.put("email", mailField.getText());
        params.put("password", passwordField.getText());
        params.put("password_confirmation", passwordVerificationField.getText());
        return gson.toJson(params);
    }
}
