package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.OpinionPoll;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of ModifyPollDashbord.fxml
 */
public class ModifyPollController {

    private static final Logger LOGGER = Logger.getLogger(AddPollController.class);

    /**
     * Variable send by DashbordController when user click on poll in ListView
     */
    private StringProperty namePoll = new SimpleStringProperty();
    private StringProperty descriptionPoll = new SimpleStringProperty();
    private StringProperty placePoll = new SimpleStringProperty();

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

    /**
     * Constructor
     * @param opinionPoll reference of the model
     */
    public ModifyPollController(OpinionPoll opinionPoll){
        namePoll.set(opinionPoll.getTitle());
        descriptionPoll.set(opinionPoll.getDescription());
        placePoll.set(opinionPoll.getPlace());
    }

    /**
     * Fonction initialize when fxml file is load
     */
    @FXML
    public void initialize() {
        LOGGER.info("Initialize ModifyPollController");
        namePollLabel.setText(namePoll.get());
        namePollField.setText(namePoll.get());
        pollDescriptionField.setText(descriptionPoll.get());
        pollPlaceField.setText(placePoll.get());
    }
}
