package com.example.interfacegraphique;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.stage.StageStyle;
import java.sql.*;

public class MainController{
    public Button login;
    public Button signup;
    public Hyperlink retour;
    public TextField username;
    public PasswordField password;
    public TextField isusername;
    public PasswordField ispassword;
    public PasswordField isrenter;
    @FXML
    private void exit(ActionEvent event){
        System.exit(0);
    }
    private double xOffset;
    private double yOffset;
    public void Inscription() throws IOException {
        Stage stage = (Stage) signup.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene);
        stage.show();
    }
    public void insertion() throws IOException{
        PreparedStatement st = null;
        Connection con = ConnexionDB.getConnect();
        try{
            if(ispassword.getText().equals(isrenter.getText())){
                st = con.prepareStatement("INSERT INTO guest(username,password) VALUES (?,?)");
                st.setString(1, isusername.getText());
                st.setString(2,ispassword.getText());
                int rs = st.executeUpdate();
                if(rs>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insertion SuccessFully", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Insertion Error", ButtonType.OK);
                    alert.show();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Enter a correct password", ButtonType.OK);
                alert.show();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void connexion() throws IOException {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene);
        stage.show();
    }
    public void login() throws IOException{
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection con = ConnexionDB.getConnect();
        try {
            String[] roles = {"admin", "user", "guest"};
            String[] dashboards = {"DashboardAdmin.fxml", "DashboardTeachers.fxml", "Dashboardguest.fxml"};
            for (int i = 0; i < roles.length; i++) {
                st = con.prepareStatement("SELECT * FROM " + roles[i] + " WHERE username=? AND password =?");
                st.setString(1, username.getText());
                st.setString(2,password.getText());
                rs = st.executeQuery();
                if(rs.next()){
                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource(dashboards[i]));
                    primaryStage.setTitle("Acceuil");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login SuccessFully", ButtonType.OK);
                    alert.show();
                    break;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Login Error", ButtonType.OK);
                    alert.show();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}