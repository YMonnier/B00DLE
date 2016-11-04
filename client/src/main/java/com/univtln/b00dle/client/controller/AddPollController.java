package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.model.TimeSlot;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of AddPollDashbord.fxml
 */
public class AddPollController {

    private static final Logger LOGGER = Logger.getLogger(AddPollController.class);

    private ListView listView;

    private OpinionPoll opinionPoll;
    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private Stage mailsDialog;

    @FXML
    private TextField namePollField;

    @FXML
    private TextField descriptionPollField;

    @FXML
    private TextField placePollField;

    @FXML
    private TextField departureDateField;

    @FXML
    private TextField departureTimeField;

    @FXML
    private TextField endDateField;

    @FXML
    private TextField endTimeField;

    @FXML
    private TableView tableViewPoll;

    @FXML
    private Button addButton;

    @FXML
    private Button validateButton;

    public AddPollController() {
    }

    /**
     * Constructor
     *
     * @param listView reference of the ListView instance in DashbordController.
     */
    public AddPollController(ListView listView) {
        this.listView = listView;
        this.opinionPoll = new OpinionPoll.Builder().build();
    }

    /**
     * Event who add dynamically column in poll
     */
    @FXML
    public void addTimeSlotAction() {
        LOGGER.info("Add time slot...");
        String dateFrom = departureDateField.getText();
        String timeFrom = departureTimeField.getText();
        String dateTo = endDateField.getText();
        String timeTo = endTimeField.getText();


        // Add time slot to the current model
        TimeSlot timeSlot = new TimeSlot.Builder()
                .setFrom(dateFrom + " " + timeFrom)
                .setTo(dateTo + " " + timeTo)
                .build();
        this.opinionPoll.getTimeSlots().add(timeSlot);
        this.departureTimeField.setText("");
        this.endTimeField.setText("");

        LOGGER.debug("current opinion poll: " + opinionPoll.toString());
        // Add colomn to the tableView
        String newNameColumn = dateFrom + " - " + timeFrom + "\n" + dateTo + " - " + timeTo;
        TableColumn<Map, String> column = new TableColumn<>(newNameColumn);
        column.setCellValueFactory(new MapValueFactory(newNameColumn));
        column.setMinWidth(newNameColumn.length());
        tableViewPoll.getColumns().add(column);
    }

    /**
     * Run popup to add mails
     */
    @FXML
    public void viewEmailFormAction() {
        LOGGER.info("View email form...");
        //Get Poll informations
        String title = namePollField.getText();
        String description = descriptionPollField.getText();
        String place = placePollField.getText();

        if (!title.isEmpty()
                && !description.isEmpty()
                && !place.isEmpty()
                && this.opinionPoll.getTimeSlots().size() > 0) {
            this.opinionPoll.setDescription(description);
            this.opinionPoll.setPlace(place);
            this.opinionPoll.setTitle(title);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewNavigator.MAILS_DIALOG));
            MailsDialogController mailsDialogController = new MailsDialogController(this.listView, this.opinionPoll);
            loader.setController(mailsDialogController);
            Parent content = null;
            try {
                content = loader.load();
                Dialog.build("Invitations");
                Dialog.getStage().setScene(new Scene(content));
                Dialog.getStage().show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Dialog.showAlert("Opinion Poll",
                    "Oops! Please check your input data.",
                    Alert.AlertType.WARNING);
        }
    }

    /**
     * Fonction initialize when fxml file is load
     */
    @FXML
    public void initialize() {
        LOGGER.info("Initialize AddPollController");
        tableViewPoll.setEditable(true);
        this.addButton.setOnAction(e -> this.addTimeSlotAction());
        this.validateButton.setOnAction(e -> this.viewEmailFormAction());
    }
}
