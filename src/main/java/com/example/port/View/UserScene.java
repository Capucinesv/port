package com.example.port.View;

import com.example.port.Controler.PorteFeuilleControler;
import com.example.port.Controler.repo.ActionRepo;
import com.example.port.Controler.repo.PorteFeuilleRepo;
import com.example.port.HelloApplication;
import com.example.port.Model.PorteFeuille;
import com.example.port.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserScene extends Parent {
    public TextField amountField;
    Screen screen = Screen.getPrimary();
    double screenWidth = screen.getBounds().getWidth();
    double screenHeight = screen.getBounds().getHeight();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private HBox mainHBox;

    @FXML
    private VBox leftVBox;

    @FXML
    private Pane leftPane;

    @FXML
    private Label leftLabel;

    @FXML
    private TextArea leftListView;

    @FXML
    private VBox rightVBox;

    @FXML
    private Pane rightPane;

    @FXML
    private HBox rightHBox;

    @FXML
    private TextArea rightListView;
    @FXML
    private Label Name;

    @FXML
    private Label id;

    @FXML
    private Label age;

    @FXML
    private Label date_crea;

    @FXML
    private Label Argent;

    @FXML
    private Label nbrActions;

    @FXML
    private Label total;

    @FXML
    private TextField yourTextField1; // Remplacez "yourTextField1" par le nom réel de votre champ

    @FXML
    private TextField yourTextField2;

    @FXML
    private ComboBox<String> companyComboBox;

    @FXML
    private PieChart pieChart;

    private ObservableList<String> companies = FXCollections.observableArrayList();

    private List<Double> stockVals = new ArrayList<>();
    private User user;
    ActionRepo actionRepo= new ActionRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "Capucine33?");
    PorteFeuilleRepo porteFeuilleRepo= new PorteFeuilleRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "Capucine33?");
    PorteFeuilleControler porteFeuilleControler = new PorteFeuilleControler(this.porteFeuilleRepo);

    StringBuilder stocksBuilder;
    StringBuilder history = new StringBuilder();


    @FXML
    public void initialize(User users) throws SQLException {

        Stock s = new Stock();

        s.loadCompanies();

        companies = s.companies;

        companyComboBox.setItems(companies);
        companyComboBox.setPromptText("Sélectionnez une société");

        updateStockVals();

        stocksBuilder = buildStocks();

        leftListView.setText(stocksBuilder.toString());
        rightListView.setText(history.toString());

        this.user=users;
        // Configurez les éléments ici, si nécessaire
        configureLayout();
        setUserData(user);

        this.pieChart.getData().clear();
        Map<String, Double> map = HelloApplication.currentPF.getActionsMap();
        for(Map.Entry<String, Double> entry : map.entrySet()) {
            this.pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
    }

    private void updateStockVals(){
        Stock s = new Stock();
        stockVals.clear();
        for(String companyName : companies){
            try (Connection connection = DriverManager.getConnection(s.url, s.user, s.password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "SELECT close_value FROM public.companyvalues WHERE ticker_symbol = ? and day_date = ?")) {
                preparedStatement.setString(1, companyName);
                preparedStatement.setString(2, String.valueOf(LocalDate.now().minusYears(5)));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    stockVals.add(resultSet.getDouble("close_value"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private StringBuilder buildStocks(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < companies.size(); ++i){
            builder.append(companies.get(i)).
                    append(":").append(stockVals.get(i)).append("\n");
        }
        return builder;
    }

    private void configureLayout() {

        // Configurer la largeur initiale des panneaux gauche et droit
        leftPane.setPrefWidth(rootPane.getPrefWidth() * 0.3);
        leftPane.setPrefHeight(rootPane.getPrefHeight());
        rightPane.setPrefWidth(rootPane.getPrefWidth() * 0.7);
        rightPane.setPrefHeight(rootPane.getPrefHeight());
        leftVBox.setPrefWidth(leftPane.getPrefWidth());
        leftVBox.setPrefHeight(leftPane.getPrefHeight() * 0.5);
        rightVBox.setPrefWidth(leftPane.getPrefWidth());
        rightVBox.setPrefHeight(leftPane.getPrefHeight() * 0.5);
        // Écouter les changements de largeur de la racine et ajuster en conséquence
        rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double leftWidth = newWidth.doubleValue() * 0.4;
            double rightWidth = newWidth.doubleValue() * 0.6;

            leftPane.setPrefWidth(leftWidth);
            rightPane.setPrefWidth(rightWidth);
        });

        // Configurer la hauteur initiale du HBox principal
        mainHBox.setPrefWidth(rootPane.getPrefWidth());
        mainHBox.setPrefHeight(rootPane.getPrefHeight());

        // Écouter les changements de hauteur de la racine et ajuster en conséquence
        rootPane.heightProperty().addListener((obs, oldHeight, newHeight) ->
                mainHBox.setPrefHeight(newHeight.doubleValue()));

    }
    public void setUserData(User user) throws SQLException {
        // Mettre à jour les étiquettes avec les données de l'utilisateur
        Name.setText("Nom: " + user.getNames() + " " + user.getLastname());
        id.setText("ID: " + user.getUser_id().toString());
        age.setText("Âge: " + Integer.toString(user.getAge()));
        date_crea.setText("Date de création: " + user.getDate_crea().toString());
        PorteFeuille porteFeuille = HelloApplication.currentPF;
        HelloApplication.currentPF.setUser_id(user.getUser_id());// update the real user id
        Argent.setText(String.valueOf(porteFeuille.getArgent()));
        // Ajoutez le reste des étiquettes selon vos besoins
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    @FXML
    private void onBuySellClicked(ActionEvent actionEvent) throws SQLException {
        String company = this.companyComboBox.getValue();
        Double amount = Double.parseDouble(this.amountField.getText());
        Integer index = companies.indexOf(company);
        Double stockValue = stockVals.get(index);

        HelloApplication.currentPF.buyOrSellAction(company, stockValue, amount);
        this.history.append(company +" "+ stockValue+" "+ amount + " total:" + stockValue * amount + "\n");
        this.initialize(user);
    }
}