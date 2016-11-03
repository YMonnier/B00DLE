package com.univtln.b00dle.client.controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Stéphen on 07/10/2016.
 * MainController class for the entire layout.
 */

public class MainController {

    /** Holder of a switchable FXML file. */
    @FXML
    private StackPane stackPane;

    /**
     * Replaces the fxml file displayed in the fxml holder with a new view.
     *
     * @param node the view node to be swapped in.
     */
    public void setStackPane(Node node) {
        stackPane.getChildren().setAll(node);
    }
}
