package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.poll.ResponseTest;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

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

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */

    /** The constructor. The constructor is called before the initialize()
      * method.
      */
    public PollController() {

    }

    @FXML
    public void nextPaneHome() {
        ViewNavigator.loadVista(ViewNavigator.HOME);
    }

    @FXML
    public void nextPaneLogin(){
        ViewNavigator.loadVista(ViewNavigator.LOGIN);
    }

    @FXML
    public void initialize() {
        //Rend le tableau editable
        tableViewResponsePoll.setEditable(true);
        //Rend la colone response editable
        reponse.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
        reponse2.setCellFactory(TextFieldTableCell.<ResponseTest>forTableColumn());
    }
}
