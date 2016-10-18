package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.model.poll.Poll;
import com.univtln.b00dle.client.model.poll.ResponseTest;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

/**
 * Created by Stéphen on 15/10/2016.
 */
public class PollController{

    @FXML
    TableView tableViewResponsePoll;

    @FXML
    TableColumn<ResponseTest, String> reponse;

    @FXML
    TableColumn<ResponseTest, String> reponse2;

    @FXML
    ResponseTest lig;

    @FXML
    ListView chat;

    @FXML
    TextField chatMessageField;

    @FXML
    TextArea description;

    @FXML
    TextArea place;

    @FXML
    Label name;

    private Model model;

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */

    /** The constructor. The constructor is called before the initialize()
      * method.
      */
    public PollController(){
        this.model = new Model();
    }

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneLogin(){
        ViewNavigator.loadVista(ViewNavigator.LOGIN);
    }

    /**
     * Add a message into chat poll
     */
    @FXML
    public void addChatMessage(){
        String message = chatMessageField.getText();
        model.addChatMessage("484f74", message);
        ObservableList<String> listMessage = FXCollections.observableArrayList(model.getChatMessage("484f74"));
        chat.setItems(listMessage);
    }

    /**
     * Initialize controller
     * Fonction as a constructor in javaFX
     */

    @FXML
    public void initialize() {
        //Get chat message
        List<String> listMessage = model.getChatMessage("484f74");

        //Get and put all informations about poll and textArea become not editable
        Poll poll = model.getPoll("484f74");
        name.setText(poll.getName());
        description.setText(poll.getDescription());
        description.setEditable(false);
        place.setText(poll.getPlace());
        place.setEditable(false);

        //Rend le tableau editable
        tableViewResponsePoll.setEditable(true);
        //Rend la colone response editable
        reponse.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
        reponse2.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
    }

}
