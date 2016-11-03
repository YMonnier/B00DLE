package com.univtln.b00dle.client.view;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Created by Stéphen on 15/10/2016.
 * Singleton Object to display alert dialog to send mails in application
 */
public class Dialog {

    /**
     * Dialog allow to instanciate Stage variable to use for Dialog
     * This variable can't be instanciante into Controller because he never instanciate
     */
    private static Stage stage;

    /**
     * Create singleton Stage
     */
    public static Stage build(String name){
        if(stage == null) {
            stage = new Stage();
            stage.setTitle(name);
            return stage;
        }
        else
            return stage;
    }

    /**
     * Getter varibale static stage of Dialog
     * @return stage of the dialog
     */
    public static Stage getStage(){
        return stage;
    }

    /**
     * Show Dialog
     * @param title title of the stage
     * @param content content of the stage
     * @param type type of alert
     */
    public static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
