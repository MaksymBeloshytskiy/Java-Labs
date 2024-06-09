module edu.lab05_t00 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lab05_t00 to javafx.fxml;
    exports edu.lab05_t00;
}
