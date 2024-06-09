module edu.lab05_ind00 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires java.desktop;
    requires javafx.swing;

    opens edu.lab05_ind00 to javafx.fxml;
    exports edu.lab05_ind00;
}
