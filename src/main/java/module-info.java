module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.example.Presentation to javafx.fxml;

    exports com.example.Presentation;
}
