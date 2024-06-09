package edu.lab05_ind00;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.concurrent.Task;

import javax.imageio.ImageIO;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.net.URL;

public class FunctionPlotter extends Application {
    private TextField aField;
    private TextField bField;
    private TextField fField;
    private TextField gField;
    private TextField minXField;
    private TextField maxXField;
    private LineChart<Number, Number> lineChart;
    private ProgressIndicator progressIndicator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        primaryStage.setTitle("Function Plotter");

        Label aLabel = new Label("a:");
        aField = new TextField();

        Label bLabel = new Label("b:");
        bField = new TextField();

        Label fLabel = new Label("f(x):");
        fField = new TextField();

        Label gLabel = new Label("g(x):");
        gField = new TextField();

        Label minXLabel = new Label("min x:");
        minXField = new TextField();

        Label maxXLabel = new Label("max x:");
        maxXField = new TextField();

        // Apply white color to all labels
        String labelStyle = "-fx-text-fill: white;";
        aLabel.setStyle(labelStyle);
        bLabel.setStyle(labelStyle);
        fLabel.setStyle(labelStyle);
        gLabel.setStyle(labelStyle);
        minXLabel.setStyle(labelStyle);
        maxXLabel.setStyle(labelStyle);

        Button plotButton = new Button("Plot");
        plotButton.setOnAction(e -> plotGraph());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearFields());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveGraph(primaryStage));

        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);

        VBox inputVBox = new VBox(10);
        inputVBox.getChildren().addAll(aLabel, aField, bLabel, bField, fLabel, fField, gLabel, gField, minXLabel, minXField, maxXLabel, maxXField);

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(plotButton, clearButton, saveButton);
        buttonHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonHBox, new Insets(20, 0, 0, 0));

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(inputVBox, progressIndicator, buttonHBox);
        vbox.setStyle("-fx-border-color: black; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #363537;");

        BorderPane root = new BorderPane();
        root.setLeft(vbox);
        root.setCenter(lineChart);
        BorderPane.setMargin(vbox, new Insets(20));
        BorderPane.setMargin(lineChart, new Insets(20));

        // Bind the height of the line chart to the height of the vbox
        lineChart.prefHeightProperty().bind(vbox.heightProperty());

        Scene scene = new Scene(root, 1000, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void plotGraph() {
        try {
            double a = Double.parseDouble(aField.getText());
            double b = Double.parseDouble(bField.getText());
            String fFunction = formatFunction(fField.getText());
            String gFunction = formatFunction(gField.getText());
            double minX = Double.parseDouble(minXField.getText());
            double maxX = Double.parseDouble(maxXField.getText());

            if (minX >= maxX) {
                throw new IllegalArgumentException("min x must be less than max x.");
            }

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    List<Double> xValues = generateXValues(minX, maxX, 0.1);
                    List<Double> hValues = calculateHValues(xValues, a, b, fFunction, gFunction);

                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    series.setName("a ∙ f(x) - b ∙ g(x)");
                    for (int i = 0; i < xValues.size(); i++) {
                        final int index = i;
                        Platform.runLater(() -> series.getData().add(new XYChart.Data<>(xValues.get(index), hValues.get(index))));
                    }

                    Platform.runLater(() -> {
                        lineChart.getData().clear();
                        lineChart.setCreateSymbols(false);
                        lineChart.getData().add(series);
                        progressIndicator.setVisible(false);
                    });

                    return null;
                }

                @Override
                protected void failed() {
                    Throwable exception = getException();
                    Platform.runLater(() -> {
                        showAlert("Execution Error", "An error occurred while calculating the function.");
                        exception.printStackTrace();
                        progressIndicator.setVisible(false);
                    });
                }
            };

            progressIndicator.setVisible(true);
            new Thread(task).start();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for a, b, min x, and max x.");
        } catch (IllegalArgumentException e) {
            showAlert("Function Error", e.getMessage());
        } catch (Exception e) {
            showAlert("Execution Error", "An error occurred while calculating the function.");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        aField.clear();
        bField.clear();
        fField.clear();
        gField.clear();
        minXField.clear();
        maxXField.clear();
        lineChart.getData().clear();
        progressIndicator.setVisible(false);
    }

    private void saveGraph(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Graph");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            WritableImage image = lineChart.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                showAlert("Save Error", "An error occurred while saving the graph.");
                e.printStackTrace();
            }
        }
    }

    public List<Double> generateXValues(double start, double end, double step) {
        List<Double> xValues = new ArrayList<>();
        for (double x = start; x <= end; x += step) {
            xValues.add(x);
        }
        return xValues;
    }

    public List<Double> calculateHValues(List<Double> xValues, double a, double b, String fFunction, String gFunction) throws Exception {
        List<Double> hValues = new ArrayList<>();

        Class<?> fClass = compileFunction("edu.lab05_ind00", "F", fFunction);
        Class<?> gClass = compileFunction("edu.lab05_ind00", "G", gFunction);

        Method fMethod = fClass.getMethod("apply", double.class);
        Method gMethod = gClass.getMethod("apply", double.class);

        for (double x : xValues) {
            double fValue = (double) fMethod.invoke(null, x);
            double gValue = (double) gMethod.invoke(null, x);
            hValues.add(a * fValue - b * gValue);
        }

        return hValues;
    }

    public Class<?> compileFunction(String packageName, String className, String functionBody) throws Exception {
        String sourceCode = "package " + packageName + ";\n\npublic class " + className + " { public static double apply(double x) { return " + functionBody + "; } }";
        System.out.println("Source code: " + sourceCode);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Java Compiler is not available. Ensure you are using a JDK and not a JRE.");
        }

        String outputDirectory = "./ind00/target/classes/generated";
        List<String> options = new ArrayList<>();
        options.addAll(Arrays.asList("-d", outputDirectory));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        JavaSourceFromString javaSource = new JavaSourceFromString(packageName + "." + className, sourceCode);

        boolean success = compiler.getTask(null, null, diagnostics, options, null, Collections.singletonList(javaSource)).call();

        if (!success) {
            StringBuilder errorMsg = new StringBuilder();
            diagnostics.getDiagnostics().forEach(diagnostic -> errorMsg.append(diagnostic.getMessage(null)).append("\n"));
            throw new IllegalArgumentException("Compilation failed: \n" + errorMsg.toString());
        }

        @SuppressWarnings("resource")
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(outputDirectory).toURI().toURL()});
        return classLoader.loadClass(packageName + "." + className);
    }

    public String formatFunction(String function) {
        function = function.replaceAll("\\s", "");
        function = function.replaceAll("(?<!Math\\.)(sin|cos|tan|log|sqrt|abs|pow|exp|asin|acos|atan)", "Math.$1");
        function = function.replaceAll("(\\d+|x|e|PI)\\^(\\d+|x|e|PI)", "Math.pow($1,$2)");
        int openParenCount = function.length() - function.replace("(", "").length();
        int closeParenCount = function.length() - function.replace(")", "").length();
        if (openParenCount > closeParenCount) {
            for (int i = 0; i < openParenCount - closeParenCount; i++) {
                function += ")";
            }
        }
        return function;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
