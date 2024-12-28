module com.example.macdanyapp {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;
    requires java.desktop;

    opens com.example.macdanyapp.controllers to javafx.fxml, javafx.graphics;

}
