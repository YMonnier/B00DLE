package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.model.boodle.exception.PollNotFoundException;
import com.univtln.b00dle.client.model.boodle.poll.Date;
import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.model.boodle.poll.ResponseTest;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;
import java.util.Map;

/**
 * Created by Stéphen on 15/10/2016.
 * Controller of viewPoll.fxml
 */
public class PollController{

    /**
     * Controller can use Model -> MVC
     */
    private Model model;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TableView tableViewResponsePoll;

    @FXML
    private TableColumn<ResponseTest, String> reponse;

    @FXML
    private TableColumn<ResponseTest, String> reponse2;

    @FXML
    private ListView chat;

    @FXML
    private TextField chatMessageField;

    @FXML
    private TextField chatNameField;

    @FXML
    private TextArea description;

    @FXML
    private TextArea place;

    @FXML
    private Label name;

    /** The constructor. The constructor is called before the initialize()
      * method.
      */
    public PollController(){
        this.model = new Model();
    }

    /**
     * Event load home.fxml
     */
    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

    /**
     * Event load login.fxml
     */
    @FXML
    public void nextPaneLogin(){
        ViewNavigator.loadFXMLFile(ViewNavigator.LOGIN);
    }

    /**
     * Add a message into chat poll
     */
    @FXML
    public void addChatMessage(){
        String name = chatNameField.getText();
        String message = chatMessageField.getText();
        String chatMessage = name + " : " + message;
        model.addChatMessage(HomeController.getLink(), chatMessage);
        ObservableList<String> listMessage = FXCollections.observableArrayList(model.getChatMessage(HomeController.getLink()));
        chat.setItems(listMessage);
        chatMessageField.setText("");
    }

    /**
     * Initialize controller
     * Fonction as a constructor in javaFX
     */
    @FXML
    public void initialize() {

        //Get chat message
        List<String> listMessage = model.getChatMessage(HomeController.getLink());

        //Get and put all informations about poll and textArea become not editable
        Poll poll = model.getPollByLink(HomeController.getLink());
        name.setText(poll.getName());
        description.setText(poll.getDescription());
        description.setEditable(false);
        place.setText(poll.getPlace());
        place.setEditable(false);

        //Add date in table
        for(Date date : poll.getDates()){
            String newNameColumn = date.toString();
            TableColumn<Date, String> column = new TableColumn<>(newNameColumn);
            column.setCellValueFactory(new MapValueFactory(newNameColumn));
            column.setMinWidth(newNameColumn.length());
            tableViewResponsePoll.getColumns().add(column);
            column.setCellFactory(TextFieldTableCell.<Date>forTableColumn());
        }

        //Makes the table editable
        tableViewResponsePoll.setEditable(true);

        //Makes the response editable column
        //reponse.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
        //reponse2.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
    }

}
