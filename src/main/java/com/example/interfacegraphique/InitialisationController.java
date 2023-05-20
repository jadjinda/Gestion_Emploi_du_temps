package com.example.interfacegraphique;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitialisationController implements Initializable{
    @FXML
    private void exit(ActionEvent event){
        System.exit(0);
    }
    @FXML
    ProgressBar progressBar = new ProgressBar();
    @FXML
    Label labelprogress = new Label();
    private double xOffset;
    private double yOffset;
    public Task<ObservableList> task = new Task<ObservableList>() {
        @Override
        protected ObservableList call() throws Exception {
            for (int i = 0; i<101; i++){
                updateProgress(i,99);
                updateMessage(""+1);
                Thread.sleep(200);
            }
            return null;
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle ressources){
        progressBar.progressProperty().bind(task.progressProperty());
        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                labelprogress.setText("Veuillez patienter.....");
            }
        });
        new Thread(task).start();
        task.setOnSucceeded(ev->{
            labelprogress.setText("Welcome User");
            progressBar.getScene().getWindow().hide();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);

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
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    }
                });
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
