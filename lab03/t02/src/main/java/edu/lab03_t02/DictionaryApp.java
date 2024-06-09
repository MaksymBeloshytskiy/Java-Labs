package edu.lab03_t02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class DictionaryApp extends Application {

    private Map<String, String> dictionary;
    private TextField searchField;
    private TextField englishField;
    private TextField ukrainianField;
    private TextArea resultArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("English-Ukrainian Dictionary");

        dictionary = new HashMap<>();
        populateDictionary();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        searchField = new TextField();
        searchField.setPromptText("Search word...");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchWord());

        englishField = new TextField();
        englishField.setPromptText("English word");

        ukrainianField = new TextField();
        ukrainianField.setPromptText("Ukrainian translation");

        Button addButton = new Button("Add Word");
        addButton.setOnAction(e -> addWord());

        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);

        HBox searchBox = new HBox(10);
        searchBox.getChildren().addAll(searchField, searchButton);

        HBox addBox = new HBox(10);
        addBox.getChildren().addAll(englishField, ukrainianField, addButton);

        grid.add(searchBox, 0, 0);
        grid.add(addBox, 0, 1);
        grid.add(resultArea, 0, 2, 2, 1);

        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void populateDictionary() {
        dictionary.put("hello", "привіт");
        dictionary.put("world", "світ");
        dictionary.put("apple", "яблуко");
        dictionary.put("book", "книга");
    }

    private void searchWord() {
        String searchKey = searchField.getText().trim().toLowerCase();
        String result = dictionary.getOrDefault(searchKey, "Word not found.");
        resultArea.setText("Search result:\n" + searchKey + " - " + result);
    }

    private void addWord() {
        String englishWord = englishField.getText().trim().toLowerCase();
        String ukrainianTranslation = ukrainianField.getText().trim();

        if (!englishWord.isEmpty() && !ukrainianTranslation.isEmpty()) {
            dictionary.put(englishWord, ukrainianTranslation);
            resultArea.setText("Word added:\n" + englishWord + " - " + ukrainianTranslation);
            englishField.clear();
            ukrainianField.clear();
        } else {
            resultArea.setText("Error: Both fields must be filled.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
