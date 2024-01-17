package com.example.port.View;

import com.example.port.Controler.UserControler;
import com.example.port.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterForm {
    @FXML
    private TextField nameInput;

    @FXML
    private PasswordField passwordInput;
    @FXML
    private TextField ageInput;

    @FXML
    private TextField LastnameInput;

    private final UserControler userControler= new UserControler();
    public void handleRegister() throws SQLException {
        if (nameInput.getText().equals(null) || passwordInput.getText().equals(null) || ageInput.getText().equals(null) || LastnameInput.getText().equals(null)){
            System.out.println("oui");
            return;
        }
        User user = new User(0L,Integer.parseInt(ageInput.getText()), LocalDate.now(),LastnameInput.getText(),nameInput.getText(),passwordInput.getText());
        userControler.Register(user);
        // Votre logique d'inscription ici
        System.out.println("Register Button Clicked");
    }
}