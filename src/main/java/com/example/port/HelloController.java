package com.example.port;



import com.example.port.Controler.UserControler;
import com.example.port.View.LoginForm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    private LoginForm loginForm;

    private UserControler userControler;

    // Ajoutez un constructeur par défaut
    public HelloController() {
    }
    @FXML
    protected void onHelloButtonClick() throws IOException {
        System.out.println("oui");
        // Charger le formulaire de connexion
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Parent loginRoot = loginLoader.load();
        loginForm = loginLoader.getController();

        // Créer une nouvelle scène pour le formulaire de connexion
        Scene loginScene = new Scene(loginRoot);
        // Obtenir la scène actuelle
        Stage currentStage = (Stage) welcomeText.getScene().getWindow();
        // Définir la nouvelle scène
        currentStage.setScene(loginScene);


    }
}