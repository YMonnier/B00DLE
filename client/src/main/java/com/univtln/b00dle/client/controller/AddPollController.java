package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.view.Dialog;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Stéphen on 20/10/2016.
 * Controller of AddPollDashbord.fxml
 */
public class AddPollController {

    private ListView listView;

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

    public AddPollController(){}

    public AddPollController(ListView listView){
        this.listView = listView;
    }

    /**
     * Event who add dynamically column in poll
     */
    @FXML
    public void addDate(){
        String departureDate = departureDateField.getText();
        String departureTime = departureTimeField.getText();
        String endDate = endDateField.getText();
        String endTime = endTimeField.getText();
        String newNameColumn = departureDate + " - " + departureTime + "/" + endDate + " - " + endTime;
        TableColumn<Map, String> column = new TableColumn<>(newNameColumn);
        column.setCellValueFactory(new MapValueFactory(newNameColumn));
        column.setMinWidth(newNameColumn.length());
        tableViewPoll.getColumns().add(column);
    }

    /**
     * Run popup to add mails
     */
    @FXML
    public void addMails() throws IOException {
        //Get Poll informations
        Poll poll = new Poll(namePollField.getText(), descriptionPollField.getText(), placePollField.getText());
        namePollField.setText("");
        descriptionPollField.setText("");
        placePollField.setText("");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ViewNavigator.MAILS_DIALOG));
        MailsDialogController mailsDialogController = new MailsDialogController(listView, poll);
        loader.setController(mailsDialogController);
        Parent content = (Parent) loader.load();
        Dialog.build("Ajouter collaborateurs");
        Dialog.getStage().setScene(new Scene(content));
        Dialog.getStage().show();
    }

    /**
     * Event add poll in ListView
     */
    @FXML
    public void addPollInListView(){

    }

    @FXML
    public void initialize(){
        tableViewPoll.setEditable(true);
    }
}
