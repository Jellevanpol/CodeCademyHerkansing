module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;

    opens com.example.Presentation to javafx.fxml;
    opens com.example.Domain to javafx.base;

    exports com.example.Presentation;
}
