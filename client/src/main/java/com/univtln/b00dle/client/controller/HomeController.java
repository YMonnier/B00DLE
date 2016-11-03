package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.Answer;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.model.TimeSlot;
import com.univtln.b00dle.client.model.boodle.exception.PollNotFoundException;
import com.univtln.b00dle.client.view.MainApp;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stéphen on 11/10/2016.
 * Controller of home.fxml
 */
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    /**
     * Variable static contain link of poll enter by user
     * Permit to get and load all informations about the poll
     */
    private static String link;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TextField linkField;
/*
    @FXML
    private Button loginButton;

    @FXML
    private Button viewOpinionButton;
*/
    /**
     * Event load login.fxml
     */
    public void viewLoginAction() {
        ViewNavigator.loadFXMLFile(ViewNavigator.LOGIN);
    }

    /**
     * Instanciate link get by linkField
     * Event load viewPoll.fxml
     */
    public void viewOpinionPollAction() {
        LOGGER.debug("viewOpinionPollAction");

        TimeSlot t1 = new TimeSlot.Builder()
                .setFrom("2016-10-04 10:30")
                .setTo("2016-10-04 11:30")
                .build();
        t1.setId(1);


        TimeSlot t2 = new TimeSlot.Builder()
                .setFrom("2016-10-05 10:30")
                .setTo("2016-10-05 11:30")
                .build();
        t2.setId(2);


        List<Integer> ts = new ArrayList<>();
        ts.add(1);
        ts.add(2);
        Answer answer = new Answer.Builder()
                .setName("John")
                .setTimeSlots(ts)
                .build();

        List<Answer> answers = new ArrayList<>();
        answers.add(answer);


        OpinionPoll opinionPoll = new OpinionPoll.Builder()
                .setDescription("My decriptipon")
                .setPlace("Paris")
                .setTitle("Amazing")
                .setAnswers(answers)
                .build();
        opinionPoll.getTimeSlots().add(t1);
        opinionPoll.getTimeSlots().add(t2);

        LOGGER.debug("viewOpinionPollAction::next view");

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        ViewNavigator.VIEW_POLL
                )
        );

        PollController m = new PollController(opinionPoll);
        loader.setController(m);

        try {
            MainApp.mainStage.setScene(
                    new Scene(
                            (Pane)loader.load()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    @FXML
    public void initialize() {
        LOGGER.debug("initialize");
        //this.loginButton.setOnAction(e -> this.viewLoginAction());
        //this.viewOpinionButton.setOnAction(e -> this.viewOpinionPollAction());
    }


    /**
     * Return current link
     * @return Static String
     * @throws PollNotFoundException
     */
    protected static String getLink() {
        return link;
    }

}
