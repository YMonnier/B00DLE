package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Model;
import com.univtln.b00dle.client.model.boodle.poll.Date;
import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.model.boodle.poll.Response;
import com.univtln.b00dle.client.model.boodle.poll.TableItem;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

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
    private TableView<TableItem> tableViewResponsePoll;

    @FXML
    private TableColumn tableColumnName;

    @FXML
    private ListView chat;

    @FXML
    private TextField chatMessageField;

    @FXML
    private TextField chatNameField;

    @FXML
    private Label description;

    @FXML
    private Label place;

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
     * Fonction as a constructor/main in javaFX
     */
    @FXML
    public void initialize() {

        //Get chat message
        List<String> listMessage = model.getChatMessage(HomeController.getLink());

        //Get and put all informations about poll and textArea become not editable
        Poll poll = model.getPollByLink(HomeController.getLink());
        name.setText(poll.getName());
        description.setText(poll.getDescription());
        place.setText(poll.getPlace());

        //Makes the table editable
        tableViewResponsePoll.setEditable(true);

        //Makes the name editable column
        tableColumnName.setCellFactory(TextFieldTableCell.<TableItem>forTableColumn());

        //Add date in table
        if(poll.getDates() != null) {
            for(Date date : poll.getDates()){
                String newNameColumn = date.toString();
                TableColumn column = new TableColumn(newNameColumn);
                column.setCellValueFactory(new PropertyValueFactory<TableItem, String>(newNameColumn));
                column.setMinWidth(newNameColumn.length());
                tableViewResponsePoll.getColumns().add(column);
                column.setCellFactory(TextFieldTableCell.<TableItem>forTableColumn());
            }
        }

        //Add empty line response

        //Makes the table editable
        tableViewResponsePoll.setEditable(true);

        final ObservableList<TableItem> data =
                FXCollections.observableArrayList(
                    new Response()
                );
        tableViewResponsePoll.setItems(data);
    }

}
