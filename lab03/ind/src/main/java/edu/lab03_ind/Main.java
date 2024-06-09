package edu.lab03_ind;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The main class that extends the Application class and serves as the entry point for the JavaFX application.
 */
/**
 * The Main class is the entry point of the application and extends the Application class.
 * It provides the main method to launch the application and defines the UI components and event handlers.
 */
public class Main extends Application {
    private TableView<Conference> conferenceTable;
    private TableView<Meeting> meetingTable;
    private List<Conference> conferences = new ArrayList<>();
    private boolean showingConferences = true;

    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application by calling the launch method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conference Manager");

        // Initialize buttons
        Button addConferenceButton = new Button("Add Conference");
        Button addMeetingButton = new Button("Add Meeting");
        Button switchViewButton = new Button("Switch View");
        Button searchMeetingButton = new Button("Search Meeting");

        // Initialize menu items
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> showAboutDialog());
        MenuItem saveMenuItem = new MenuItem("Save to XML");
        saveMenuItem.setOnAction(e -> saveToXML());
        MenuItem loadMenuItem = new MenuItem("Load from XML");
        loadMenuItem.setOnAction(e -> loadFromXML());

        // Initialize menus
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(saveMenuItem, loadMenuItem);
        Menu aboutMenu = new Menu("About");
        aboutMenu.getItems().addAll(aboutMenuItem);

        // Initialize menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, aboutMenu);

        // Set event handlers
        addConferenceButton.setOnAction(e -> showAddConferenceDialog());
        addMeetingButton.setOnAction(e -> showAddMeetingDialog());
        switchViewButton.setOnAction(e -> switchView());
        searchMeetingButton.setOnAction(e -> showSearchMeetingDialog());

        // Initialize conference table
        conferenceTable = new TableView<>();
        TableColumn<Conference, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Conference, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        conferenceTable.getColumns().addAll(nameColumn, locationColumn);

        // Initialize meeting table
        meetingTable = new TableView<>();
        TableColumn<Meeting, String> topicColumn = new TableColumn<>("Topic");
        topicColumn.setCellValueFactory(cellData -> cellData.getValue().topicProperty());
        TableColumn<Meeting, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        TableColumn<Meeting, Integer> participantsColumn = new TableColumn<>("Participants");
        participantsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getParticipants()).asObject());
        meetingTable.getColumns().addAll(topicColumn, dateColumn, participantsColumn);

        // Layout
        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(addConferenceButton, addMeetingButton, switchViewButton, searchMeetingButton);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(conferenceTable);
        layout.setBottom(buttons);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The switchView method is called when the switchViewButton is clicked.
     * It switches between displaying the conference table and the meeting table.
     */
    private void switchView() {
        BorderPane layout = (BorderPane) conferenceTable.getScene().getRoot();
        System.out.println("Switching view. Showing conferences: " + showingConferences);

        if (showingConferences) {
            // Collect all meetings from the conferences
            List<Meeting> meetings = conferences.stream()
                    .flatMap(conference -> conference.getMeetings().stream())
                    .collect(Collectors.toList());

            System.out.println("Number of meetings: " + meetings.size());

            // Check if there are any meetings to display
            if (meetings.isEmpty()) {
                showAlert("No Meetings", "There are no meetings to display.");
                return;
            }

            // Populate the meeting table with the meetings
            if (meetingTable != null) {
                System.out.println("Meeting table is not null");
                meetingTable.getItems().setAll(meetings);
                layout.setCenter(meetingTable);
            } else {
                System.out.println("Meeting table is null");
            }
        } else {
            System.out.println("Number of conferences: " + conferences.size());

            // Check if there are any conferences to display
            if (conferences.isEmpty()) {
                showAlert("No Conferences", "There are no conferences to display.");
                return;
            }

            // Populate the conference table with the conferences
            if (conferenceTable != null) {
                System.out.println("Conference table is not null");
                conferenceTable.getItems().setAll(conferences);
                layout.setCenter(conferenceTable);
            } else {
                System.out.println("Conference table is null");
            }
        }

        showingConferences = !showingConferences;
    }

    /**
     * The showAlert method displays an alert dialog with the given title and message.
     *
     * @param title   the title of the alert
     * @param message the message of the alert
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * The showAddConferenceDialog method displays a dialog for adding a new conference.
     * It prompts the user to enter the name and location of the conference.
     */
    private void showAddConferenceDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Conference");

        TextField nameField = new TextField();
        TextField locationField = new TextField();
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String location = locationField.getText();
            conferences.add(new Conference(name, location));
            conferenceTable.getItems().setAll(conferences);
            dialog.close();
        });

        VBox dialogVBox = new VBox(10, new Label("Name"), nameField, new Label("Location"), locationField, submitButton);
        dialogVBox.setPadding(new Insets(10));

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * The showAddMeetingDialog method displays a dialog for adding a new meeting.
     * It prompts the user to select a conference, enter the topic, date, and number of participants of the meeting.
     */
    private void showAddMeetingDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Meeting");

        // Create ComboBox for selecting a conference
        ComboBox<String> conferenceComboBox = new ComboBox<>();
        conferenceComboBox.getItems().setAll(conferences.stream().map(Conference::getName).collect(Collectors.toList()));

        TextField topicField = new TextField();
        DatePicker datePicker = new DatePicker();
        TextField participantsField = new TextField();
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String selectedConferenceName = conferenceComboBox.getValue();
            if (selectedConferenceName != null) {
                Conference selectedConference = conferences.stream()
                        .filter(conference -> conference.getName().equals(selectedConferenceName))
                        .findFirst()
                        .orElse(null);
                if (selectedConference != null) {
                    String topic = topicField.getText();
                    String date = datePicker.getValue().toString();
                    int participants = Integer.parseInt(participantsField.getText());
                    selectedConference.addMeeting(new Meeting(topic, date, participants));
                    if (!showingConferences) {
                        List<Meeting> meetings = conferences.stream()
                                .flatMap(conference -> conference.getMeetings().stream())
                                .collect(Collectors.toList());
                        meetingTable.getItems().setAll(meetings);
                    }
                    conferenceTable.refresh();
                    dialog.close();
                }
            }
        });

        VBox dialogVBox = new VBox(10,
                new Label("Conference"), conferenceComboBox,
                new Label("Topic"), topicField,
                new Label("Date"), datePicker,
                new Label("Participants"), participantsField,
                submitButton);
        dialogVBox.setPadding(new Insets(10));

        Scene dialogScene = new Scene(dialogVBox, 300, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * The showSearchMeetingDialog method displays a dialog for searching meetings.
     * It prompts the user to enter a search query and displays the search results.
     */
    private void showSearchMeetingDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Search Meeting");

        TextField searchField = new TextField();
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String searchQuery = searchField.getText().toLowerCase();
            List<Meeting> searchResults = conferences.stream()
                    .flatMap(conference -> conference.getMeetings().stream())
                    .filter(meeting -> meeting.getTopic().toLowerCase().contains(searchQuery))
                    .collect(Collectors.toList());
            meetingTable.getItems().setAll(searchResults);
            dialog.close();
            BorderPane layout = (BorderPane) conferenceTable.getScene().getRoot();
            layout.setCenter(meetingTable);
            showingConferences = false;
        });

        VBox dialogVBox = new VBox(10, new Label("Search Topic"), searchField, searchButton);
        dialogVBox.setPadding(new Insets(10));

        Scene dialogScene = new Scene(dialogVBox, 300, 150);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void showAboutDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("About");

        VBox dialogVBox = new VBox(10, new Label("Conference Manager v1.0"), new Label("Author: Maksym Beloshytskiy"));
        dialogVBox.setPadding(new Insets(10));

        Scene dialogScene = new Scene(dialogVBox, 200, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void serializeToXML(Object objectToSerialize, String fileName) {
        XStream xStream = new XStream(new DomDriver());
        try {
            FileWriter writer = new FileWriter(fileName);
            xStream.toXML(objectToSerialize, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({ "unchecked" })
    private List<Conference> deserializeFromXML(String fileName) {
        XStream xStream = new XStream(new DomDriver());
        try {
            return (List<Conference>) xStream.fromXML(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveToXML() {
        serializeToXML(conferences, "conferences.xml");
    }
    
    private void loadFromXML() {
        conferences = deserializeFromXML("conferences.xml");
        conferenceTable.getItems().setAll(conferences);
    }
}
