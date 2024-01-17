package com.example.port.Controler.repo;

import com.example.port.Controler.UserControler;
import com.example.port.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserRepo {

    private String url;
    private String user;
    private String password;

    public UserRepo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public static Connection connectToDatabase(String url, String user, String password) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

        return connection;
    }

    public void create(User users) throws SQLException {


        String sqlQuery = "INSERT INTO users (age,lastname,names,passwords ) VALUES (?, ? , ? , ?)";
        Connection connection = connectToDatabase(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, users.getAge());
        preparedStatement.setString(2, users.getLastname());
        preparedStatement.setString(3, users.getNames());
        preparedStatement.setString(4, users.getPasswords());

        int lignesAffectees = preparedStatement.executeUpdate();

        if (lignesAffectees > 0) {
            System.out.println("Nouvel utilisateur inséré avec succès.");
        } else {
            System.out.println("Échec de l'insertion de l'utilisateur.");
        }

    }
    public User FindByName(String Name) {


        String sqlQuery = "SELECT * from users where names = ?";
        Connection connection = connectToDatabase(url, user, password);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, Name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("User Found");
                    return mapResultSetToUser(resultSet);
                }
                System.out.println("User Not Found");
                return new User(null,0,null,null,null,null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getLong("user_id"),resultSet.getInt("age"),resultSet.getObject("date_crea", LocalDate.class), resultSet.getString("lastname"), resultSet.getString("names"), resultSet.getString("passwords"));
        return user;
    }
}