package com.example.port;



import com.example.port.Controler.UserControler;
import com.example.port.Controler.repo.UserRepo;
import com.example.port.Model.PorteFeuille;
import com.example.port.Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {

    public static PorteFeuille currentPF;
    private final UserControler userControler;
    public HelloApplication() {
        // Initialisez le userRepo ici en utilisant les informations de connexion
        UserRepo userRepo = new UserRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "Capucine33?");

        // Initialisez le userControler avec le userRepo
        this.userControler = new UserControler();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        System.out.println(LocalDate.now());
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "Capucine33?";

        // JDBC variables for opening, closing, and managing connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        // Créer une nouvelle fenêtre (stage) avec la taille de l'écran
        Stage newStage = new Stage();
        newStage.setWidth(screenWidth);
        newStage.setHeight(screenHeight);


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        newStage.setTitle("Hello!");
        newStage.setScene(scene);
        newStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}