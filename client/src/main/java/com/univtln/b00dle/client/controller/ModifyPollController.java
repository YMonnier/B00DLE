package com.univtln.b00dle.client.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Stéphen on 20/10/2016.
 */
public class ModifyPollController {

    private StringProperty namePoll = new SimpleStringProperty();
    private StringProperty descriptionPoll = new SimpleStringProperty();
    private StringProperty placePoll = new SimpleStringProperty();

    public ModifyPollController(String name, String descritpion, String place){
        namePoll.set(name);
        descriptionPoll.set(descritpion);
        placePoll.set(place);
    }

    @FXML
    Label namePollLabel;

    @FXML
    TextField pollDescriptionField;

    @FXML
    TextField pollPlaceField;


    @FXML
    public void initialize() {
        namePollLabel.setText(namePoll.get());
        pollDescriptionField.setText(descriptionPoll.get());
        pollPlaceField.setText(placePoll.get());
    }
}
