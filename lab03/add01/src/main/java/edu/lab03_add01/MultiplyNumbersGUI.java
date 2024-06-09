package edu.lab03_add01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MultiplyNumbersGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Multiply Numbers");

        // Create UI components
        Label inputLabel1 = new Label("Number 1:");
        TextField inputField1 = new TextField();

        Label inputLabel2 = new Label("Number 2:");
        TextField inputField2 = new TextField();

        Button multiplyButton = new Button("Multiply");
        Label resultLabel = new Label("Result:");

        // Event handler for button click
        multiplyButton.setOnAction(e -> {
            try {
                double number1 = Double.parseDouble(inputField1.getText());
                double number2 = Double.parseDouble(inputField2.getText());
                double result = number1 * number2;
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
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
        grid.add(multiplyButton, 0, 2);
        grid.add(resultLabel, 1, 2);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

