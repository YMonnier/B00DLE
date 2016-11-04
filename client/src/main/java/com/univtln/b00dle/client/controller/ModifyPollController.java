package com.univtln.b00dle.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.model.TimeSlot;
import com.univtln.b00dle.client.utilities.network.api.API;
import com.univtln.b00dle.client.view.*;
import com.univtln.b00dle.client.view.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of ModifyPollDashbord.fxml
 */
public class ModifyPollController {

    private static final Logger LOGGER = Logger.getLogger(AddPollController.class);

    private OpinionPoll opinionPoll;

    private ObservableList<TimeSlot> observableList;

    /**
     * Variable FXML
     * All poll informations who are display
     * are instanciate when fxml file is load
     */
    @FXML
    private Label namePollLabel;

    @FXML
    private TextField pollDescriptionField;

    @FXML
    private TextField pollPlaceField;

    @FXML
    private TextField namePollField;

    @FXML
    private ListView<TimeSlot> listView;

    @FXML
    private Button modifyButton;

    /**
     * Constructor
     * @param opinionPoll reference of the model
     */
    public ModifyPollController(OpinionPoll opinionPoll){
        assert opinionPoll != null;
        this.opinionPoll = opinionPoll;
        this.observableList = FXCollections.observableArrayList(this.opinionPoll.getTimeSlots());
    }

    private void sendModificationAction() {
        String description = pollDescriptionField.getText();
        String title = namePollField.getText();
        String place = pollPlaceField.getText();

        if(!description.isEmpty()
                && !title.isEmpty()
                && !place.isEmpty()) {

            this.opinionPoll.setTitle(title);
            this.opinionPoll.setDescription(description);
            this.opinionPoll.setPlace(place);
            int id = this.opinionPoll.getId();

            try {
                Gson gson = new Gson();
                String parameters = gson.toJson(this.opinionPoll);
                HttpResponse response = API.update(API.Resources.OPINION_POLLS + "/" + id, parameters);
                int status = response.getStatusLine().getStatusCode();
                LOGGER.debug("HTTP Status code: " + status);
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                if (status == HttpStatus.SC_OK) {
                    LOGGER.debug("JSON: " + jsonObject);
                    Dialog.showAlert("Modify Opinion Poll",
                            "The modification is succeed.",
                            Alert.AlertType.INFORMATION);
                } else {
                    LOGGER.warn("The HTTP status code is invalid: " + status);
                    Dialog.showAlert("Modify Opinion Poll",
                            "Oops! Please try again later.",
                            Alert.AlertType.WARNING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

        }
    }

    /**
     * Fonction initialize when fxml file is load
     */
    @FXML
    public void initialize() {
        LOGGER.info("Initialize ModifyPollController");
        namePollLabel.setText(this.opinionPoll.getTitle());
        namePollField.setText(this.opinionPoll.getTitle());
        pollDescriptionField.setText(this.opinionPoll.getDescription());
        pollPlaceField.setText(this.opinionPoll.getPlace());

        this.listView.setCellFactory(param -> new CustomCell());
        this.listView.getItems().addAll(this.observableList);

        this.modifyButton.setOnAction(e -> this.sendModificationAction());
    }

    private static class CustomCell extends ListCell<TimeSlot> {
        @Override
        protected void updateItem(TimeSlot item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getFrom() + " - " + item.getTo());
            }
        }
    }
}
