package com.example.interfacegraphique;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class MatiereController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    public Button supprimer,ajouter;
    public TextField recup,recup1;
    public TableView<Matiere> tableau;
    @FXML
    private TableColumn<Matiere, String> intitule;

    @FXML
    private TableColumn<Matiere, String> code;
    int id=0;
    public Button Acceuil;
    public Button Annee;
    public Button Cour;
    public Button EmploiDuTemps;
    public Button Matiere;
    public Button Enseignant,Classe;
    @Override
    public void initialize(URL location, ResourceBundle ressourceBundle){
        showMatiere();
    }
    public ObservableList<Matiere> getMatiere(){
        ObservableList<Matiere> a = FXCollections.observableArrayList();

        String query = "SELECT * FROM matiere";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Matiere mat = new Matiere();
                mat.setId(rs.getInt("id"));
                mat.setIntitule(rs.getString("intitule"));
                mat.setCode(rs.getString("code"));
                a.add(mat);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return a;
    }
    public void showMatiere(){
        ObservableList<Matiere>liste= getMatiere();
        tableau.setItems(liste);
        intitule.setCellValueFactory(new PropertyValueFactory<Matiere,String>("intitule"));
        code.setCellValueFactory(new PropertyValueFactory<Matiere,String>("code"));
    }

    @FXML
    void clearField(ActionEvent event){
        clear();
    }
    @FXML
    void createField(ActionEvent event){
        String insert = "INSERT INTO matiere(intitule,code) VALUES(?,?)";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,recup.getText());
            st.setString(2,recup1.getText());
            st.executeUpdate();
            showMatiere();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        Matiere mat = tableau.getSelectionModel().getSelectedItem();
        id = mat.getId();
        intitule.setText(mat.getIntitule());
        code.setText(mat.getCode());
        ajouter.setDisable(true);
    }
    void clear(){
        intitule.setText(null);
        code.setText(null);
        ajouter.setDisable(true);
    }
    @FXML
    void deleteField(ActionEvent event){
        String delete = "DELETE FROM matiere WHERE id=?";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showMatiere();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void acceuil() throws IOException {
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
    public void matiere() throws IOException{
        Stage stage = (Stage) Matiere.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("creationMatiere.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Acceuil");
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
}
