module com.application.movieticketmanagementsystemapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.movieticketmanagementsystemapp to javafx.fxml;
    exports com.application.movieticketmanagementsystemapp;
}