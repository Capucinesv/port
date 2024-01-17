package com.example.port.Controler.repo;

import com.example.port.Model.PorteFeuille;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PorteFeuilleRepo {

    private String url;
    private String user;
    private String password;

    public PorteFeuilleRepo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    public PorteFeuille chargerListePortefeuilles(Long id) {
        PorteFeuille porteFeuille=null;
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            String sql = "SELECT * FROM public.portefeuille WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Math.toIntExact(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            porteFeuille = mapResultSetToPortefeuilles(resultSet);


            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return porteFeuille;
    }
    public static PorteFeuille mapResultSetToPortefeuilles(ResultSet resultSet) {
        PorteFeuille portefeuilles = null;

        try {
            while (resultSet.next()) {
                Long portefeuilleId = resultSet.getLong("portefeuille_id");
                int argent = resultSet.getInt("argent");
                long userId = resultSet.getLong("user_id");

                PorteFeuille portefeuille = new PorteFeuille(portefeuilleId, argent, userId);
                portefeuilles =portefeuille;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        System.out.println(portefeuilles);

        return portefeuilles;
    }
}