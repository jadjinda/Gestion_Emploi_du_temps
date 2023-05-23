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

public class CourController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int id=0;
    public TextField entry,entry1,entry2,entry3;
    public TableView<Cour> tableauCour;
    @FXML
    private TableColumn<Cour, String> matiere;
    @FXML
    private TableColumn<Cour, String> anneeScolaire;
    @FXML
    private TableColumn<Cour, String> enseignant;
    @FXML
    private TableColumn<Cour, String> classe;
    public Button supprimerCour,ajouterCour;
    public Button Acceuil;
    public Button Classe;
    public Button Enseignant;
    public Button Annee;
    public Button Cour;
    public Button Matiere;
    public Button EmploiDuTemps;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCour();
    }
    public ObservableList<Cour> getCour(){
        ObservableList<Cour> course = FXCollections.observableArrayList();

        String query = "SELECT * FROM cour";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Cour a = new Cour();
                a.setId(rs.getInt("id"));
                a.setMatiere(rs.getString("matiere"));
                a.setAnneeScolaire(rs.getString("anneeScolaire"));
                a.setEnseignant(rs.getString("enseignant"));
                a.setClasse(rs.getString("classe"));
                course.add(a);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return course;
    }
    public void showCour(){
        ObservableList<Cour>list= getCour();
        tableauCour.setItems(list);
        matiere.setCellValueFactory(new PropertyValueFactory<Cour,String>("matiere"));
        anneeScolaire.setCellValueFactory(new PropertyValueFactory<Cour,String>("anneeScolaire"));
        enseignant.setCellValueFactory(new PropertyValueFactory<Cour,String>("enseignant"));
        classe.setCellValueFactory(new PropertyValueFactory<Cour,String>("classe"));
    }
    @FXML
    void createField(ActionEvent event){
        String insert = "INSERT INTO cour(matiere,anneeScolaire,enseignant,classe) VALUES(?,?,?,?)";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,entry.getText());
            st.setString(2,entry1.getText());
            st.setString(3,entry2.getText());
            st.setString(4,entry3.getText());
            st.executeUpdate();
            showCour();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        Cour course = tableauCour.getSelectionModel().getSelectedItem();
        id = course.getId();
        matiere.setText(course.getMatiere());
        anneeScolaire.setText(course.getAnneeScolaire());
        enseignant.setText(course.getEnseignant());
        classe.setText(course.getClasse());
        ajouterCour.setDisable(true);
    }
    @FXML
    void deleteField(ActionEvent event){
        String delete = "DELETE FROM cour WHERE id=?";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showCour();
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
