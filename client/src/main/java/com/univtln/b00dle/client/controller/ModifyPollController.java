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
import java.util.List;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of ModifyPollDashbord.fxml
 */
public class ModifyPollController {

    private static final Logger LOGGER = Logger.getLogger(AddPollController.class);

    private OpinionPoll opinionPoll;

    private ListView<OpinionPoll> opinionPollListView;

    private ObservableList<TimeSlot> observableList;

    private int index;

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

    @FXML
    private Button deleteButton;

    @FXML
    private CheckBox closeCheckBox;

    /**
     * Constructor
     * @param opinionPoll reference of the model
     */
    public ModifyPollController(ListView<OpinionPoll> opinionPolls, int index){
        this.index = index;
        assert opinionPolls!= null;
        this.opinionPollListView = opinionPolls;
        this.opinionPoll = opinionPollListView.getItems().get(index);
        assert this.opinionPoll != null;
        this.observableList = FXCollections.observableArrayList(this.opinionPoll.getTimeSlots());
    }

    /**
     * Method which allows to modify the opinion poll selected.
     */
    private void sendModificationAction() {
        String description = this.pollDescriptionField.getText();
        String title = this.namePollField.getText();
        String place = this.pollPlaceField.getText();
        boolean close = this.closeCheckBox.selectedProperty().get();

        if(!description.isEmpty()
                && !title.isEmpty()
                && !place.isEmpty()) {

            this.opinionPoll.setTitle(title);
            this.opinionPoll.setDescription(description);
            this.opinionPoll.setPlace(place);
            this.opinionPoll.setClose(close);
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
                    this.setCloseStatus();
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
            Dialog.showAlert("Modify Opinion Poll",
                    "Oops! Please try again later.",
                    Alert.AlertType.WARNING);
        }
    }

    private void deleteOpinionPollAction() {
        try {
            int id = this.opinionPoll.getId();

            HttpResponse response = API.delete(API.Resources.OPINION_POLLS + "/" + id);
            int status = response.getStatusLine().getStatusCode();
            LOGGER.debug("HTTP Status code: " + status);

            if (status == HttpStatus.SC_NO_CONTENT) {
                this.setCloseStatus();
                Dialog.showAlert("Opinion Poll",
                        "Removing worked!",
                        Alert.AlertType.INFORMATION);
                this.opinionPollListView.getItems().remove(this.index);
                this.reset();
            } else {
                LOGGER.warn("The HTTP status code is invalid: " + status);
                Dialog.showAlert("Opinion Poll",
                        "Oops! Please try again later.",
                        Alert.AlertType.WARNING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction initialize when fxml file is load
     */
    @FXML
    public void initialize() {
        LOGGER.info("Initialize ModifyPollController");

        this.setCloseStatus();

        this.namePollLabel.setText(this.opinionPoll.getTitle());
        this.namePollField.setText(this.opinionPoll.getTitle());
        this.pollDescriptionField.setText(this.opinionPoll.getDescription());
        this.pollPlaceField.setText(this.opinionPoll.getPlace());

        this.listView.setCellFactory(param -> new CustomCell());
        this.listView.getItems().addAll(this.observableList);

        this.modifyButton.setOnAction(e -> this.sendModificationAction());
        this.deleteButton.setOnAction(e -> this.deleteOpinionPollAction());
        this.closeCheckBox.setOnAction(e -> {
            this.opinionPoll.setClose(this.closeCheckBox.selectedProperty().get());
            this.setCloseStatus();
        });
    }

    private void reset() {
        this.namePollLabel.setText("");
        this.namePollField.setText("");
        this.pollDescriptionField.setText("");
        this.pollPlaceField.setText("");
        this.listView.getItems().removeAll(this.observableList);
        this.observableList.removeAll();
    }

    private void setCloseStatus() {
        if (this.opinionPoll.isClose()) {
            this.namePollField.setEditable(false);
            this.pollDescriptionField.setEditable(false);
            this.pollPlaceField.setEditable(false);
            this.modifyButton.setDisable(true);
            this.closeCheckBox.setDisable(true);
        } else {
            this.namePollField.setEditable(true);
            this.pollDescriptionField.setEditable(true);
            this.pollPlaceField.setEditable(true);
            this.modifyButton.setDisable(false);
            this.closeCheckBox.setDisable(false);
        }
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
