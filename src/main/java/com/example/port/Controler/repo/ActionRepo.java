package com.example.port.Controler.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActionRepo {
    private String url;
    private String user;
    private String password;

    public ActionRepo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    public ResultSet chargerActionsPourPortefeuille(long portefeuilleId) {
        ResultSet resultSet = null;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM public.actions left join actionall on actions.actions = actionall.actions_id WHERE portefeuille_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, portefeuilleId);

            resultSet = preparedStatement.executeQuery();

            // Ne fermez pas la connexion ici, car vous voudrez probablement l'utiliser à l'extérieur de cette méthode.
        } catch (Exception e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins
        }
        return resultSet;
    }

}
