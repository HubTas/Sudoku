module com.example.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;
    requires org.apache.commons.lang3;
    requires java.logging;

    opens com.example.view to javafx.fxml;
    exports com.example.view;
}