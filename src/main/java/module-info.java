module com.example.port {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    requires com.dlsc.formsfx;


    opens com.example.port to javafx.fxml;
    opens com.example.port.View to javafx.fxml;
    exports com.example.port;
    exports com.example.port.Controler;
    exports com.example.port.Model;
    exports com.example.port.View;
}