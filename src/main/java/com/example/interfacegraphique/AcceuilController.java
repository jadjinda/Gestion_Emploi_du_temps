package com.example.interfacegraphique;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {
    @FXML
    private TableView<Tab> table;

    @FXML
    private TableColumn<Tab, String> enseignant;

    @FXML
    private TableColumn<Tab, String> matiere;

    @FXML
    private TableColumn<Tab, String> heure;
    public Button Acceuil;

    public Button Classe;
    public Button Enseignant;
    public Button Annee;
    public Button Cour;
    public Button Matiere;
    public Button EmploiDuTemps;
    public Button message1;
    public Button message2;
    public Button message3;
    public Button message4;
    public Button message5;
    public Button message6;
    public Button logout;

    ObservableList<Tab> list = FXCollections.observableArrayList(
            new Tab("BATANA Alou Pougnozi", "Structure de données avec Java ||","07H30 à 09H30"),
            new Tab("AYENA Adébayor", "Algorithmes & Programmation Structurée avec Python |","15H00 à 17H00")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enseignant.setCellValueFactory(new PropertyValueFactory<Tab, String>("enseignant"));
        matiere.setCellValueFactory(new PropertyValueFactory<Tab, String>("matiere"));
        heure.setCellValueFactory(new PropertyValueFactory<Tab, String>("heure"));
        table.setItems(list);
    }
    public void message() throws IOException {
        Alert ab = new Alert(Alert.AlertType.INFORMATION);
        ab.setContentText("Vous n'êtes pas autorisé à accéder à cet menu\n" +
                "Veuillez voir l'administrateur pour y accéder....");
        ab.show();
    }
    public void logout() throws IOException{
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }
    public void acceuil() throws IOException{
        Stage stage = (Stage) Acceuil.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationClasse.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Acceuil");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void classe() throws IOException {
        Stage stage = (Stage) Classe.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationClasse.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION DE CLASSE");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void enseignant() throws IOException {
        Stage stage = (Stage) Enseignant.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationEnseignant.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION D'ENSEIGNANT'");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void annee() throws IOException {
        Stage stage = (Stage) Annee.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationAnnee.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION D'ANNEE");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void matiere() throws IOException {
        Stage stage = (Stage) Matiere.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationMatiere.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION D'ANNEE");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void emploiDuTemps() throws IOException{
        Stage stage = (Stage) EmploiDuTemps.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationEmploiDuTemps.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION DE L'EMPLOI DU TEMPS");
        primaryStage.show();
    }
    public void cour() throws IOException{
        Stage stage = (Stage) Cour.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationCour.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION DE COUR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}