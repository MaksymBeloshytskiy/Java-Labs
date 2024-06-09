package edu.lab03_add02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

        // Event handler for button click
        multiplyButton.setOnAction(e -> {
            try {
                int number1 = Integer.parseInt(inputField1.getText());
                int number2 = Integer.parseInt(inputField2.getText());
                int result = number1 * number2;
                // Display result in a dialog window
                showAlert("Result", "The product of the numbers is: " + result, AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter valid integer numbers", AlertType.ERROR);
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

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

