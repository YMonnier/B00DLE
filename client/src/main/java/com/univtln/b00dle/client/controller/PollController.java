package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.model.boodle.exception.PollNotFoundException;
import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.model.boodle.poll.ResponseTest;
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
    TextField chatNameField;

    @FXML
    TextArea description;

    @FXML
    TextArea place;

    @FXML
    Label name;

    private Model model;

    private String link;

    /** The constructor. The constructor is called before the initialize()
      * method.
      */
    public PollController(){
        this.model = new Model();
    }

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadFXMLFile(ViewNavigator.HOME);
    }

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
        model.addChatMessage(link, chatMessage);
        ObservableList<String> listMessage = FXCollections.observableArrayList(model.getChatMessage(link));
        chat.setItems(listMessage);
        chatMessageField.setText("");
    }

    /**
     * Initialize controller
     * Fonction as a constructor in javaFX
     */

    @FXML
    public void initialize() {
        //Get link poll
        try {
            this.link = HomeController.getLink();
        } catch (PollNotFoundException e) {

        }


        //Get chat message
        List<String> listMessage = model.getChatMessage(link);

        //Get and put all informations about poll and textArea become not editable
        Poll poll = model.getPollByLink(link);
        name.setText(poll.getName());
        description.setText(poll.getDescription());
        description.setEditable(false);
        place.setText(poll.getPlace());
        place.setEditable(false);

        //Rend le tableau editable
        tableViewResponsePoll.setEditable(true);

        //Rend la colonne response editable
        reponse.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
        reponse2.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
    }

}
