package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.view.MainApp;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeFunction.call;

/**
 * Created by Stéphen on 15/10/2016.
 * Controller of dashbord.fxml
 */

public class DashboardController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    /**
     * Variable FXML
     * are instanciate when fxml file is load
     * Pane is a area who can put other fxml file
     * Here AddPollDashbord.fxml and ModifyPollDashbord.fxml
     */
    @FXML
    private Pane pane;

    @FXML
    private Button viewPollButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    /**
     * ListView display poll who are create by administrator
     */
    @FXML
    private ListView<OpinionPoll> listView;

    private ObservableList<OpinionPoll> observableList;

    /**
     * Default Constructor
     */
    public DashboardController(List<OpinionPoll> opinionPolls) {
        LOGGER.debug("DashboardController:: " + opinionPolls);
        assert opinionPolls != null;
        this.observableList = FXCollections.observableArrayList(opinionPolls);
    }

    /**
     * Event load home.fxml
     */
    @FXML
    public void viewHomeAction() {
        LOGGER.debug("viewHomeAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        try {
            MainApp.mainStage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Event load go back to the home view
     * Disconnect administrator and load home.fxml
     */
    @FXML
    public void logoutAction() {
        LOGGER.debug("viewLogoutAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        try {
            MainApp.mainStage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Load inside the pane AddPollDashbord.fxml
     *
     * @throws IOException
     */
    @FXML
    public void viewPollFormAction() {
        LOGGER.info("viewPollFormAction:: add view in pane");
        AddPollController addPollController =
                new AddPollController(listView);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        ViewNavigator.ADD_POLL
                )
        );
        loader.setController(addPollController);
        pane.getChildren().clear();
        try {
            pane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load inside the pane ModifyPollDashbord.fxml
     * Show allo information that you can edit and sent to the server.
     *
     * @throws IOException
     */
    @FXML
    public void viewModifyPollAction() {
        LOGGER.info("viewModifyPollAction:: add view in pane");
        OpinionPoll opinionPoll = listView.getSelectionModel().getSelectedItem();
        if (opinionPoll != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.MODIFY_POLL));
            ModifyPollController m = new ModifyPollController(opinionPoll);
            loader.setController(m);
            pane.getChildren().clear();
            try {
                pane.getChildren().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fonction initialize when fxml file is load
     * Get and add poll in ListView behove administrator
     */
    @FXML
    public void initialize() {
        LOGGER.info("Initialize DashbordController");

        this.listView.setCellFactory(param -> new CustomCell());
        this.listView.getItems().addAll(this.observableList);

        this.listView.setOnMouseClicked(e -> this.viewModifyPollAction());

        this.viewPollButton.setOnAction(e -> this.viewPollFormAction());
        this.logoutButton.setOnAction(e -> this.logoutAction());
        this.homeButton.setOnAction(e -> this.viewHomeAction());
    }

    private final static class CustomCell extends ListCell<OpinionPoll> {
        @Override
        protected void updateItem(OpinionPoll item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getId() + " - " + item.getTitle());
            }
        }
    }
}
