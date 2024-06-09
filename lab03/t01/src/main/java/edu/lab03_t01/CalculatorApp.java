package edu.lab03_t01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private TextField numberField1;
    private TextField numberField2;
    private TextField resultField;
    private ToggleGroup operationGroup; // Define ToggleGroup as a class variable

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        numberField1 = new TextField();
        numberField2 = new TextField();
        resultField = new TextField();
        resultField.setEditable(false);

        operationGroup = new ToggleGroup(); // Initialize ToggleGroup

        RadioButton addRadioButton = new RadioButton("Add");
        addRadioButton.setToggleGroup(operationGroup);
        addRadioButton.setSelected(true);

        RadioButton subtractRadioButton = new RadioButton("Subtract");
        subtractRadioButton.setToggleGroup(operationGroup);

        RadioButton multiplyRadioButton = new RadioButton("Multiply");
        multiplyRadioButton.setToggleGroup(operationGroup);

        RadioButton divideRadioButton = new RadioButton("Divide");
        divideRadioButton.setToggleGroup(operationGroup);

        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculate());

        grid.add(numberField1, 0, 0);
        grid.add(numberField2, 0, 1);
        grid.add(new HBox(10, addRadioButton, subtractRadioButton, multiplyRadioButton, divideRadioButton), 1, 0);
        grid.add(calculateButton, 1, 1);
        grid.add(resultField, 0, 2, 2, 1);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate() {
        double num1 = Double.parseDouble(numberField1.getText());
        double num2 = Double.parseDouble(numberField2.getText());
        double result = 0.0;

        RadioButton selectedRadioButton = (RadioButton) operationGroup.getSelectedToggle(); // Use operationGroup directly
        String operation = selectedRadioButton.getText();

        switch (operation) {
            case "Add":
                result = num1 + num2;
                break;
            case "Subtract":
                result = num1 - num2;
                break;
            case "Multiply":
                result = num1 * num2;
                break;
            case "Divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultField.setText("Error: Division by zero");
                    return;
                }
                break;
        }

        resultField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
