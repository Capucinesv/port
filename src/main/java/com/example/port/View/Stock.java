package com.example.port.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

public class Stock {
    String url;
    String user;
    String password;

    public Stock(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Stock() {
        this.url = "jdbc:postgresql://localhost:5432/postgres";
        this.user = "postgres";
        this.password = "Capucine33?";
    }

    @FXML
    private ComboBox<String> companyComboBox;

    @FXML
    private LineChart<Number, Number> closeChart;

    @FXML
    private LineChart<Number, Number> volumeChart;


    @FXML
    private DatePicker datePicker;

    private Stage stage;


    ObservableList<String> companies = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Charger les symboles d'entreprise depuis la base de données
        loadCompanies();

        companyComboBox.setItems(companies);
        companyComboBox.setPromptText("Sélectionnez une société");

        // Graphique des valeurs de clôture
        initializeChart(closeChart, "Valeurs de clôture", "Date", "Valeur de clôture");

        // Graphique du volume
        initializeChart(volumeChart, "Volume", "Date", "Volume");

        // Gestionnaire d'événements pour mettre à jour les graphiques lorsque la société est sélectionnée
        companyComboBox.setOnAction(event -> updateCharts());
    }

    private void initializeChart(LineChart<Number, Number> chart, String title, String xLabel, String yLabel) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);

        chart.setTitle(title);
        chart.setCreateSymbols(false);

        chart.setAnimated(false);

        chart.setLegendVisible(true);

        chart.setAnimated(true);

        chart.getData().clear();

        chart.getXAxis().setAutoRanging(true);

        chart.getXAxis().setAutoRanging(true);



        chart.getXAxis().setTickLabelsVisible(true);

        chart.getYAxis().setAutoRanging(true);

        chart.getYAxis().setAutoRanging(true);



        chart.getYAxis().setTickLabelsVisible(true);
    }

    void loadCompanies() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ticker_symbol FROM public.company");
            while (resultSet.next()) {
                companies.add(resultSet.getString("ticker_symbol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCharts() {
        String selectedCompany = companyComboBox.getValue();

        // Charger les données depuis la base de données pour la société sélectionnée
        List<Double> closeValues = new ArrayList<>();
        List<Integer> volumes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT day_date, close_value, volume FROM public.companyvalues WHERE ticker_symbol = ? and day_date between ? and ? order by day_date asc")) {
            preparedStatement.setString(1, selectedCompany);
            preparedStatement.setString(2, String.valueOf(LocalDate.now().minusYears(5).minusDays(500)));
            preparedStatement.setString(3, String.valueOf(LocalDate.now().minusYears(5)));
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                closeValues.add(resultSet.getDouble("close_value"));
                volumes.add(resultSet.getInt("volume"));
            }
            System.out.println(closeValues.get(closeValues.size()-1).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("salut");
        // Mettre à jour les séries de données des graphiques
        updateSeries(closeChart, "Valeur de clôture", closeValues);
        updateSeries(volumeChart, "Volume", volumes);
    }

    private void updateSeries(LineChart<Number, Number> chart, String seriesName, List<? extends Number> values) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        System.out.println(values.size());
        System.out.println("wsh");
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, values.get(i)));
            System.out.println("yo");
        }

        chart.getData().clear();
        chart.getData().add(series);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}