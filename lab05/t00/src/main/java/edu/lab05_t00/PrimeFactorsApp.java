package edu.lab05_t00;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeFactorsApp extends Application {
    private TextField startField;
    private TextField endField;
    private TextArea resultArea;
    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private Button stopButton;
    private ExecutorService executorService;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        primaryStage.setTitle("Prime Factors Finder");

        startField = new TextField();
        startField.setPromptText("Start of range");
        endField = new TextField();
        endField.setPromptText("End of range");

        resultArea = new TextArea();
        resultArea.setEditable(false);

        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        resumeButton = new Button("Resume");
        stopButton = new Button("Stop");

        pauseButton.setDisable(true);
        resumeButton.setDisable(true);
        stopButton.setDisable(true);

        startButton.setOnAction(e -> startCalculation());
        pauseButton.setOnAction(e -> pauseCalculation());
        resumeButton.setOnAction(e -> resumeCalculation());
        stopButton.setOnAction(e -> stopCalculation());

        // Create HBox for the input fields with margin
        HBox inputBox = new HBox(10, startField, endField);
        VBox.setMargin(inputBox, new Insets(10));

        // Create HBox for the buttons with margin
        HBox buttonBox = new HBox(10, startButton, pauseButton, resumeButton, stopButton);
        VBox.setMargin(buttonBox, new Insets(10));

        // Create VBox and add all elements
        VBox vbox = new VBox(10, inputBox, buttonBox, resultArea);
        vbox.setPadding(new Insets(20)); // Add padding to the VBox

        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startCalculation() {
        String startText = startField.getText();
        String endText = endField.getText();

        if (!isValidInput(startText) || !isValidInput(endText)) {
            showAlert("Invalid input", "Please enter valid integers for the range.");
            return;
        }

        int start = Integer.parseInt(startText);
        int end = Integer.parseInt(endText);

        if (start > end) {
            showAlert("Invalid range", "Start of range should be less than or equal to end of range.");
            return;
        }

        resultArea.clear();
        running = true;
        paused = false;
        pauseButton.setDisable(false);
        stopButton.setDisable(false);

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = start; i <= end; i++) {
            final int number = i;
            executorService.submit(() -> {
                if (!running) return;
                synchronized (this) {
                    while (paused) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            if (!running) return;
                        }
                    }
                }
                List<Integer> factors = findPrimeFactors(number);
                String result = number + ": " + factors.toString() + "\n";
                Platform.runLater(() -> resultArea.appendText(result));
                try {
                    TimeUnit.MILLISECONDS.sleep(100);  // Add delay to allow pause button interaction
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
    }

    private boolean isValidInput(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void pauseCalculation() {
        paused = true;
        pauseButton.setDisable(true);
        resumeButton.setDisable(false);
    }

    private synchronized void resumeCalculation() {
        paused = false;
        resumeButton.setDisable(true);
        pauseButton.setDisable(false);
        notifyAll();
    }

    private void stopCalculation() {
        running = false;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        pauseButton.setDisable(true);
        resumeButton.setDisable(true);
        stopButton.setDisable(true);
    }

    public List<Integer> findPrimeFactors(int number) {
        List<Integer> factors = new ArrayList<>();
        while (number % 2 == 0) {
            factors.add(2);
            number /= 2;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }
        if (number > 2) {
            factors.add(number);
        }
        return factors;
    }
}
