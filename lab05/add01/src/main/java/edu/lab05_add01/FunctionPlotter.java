package edu.lab05_add01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FunctionPlotter extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Завантаження HTML-сторінки, яка відображає графік
        webEngine.load(getClass().getResource("plot.html").toExternalForm());

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Function Plotter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
