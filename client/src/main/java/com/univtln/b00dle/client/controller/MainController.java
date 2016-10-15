package com.univtln.b00dle.client.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//http://stackoverflow.com/questions/18619394/loading-new-fxml-in-the-same-scene

/**
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
