package edu.lab04_t01;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ConferenceManagerGUI extends Application {
    private static final String URL = "jdbc:mysql://localhost:3306/ConferenceDB";
    private static final String USER = "root";
    private static final String PASSWORD = "228228R@x";

    private TableView<Conference> conferenceTable;
    private ObservableList<Conference> conferenceData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conference Manager");

        // Set up table view
        conferenceTable = new TableView<>();
        conferenceTable.setEditable(true);

        TableColumn<Conference, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        idColumn.setMinWidth(50);

        TableColumn<Conference, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setMinWidth(200);

        TableColumn<Conference, String> placeColumn = new TableColumn<>("Place");
        placeColumn.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
        placeColumn.setMinWidth(200);

        conferenceTable.getColumns().addAll(idColumn, nameColumn, placeColumn);

        // Set up buttons
        Button addConferenceButton = new Button("Add Conference");
        addConferenceButton.setOnAction(e -> showAddConferenceDialog());

        Button addMeetingButton = new Button("Add Meeting");
        addMeetingButton.setOnAction(e -> showAddMeetingDialog());

        Button loadFromFileButton = new Button("Load from File");
        loadFromFileButton.setOnAction(e -> loadFromFile(primaryStage));

        Button saveToFileButton = new Button("Save to File");
        saveToFileButton.setOnAction(e -> saveToFile(primaryStage));

        Button deleteConferenceButton = new Button("Delete Conference");
        deleteConferenceButton.setOnAction(e -> deleteSelectedConference());

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addConferenceButton, addMeetingButton, loadFromFileButton, saveToFileButton, deleteConferenceButton);

        BorderPane layout = new BorderPane();
        layout.setTop(buttonBox);
        layout.setCenter(conferenceTable);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load data into table
        loadData();
    }

    private void loadData() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Conferences")) {
            conferenceData.clear();
            while (rs.next()) {
                Conference conference = new Conference();
                conference.setId(rs.getInt("id"));
                conference.setName(rs.getString("name"));
                conference.setPlace(rs.getString("place"));
                conferenceData.add(conference);
            }
            conferenceTable.setItems(conferenceData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAddConferenceDialog() {
        Dialog<Conference> dialog = new Dialog<>();
        dialog.setTitle("Add Conference");

        // Set up the buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set up the input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField placeField = new TextField();
        placeField.setPromptText("Place");

        VBox content = new VBox(10);
        content.getChildren().addAll(new Label("Name:"), nameField, new Label("Place:"), placeField);
        dialog.getDialogPane().setContent(content);

        // Convert the result to a conference object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Conference conference = new Conference();
                conference.setName(nameField.getText());
                conference.setPlace(placeField.getText());
                return conference;
            }
            return null;
        });

        Optional<Conference> result = dialog.showAndWait();
        result.ifPresent(this::addConference);
    }

    private void addConference(Conference conference) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Conferences (name, place) VALUES (?, ?)")) {
            stmt.setString(1, conference.getName());
            stmt.setString(2, conference.getPlace());
            stmt.executeUpdate();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAddMeetingDialog() {
        Dialog<Meeting> dialog = new Dialog<>();
        dialog.setTitle("Add Meeting");

        // Set up the buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set up the input fields
        TextField conferenceIdField = new TextField();
        conferenceIdField.setPromptText("Conference ID");
        TextField topicField = new TextField();
        topicField.setPromptText("Topic");
        TextField dateField = new TextField();
        dateField.setPromptText("Date (YYYY-MM-DD)");

        VBox content = new VBox(10);
        content.getChildren().addAll(new Label("Conference ID:"), conferenceIdField, new Label("Topic:"), topicField, new Label("Date:"), dateField);
        dialog.getDialogPane().setContent(content);

        // Convert the result to a meeting object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Meeting meeting = new Meeting();
                meeting.setConferenceId(Integer.parseInt(conferenceIdField.getText()));
                meeting.setName(topicField.getText());
                meeting.setDate(dateField.getText());
                return meeting;
            }
            return null;
        });

        Optional<Meeting> result = dialog.showAndWait();
        result.ifPresent(this::addMeeting);
    }

    private void addMeeting(Meeting meeting) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Meetings (conference_id, topic, date) VALUES (?, ?, ?)")) {
            stmt.setInt(1, meeting.getConferenceId());
            stmt.setString(2, meeting.getName());
            stmt.setString(3, meeting.getDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Conference> conferences = objectMapper.readValue(selectedFile, new TypeReference<List<Conference>>() {});
                for (Conference conference : conferences) {
                    addConference(conference);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            try {
                List<Conference> conferences = fetchDataFromDatabase();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(selectedFile, conferences);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Conference> fetchDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Conferences")) {
            ObservableList<Conference> conferences = FXCollections.observableArrayList();
            while (rs.next()) {
                Conference conference = new Conference();
                conference.setId(rs.getInt("id"));
                conference.setName(rs.getString("name"));
                conference.setPlace(rs.getString("place"));
                conferences.add(conference);
            }
            return conferences;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void deleteSelectedConference() {
        Conference selectedConference = conferenceTable.getSelectionModel().getSelectedItem();
        if (selectedConference != null) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM Conferences WHERE id = ?")) {
                stmt.setInt(1, selectedConference.getId());
                stmt.executeUpdate();
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Conference Selected");
            alert.setContentText("Please select a conference in the table.");
            alert.showAndWait();
        }
    }
}
