module com.application.movieticketbookingmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.movieticketbookingmanagementapp to javafx.fxml;
    exports com.application.movieticketbookingmanagementapp;
}