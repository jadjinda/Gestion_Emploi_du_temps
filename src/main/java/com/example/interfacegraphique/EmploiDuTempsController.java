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

public class EmploiDuTempsController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int id=0;
    public TextField entry,entry1,entry2,entry3,entry4;
    public TableView<EmploiDuTemps> tableauEmploiDuTemps;
    @FXML
    private TableColumn<EmploiDuTemps, String> enseignant;
    @FXML
    private TableColumn<EmploiDuTemps, String> cour;
    @FXML
    private TableColumn<EmploiDuTemps, String> heureDebut;
    @FXML
    private TableColumn<EmploiDuTemps, String> heureFin;
    @FXML
    private TableColumn<EmploiDuTemps, String> classe;
    public Button supprimer,ajouter;
    public Button Acceuil;
    public Button Classe;
    public Button Enseignant;
    public Button Annee;
    public Button Cour;
    public Button Matiere;
    public Button EmploiDuTemps;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmploiDuTemps();
    }
    public ObservableList<EmploiDuTemps> getEmploiDuTemps(){
        ObservableList<EmploiDuTemps> emploi = FXCollections.observableArrayList();

        String query = "SELECT * FROM resumer";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                EmploiDuTemps a = new EmploiDuTemps();
                a.setId(rs.getInt("id"));
                a.setCours(rs.getString("cour"));
                a.setEnseignant(rs.getString("enseignant"));
                a.setClasse(rs.getString("classe"));
                a.setHeureDebut(rs.getString("heureDebut"));
                a.setHeureFin(rs.getString("heureFin"));
                emploi.add(a);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return emploi;
    }
    public void showEmploiDuTemps(){
        ObservableList<EmploiDuTemps>list= getEmploiDuTemps();
        tableauEmploiDuTemps.setItems(list);
        enseignant.setCellValueFactory(new PropertyValueFactory<EmploiDuTemps,String>("enseignant"));
        classe.setCellValueFactory(new PropertyValueFactory<EmploiDuTemps,String>("classe"));
        cour.setCellValueFactory(new PropertyValueFactory<EmploiDuTemps,String>("cours"));
        heureDebut.setCellValueFactory(new PropertyValueFactory<EmploiDuTemps,String>("heureDebut"));
        heureFin.setCellValueFactory(new PropertyValueFactory<EmploiDuTemps,String>("heureFin"));
    }
    @FXML
    void createField(ActionEvent event){
        String insert = "INSERT INTO resumer(enseignant,cour,classe,heureDebut,heureFin) VALUES(?,?,?,?,?)";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,entry.getText());
            st.setString(2,entry1.getText());
            st.setString(3,entry2.getText());
            st.setString(4,entry3.getText());
            st.setString(5,entry4.getText());
            st.executeUpdate();
            showEmploiDuTemps();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        EmploiDuTemps course = tableauEmploiDuTemps.getSelectionModel().getSelectedItem();
        id = course.getId();
        enseignant.setText(course.getEnseignant());
        cour.setText(course.getCours());
        classe.setText(course.getClasse());
        heureDebut.setText(course.getHeureDebut());
        heureFin.setText(course.getHeureFin());
        ajouter.setDisable(true);
    }
    @FXML
    void deleteField(ActionEvent event){
        String delete = "DELETE FROM resumer WHERE id=?";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showEmploiDuTemps();
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
