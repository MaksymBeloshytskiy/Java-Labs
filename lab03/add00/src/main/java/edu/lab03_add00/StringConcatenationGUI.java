package edu.lab03_add00;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StringConcatenationGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("String Concatenation");

        // Create UI components
        Label inputLabel1 = new Label("Input 1:");
        TextField inputField1 = new TextField();

        Label inputLabel2 = new Label("Input 2:");
        TextField inputField2 = new TextField();

        Button concatenateButton = new Button("Concatenate");
        Label resultLabel = new Label("Result:");

        // Event handler for button click
        concatenateButton.setOnAction(e -> {
            String input1 = inputField1.getText();
            String input2 = inputField2.getText();
            String result = input1 + " " + input2;
            resultLabel.setText("Result: " + result);
        });

        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        grid.add(inputLabel1, 0, 0);
        grid.add(inputField1, 1, 0);
        grid.add(inputLabel2, 0, 1);
        grid.add(inputField2, 1, 1);
        grid.add(concatenateButton, 0, 2);
        grid.add(resultLabel, 1, 2);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

