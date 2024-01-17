package com.example.port.View;

import com.example.port.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.MenuBar;
import javafx.stage.Screen;

import java.io.IOException;
import java.sql.SQLException;

public class Main_Menue {
    @FXML
    private SubScene subScene;
    private User user;

    Screen screen = Screen.getPrimary();
    double screenWidth = screen.getBounds().getWidth();
    double screenHeight = screen.getBounds().getHeight();
    private UserScene userScene;
    public Main_Menue() {

        // Constructeur par défaut sans paramètres
    }

    @FXML
    private void initialize() {
        // Charger les symboles d'entreprise depuis la base de données

    }
    public Main_Menue(String fxmlFileName) {
        try {
            System.out.println("hello");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            loader.setController(this);  // Ajoutez cette ligne pour lier le contrôleur
            Parent root = loader.load();


        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins
        }
    }

    @FXML
    public void onUserMenuItemClick() {
        loadFXML("/com/example/port/UserScene.fxml",this.user);
    }

    @FXML
    public void onmarcheMenuItemClick() {
        try {
            System.out.println("oui");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/port/Chart.fxml"));
            Parent root = loader.load();
            subScene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("oui");

    }

    @FXML
    public void onplusMenuItemClick() {
        System.out.println("oui");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/port/Chart.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            System.out.println("oui");
            subScene.setRoot(root);
            userScene= loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadFXML(String fxmlFileName, User users) {
        try {


            // Remplacez le contenu actuel de la SubScene

            if(fxmlFileName == "/com/example/port/UserScene.fxml"){
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
                Parent root = loader.load();
                this.user =users;
                System.out.println("oui");
                subScene.setRoot(root);
                userScene= loader.getController();
                userScene.initialize(users);


            }
            // Mettez à jour la taille de la SubScene en fonction de la taille du contenu chargé

        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}