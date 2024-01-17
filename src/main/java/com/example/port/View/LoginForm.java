package com.example.port.View;
import com.example.port.Controler.UserControler;
import com.example.port.Controler.repo.UserRepo;
import com.example.port.HelloApplication;
import com.example.port.Model.PorteFeuille;
import com.example.port.Model.Session;
import com.example.port.Model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginForm{

    @FXML
    private TextField nameInput;

    @FXML
    private PasswordField passwordInput;
    private Stage current ;


    private final UserControler userControler= new UserControler();
    private Main_Menue mainMenue;


    public void handleLogin() throws IOException {
        // Votre logique de connexion ici
        System.out.println("Login Button Clicked");
        Session b = this.userControler.Login(nameInput.getText(),passwordInput.getText());
        if(b.getState()){
            current = (Stage) nameInput.getScene().getWindow();
            current.close();
            Stage newStage = new Stage();
            Screen screen = Screen.getPrimary();
            //double screenWidth = screen.getBounds().getWidth();
            //double screenHeight = screen.getBounds().getHeight();
            //newStage.setWidth(screenWidth);
            //newStage.setHeight(screenHeight);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Numeric Amount");
            int moneyAmount;
            do {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter an amount of money you want to simulate for the PF");
                dialog.setHeaderText(null);
                dialog.setContentText("please enter a positive int");

                Optional<String> result = dialog.showAndWait();

                try {
                    moneyAmount = Integer.parseInt(result.orElse(""));
                    if (moneyAmount == 0) moneyAmount = -1;

                    System.out.println(moneyAmount);
                } catch (Exception e){
                    moneyAmount = -1;
                }
            } while (moneyAmount < 0);

            HelloApplication.currentPF = new PorteFeuille(1000L, moneyAmount,-1);
            // take a preset user id

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/port/MainMenue.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newStage.setTitle("User Scene");
            newStage.setScene(scene);
            newStage.show();
            mainMenue = fxmlLoader.getController();
            mainMenue.loadFXML("/com/example/port/UserScene.fxml",b.getUser());



        }

    }

    public void showRegisterForm() throws IOException {
        // Votre logique pour afficher le formulaire d'inscription ici
        System.out.println("Register Button Clicked");
        // Charger le formulaire de connexion
        FXMLLoader regisLoader = new FXMLLoader(getClass().getResource("/com/example/port/RegisterFrom.fxml"));
        Parent regisRoot = regisLoader.load();
        RegisterForm registerForm = regisLoader.getController();

        // Créer une nouvelle scène pour le formulaire de connexion
        Scene RegisterScene = new Scene(regisRoot);

        // Obtenir la scène actuelle
        current=(Stage) nameInput.getScene().getWindow();
        // Définir la nouvelle scène
        current.setScene(RegisterScene);
    }
}