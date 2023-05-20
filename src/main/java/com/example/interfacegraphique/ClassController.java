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
    public Button supprimer,ajouter,modifier,annuler;
    public TextField intitule,code;
    public TableView<Classe> tableau;
    @FXML
    private TableColumn<Classe, String> intit;

    @FXML
    private TableColumn<Classe, String> cod;
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
        intit.setCellValueFactory(new PropertyValueFactory<Classe,String>("Intitulé"));
        cod.setCellValueFactory(new PropertyValueFactory<Classe,String>("Code"));
    }

    @FXML
    void clearField(ActionEvent event){

    }
    @FXML
    void createField(ActionEvent event){

    }
    @FXML
    void deleteField(ActionEvent event){

    }
    @FXML
    void updatesField(ActionEvent event){

    }
}
