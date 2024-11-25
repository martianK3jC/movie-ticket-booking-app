module com.application.loginapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.application.loginapp to javafx.fxml;
    exports com.application.loginapp;
}