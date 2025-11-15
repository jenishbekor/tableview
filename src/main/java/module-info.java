module com.example.demoproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demoproject to javafx.fxml;
    opens com.example.demoproject.model to javafx.base;
    exports com.example.demoproject;
}