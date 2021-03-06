package com.univtln.b00dle.client.controller;

import com.univtln.b00dle.client.model.*;
import com.univtln.b00dle.client.view.MainApp;
import com.univtln.b00dle.client.view.ViewNavigator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Stéphen on 15/10/2016.
 * Controller of viewPoll.fxml
 */
public class PollController {

    private static final Logger LOGGER = Logger.getLogger(PollController.class);

    /**
     * Reference of the model
     */
    private OpinionPoll opinionPoll;

    /**
     * Variable FXML
     * are instanciate when fxml file is load
     */
    @FXML
    private TableView<Person> tableView;
    //private TableView<Answer> tableView;

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

    @FXML
    private Button loginButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button sendButton;

    @FXML
    private Button sendAnswerButton;

    private final ObservableList<Person> data = FXCollections.observableArrayList();

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public PollController(OpinionPoll opinionPoll) {
        this.opinionPoll = opinionPoll;
    }


    /**
     * Event load home.fxml in stage
     */
    @FXML
    public void viewHomeAction() {
        LOGGER.debug("viewHomeAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.HOME));

        try {
            MainApp.mainStage.setScene(new Scene((Pane)loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Event load login.fxml
     */
    @FXML
    public void viewLoginAction() {
        LOGGER.debug("viewLoginAction::run view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewNavigator.LOGIN));

        try {
            MainApp.mainStage.setScene(new Scene((Pane)loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainApp.mainStage.show();
    }

    /**
     * Add a message into chat poll
     */
    @FXML
    public void sendMessage() {
        String name = chatNameField.getText();
        String message = chatMessageField.getText();
        String chatMessage = name + " : " + message;
        //model.addChatMessage(HomeController.getLink(), chatMessage);
        //ObservableList<Test> listMessage = FXCollections.observableArrayList(model.getChatMessage(HomeController.getLink()));
        //chat.setItems(listMessage);
        //chatMessageField.setText("");
    }


    private List<TableColumn<Person, ? extends Object>> columns() {
        final List<TableColumn<Person, ?>> columns = new ArrayList<>();

        final TableColumn<Person, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(cell -> cell.getValue().nameProperty());
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());

        //final TableColumn<Person, Boolean> columnClose = new TableColumn<>("Close");
        //columnClose.setCellValueFactory(cell -> cell.getValue().closeProperty());
        //columnClose.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("Close"));
        //columnClose.setCellFactory(CheckBoxTableCell.forTableColumn(param -> this.tableView.getItems().get(param).closeProperty()));

        columns.add(columnName);
        //columns.add(columnClose);



        /*final TableColumn<Person, Integer> idFast = new TableColumn<>("Age");
        idFast.setCellValueFactory(cell -> cell.getValue().ageProperty());
        columns.add(idFast);
        */

        return columns;
    }

    /**
     * Initialize controller
     * Fonction as a constructor/main in javaFX
     */
    @FXML
    public void initialize() {
        this.loginButton.setOnAction(e -> this.viewLoginAction());
        this.homeButton.setOnAction(e -> this.viewHomeAction());
        this.sendButton.setOnAction(e -> this.sendMessage());
        this.sendAnswerButton.setOnAction(e -> this.sendAnswerAction());

        this.name.setText(this.opinionPoll.getTitle());
        this.description.setText(this.opinionPoll.getDescription());
        this.place.setText(this.opinionPoll.getPlace());


        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Jpds", "OK", "OK"));
        persons.add(new Person("Jzedezzz", "", "OK"));
        persons.add(new Person("&ézed", "OK", ""));
        data.addAll(persons);

        /*
        for (TimeSlot timeSlot : this.opinionPoll.getTimeSlots()) {
            String newNameColumn = timeSlot.toString();
            LOGGER.debug("-> " + newNameColumn);
            TableColumn column = new TableColumn(newNameColumn);
            column.setCellValueFactory(new PropertyValueFactory<Answer, Boolean>(newNameColumn));
            column.setMinWidth(newNameColumn.length());
            tableView.getColumns().add(column);
        }

*/
        this.tableView.getColumns().addAll(columns());

  //      this.tableView.setOnMouseClicked(event -> this.data.forEach(answer -> System.out.println(answer.toString())));

        this.tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                TablePosition firstCell = tableView.getSelectionModel().getSelectedCells().get(0);
                System.out.println("Column : " + firstCell.getColumn() + " row : " + firstCell.getRow());
                Person person = tableView.getSelectionModel().getSelectedItem();
                System.out.println(person.toString());
                TableColumn column = tableView.getColumns().get(firstCell.getColumn());
                System.out.println(column.getText());
                //Quand on clique sur une cellule on recupere le timeSlot ok
                //On ajoute le timeSlot dans une list par exemple listReponses
                //Quand on click sur le bouton on ajout la list a la list des timeSlots
            }
        });


        //Get chat message
        //List<Test> listMessage = model.getChatMessage(HomeController.getLink());

        //Get and put all informations about poll and textArea become not editable
        this.name.setText(this.opinionPoll.getTitle());
        this.description.setText(this.opinionPoll.getDescription());
        this.place.setText(opinionPoll.getPlace());

        //Makes the table editable
        tableView.setEditable(true);

        //Makes the name editable column
        //tableColumnName.setCellFactory(TextFieldTableCell.<TableItem>forTableColumn());

        //Add date in table
        LOGGER.debug("ADD COLUMNS...");
        LOGGER.debug(this.opinionPoll);
        if (this.opinionPoll.getTimeSlots() != null) {
            for (TimeSlot timeSlot : this.opinionPoll.getTimeSlots()) {
                String newNameColumn = timeSlot.toString();
                LOGGER.debug("-> " + newNameColumn);
                final TableColumn<Person, String> columnName = new TableColumn<>(newNameColumn);
                columnName.setCellValueFactory(cell -> cell.getValue().name2Property());
                columnName.setMinWidth(newNameColumn.length());
                tableView.getColumns().add(columnName);
                columnName.setCellFactory(TextFieldTableCell.forTableColumn());
            }
        }

        this.tableView.setItems(data);
        this.tableView.setEditable(true);

        //Makes the table editable and empty line
        tableView.setEditable(true);


        //final ObservableList<Answer> data =FXCollections.observableArrayList(new Answer.Builder().build());
        //tableView.setItems(data);
/*
        if (this.opinionPoll.getAnswers() != null) {
            LOGGER.debug("ADD LINE...");
            for (Answer answer : this.opinionPoll.getAnswers()) {
                //tableView.getItems().add(new Item());
                //Answer aw = new Answer.Builder().build();
                this.tableView.getItems().add(answer);
                LOGGER.debug("DONE");
                if (tableView.getColumns() != null) {
                    for (int i = 1; i < this.tableView.getColumns().size(); i++) {
                        TimeSlot currentTimeSlot = this.opinionPoll.getTimeSlots().get(i-1);

                        LOGGER.debug("----");
                        LOGGER.debug(answer.getTimeSlots());
                        LOGGER.debug("contains");
                        LOGGER.debug(currentTimeSlot);

                        if (answer.getTimeSlots().contains(currentTimeSlot.getId())) {
                            LOGGER.debug(currentTimeSlot + " found!");

                        }
                    }
                }

            }
        }
*/
        //Answer poll
 /*       tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TablePosition tablePosition = tableView.getSelectionModel().getSelectedCells().get(0);

                System.out.println(tablePosition.getColumn());
            }
        });
*/
    }

    private void sendAnswerAction() {
        for (Object r : this.tableView.getItems()) {
            for (Object c : this.tableView.getColumns()) {
                TableColumn column = (TableColumn) c;
                LOGGER.info("Cell data contain ->" + column.getCellData(r));
                if (column.getCellData(r).equals("OK")) {
                    LOGGER.info("ADD TIME SLOT IN LIST...");
                    // I get the object bound to the cell here, but I only want
                    // to have what i've set as graphics - what the user sees on UI

                }
            }
        }
    }


    private TimeSlot parseDate(String s) {
        LOGGER.debug("parseDate: " + s);
        String[] tab = s.split("\n");
        assert tab.length == 2;

        return new TimeSlot.Builder()
                .setFrom(tab[0])
                .setTo(tab[1])
                .build();
    }
}
