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

public class EnseignantController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int id=0;
    public TextField entry,entry1,entry2,entry3,entry4,search;
    public TableView<Enseignant> tableauEnseignant;
    @FXML
    private TableColumn<Enseignant, String> nom;
    @FXML
    private TableColumn<Enseignant, String> prenom;
    @FXML
    private TableColumn<Enseignant, String> tel;
    @FXML
    private TableColumn<Enseignant, String> numeroMatricule;
    @FXML
    private TableColumn<Enseignant, String> email;
    public Button supprimerEnseignant,ajouterEnseignant,searchButton;
    public Button Acceuil;
    public Button Classe;
    public Button Enseignant;
    public Button Annee;
    public Button Cour;
    public Button Matiere;
    public Button EmploiDuTemps;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEnseignant();
    }
    public ObservableList<Enseignant> getEnseignant(){
        ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();

        String query = "SELECT * FROM enseignant";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Enseignant a = new Enseignant();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setTel(rs.getString("tel"));
                a.setNumeroMatricule(rs.getString("numMat"));
                a.setEmail(rs.getString("email"));
                enseignants.add(a);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return enseignants;
    }
    public void showEnseignant(){
        ObservableList<Enseignant>list= getEnseignant();
        tableauEnseignant.setItems(list);
        nom.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("tel"));
        numeroMatricule.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("numeroMatricule"));
        email.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("email"));
    }
    @FXML
    void createField(ActionEvent event){
        String insert = "INSERT INTO enseignant(nom,prenom,tel,numMat,email) VALUES(?,?,?,?,?)";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,entry.getText());
            st.setString(2,entry1.getText());
            st.setString(3,entry2.getText());
            st.setString(4,entry3.getText());
            st.setString(5,entry4.getText());
            st.executeUpdate();
            showEnseignant();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void searchField(ActionEvent event){
        ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();
        String input = "SELECT nom,prenom,tel FROM enseignant where id=?";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(input);
            st.setString(1,search.getText());
            rs = st.executeQuery();
            while (rs.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setNom(rs.getString("nom"));
                enseignant.setPrenom(rs.getString("prenom"));
                enseignant.setTel(rs.getString("tel"));
                enseignants.add(enseignant);
                tableauEnseignant.setItems(enseignants);
            }
            System.out.println("hello");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        Enseignant course = tableauEnseignant.getSelectionModel().getSelectedItem();
        id = course.getId();
        nom.setText(course.getNom());
        prenom.setText(course.getPrenom());
        tel.setText(course.getTel());
        numeroMatricule.setText(course.getNumeroMatricule());
        email.setText(course.getEmail());
        ajouterEnseignant.setDisable(true);
    }
    @FXML
    void deleteField(ActionEvent event){
        String delete = "DELETE FROM enseignant WHERE id=?";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showEnseignant();
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
}
