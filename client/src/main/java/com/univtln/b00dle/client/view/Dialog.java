package com.univtln.b00dle.client.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Stéphen on 15/10/2016.
 */
public class Dialog {

    /**
     * Dialog allow to instanciate Stage variable to use for Dialog
     * This variable can't be instanciante into Controller because he never instanciate
     */

    private static Stage stage;

    /**
     * Create stage
     */
    public static void build(){
        stage = new Stage();
    }

    public static Stage getStage(){
        return stage;
    }

}
