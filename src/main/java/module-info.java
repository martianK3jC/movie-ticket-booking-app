<<<<<<< HEAD
module com.application.loginapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.application.loginapp to javafx.fxml;
    exports com.application.loginapp;
=======
module com.application.movieticketbookingmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.movieticketbookingmanagementapp to javafx.fxml;
    exports com.application.movieticketbookingmanagementapp;
>>>>>>> bc566fe25301ebfadb10ad5f315f3ff0f6a9d011
}