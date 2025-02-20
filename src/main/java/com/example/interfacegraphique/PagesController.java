package com.example.interfacegraphique;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PagesController implements Initializable{
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    //Creation Annee
    int id=0;
    public TextField retour,date,date1;
    public TableView<Annee> tableauAnnee;
    @FXML
    private TableColumn<Annee, String> code;
    @FXML
    private TableColumn<Annee, String> dateDebut;
    @FXML
    private TableColumn<Annee, String> dateFin;

    public Button supprimerAnnee,ajouterAnnee;
    public Button Acceuil;
    public Button Classe;
    public Button Enseignant;
    public Button Annee;
    public Button Cour;
    public Button Matiere;
    public Button EmploiDuTemps;
    public ComboBox enseignant;
    public ComboBox classe;
    public ComboBox matiere;
    public Button logout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAnnee();
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
        Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Acceuil");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void matiere() throws IOException{
        Stage stage = (Stage) Matiere.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationMatiere.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CREATION MATIERE");
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

    //CRUD POUR LES PAGES
    //1- crud pour annee
    public ObservableList<Annee> getAnnee(){
        ObservableList<Annee> year = FXCollections.observableArrayList();

        String query = "SELECT * FROM annee";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Annee a = new Annee();
                a.setId(rs.getInt("id"));
                a.setCode(rs.getString("code"));
                a.setDateDebut(rs.getString("dateDebut"));
                a.setDateFin(rs.getString("dateFin"));
                year.add(a);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return year;
    }
    public void showAnnee(){
        ObservableList<Annee>list= getAnnee();
        tableauAnnee.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<Annee,String>("code"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<Annee,String>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<Annee,String>("dateFin"));
    }
    @FXML
    void createField(ActionEvent event){
        String insert = "INSERT INTO annee(code,dateDebut,dateFin) VALUES(?,?,?)";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,retour.getText());
            st.setString(2,date.getText());
            st.setString(3,date1.getText());
            st.executeUpdate();
            showAnnee();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        Annee year = tableauAnnee.getSelectionModel().getSelectedItem();
        id = year.getId();
        code.setText(year.getCode());
        dateDebut.setText(year.getDateDebut());
        dateFin.setText(year.getDateFin());
        ajouterAnnee.setDisable(true);
    }
    @FXML
    void deleteField(ActionEvent event){
        String delete = "DELETE FROM annee WHERE id=?";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showAnnee();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
