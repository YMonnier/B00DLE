package com.univtln.b00dle.client.view;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import com.univtln.b00dle.client.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Created by Stéphen on 11/10/2016.
 */

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class ViewNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN = "/fxml/main.fxml";
    public static final String HOME = "/fxml/home.fxml";
    public static final String LOGIN = "/fxml/login.fxml";
    public static final String CREATE_ACCOUNT = "/fxml/createAccount.fxml";
    public static final String VIEW_POLL = "/fxml/viewPoll.fxml";
    public static final String DASHBORD = "/fxml/dashbord.fxml";
    public static final String MAILS_DIALOG = "/fxml/dialogAddMails.fxml";
    public static final String MODIFY_POLL = "/fxml/modifyPollDashbord.fxml";
    public static final String ADD_POLL = "/fxml/addPollDashbord.fxml";
}