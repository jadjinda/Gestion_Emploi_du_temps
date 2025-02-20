package com.example.interfacegraphique;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    public Button supprimer,ajouter;
    public TextField recup,recup1;
    public TableView<Classe> tableau;
    @FXML
    private TableColumn<Classe, String> intitule;

    @FXML
    private TableColumn<Classe, String> code;
    int id=0;
    public Button Acceuil;
    public Button Annee;
    public Button Cour;
    public Button EmploiDuTemps;
    public Button Matiere;
    public Button Enseignant;
    @Override
    public void initialize(URL location, ResourceBundle ressourceBundle){
        showClasse();
    }
    public ObservableList<Classe> getClasse(){
        ObservableList<Classe> classe = FXCollections.observableArrayList();

        String query = "SELECT * FROM classe";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Classe clas = new Classe();
                clas.setId(rs.getInt("id"));
                clas.setIntitule(rs.getString("intitule"));
                clas.setCode(rs.getString("code"));
                classe.add(clas);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return classe;
    }
    public void showClasse(){
        ObservableList<Classe>liste= getClasse();
        tableau.setItems(liste);
        intitule.setCellValueFactory(new PropertyValueFactory<Classe,String>("intitule"));
        code.setCellValueFactory(new PropertyValueFactory<Classe,String>("code"));
    }

    @FXML
    void clearField(ActionEvent event){
        clear();
    }
    @FXML
    void createField(ActionEvent event){
        String insert = "INSERT INTO classe(intitule,code) VALUES(?,?)";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,recup.getText());
            st.setString(2,recup1.getText());
            st.executeUpdate();
            showClasse();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        Classe classe = tableau.getSelectionModel().getSelectedItem();
        id = classe.getId();
        intitule.setText(classe.getIntitule());
        code.setText(classe.getCode());
        ajouter.setDisable(true);
    }
    void clear(){
        intitule.setText(null);
        code.setText(null);
        ajouter.setDisable(true);
    }
    @FXML
    void deleteField(ActionEvent event){
        String delete = "DELETE FROM classe WHERE id=?";
        con = ConnexionDB.getConnect();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showClasse();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void updatesField(ActionEvent event){
        String update = "UPDATE classe SET intitule = ?, code = ? WHERE id=?";
        con = ConnexionDB.getConnect();
        try {
            st = con.prepareStatement(update);
            st.setString(1,recup.getText());
            st.setString(2,recup1.getText());
            st.setInt(3,id);
            st.executeUpdate();
            showClasse();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
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
