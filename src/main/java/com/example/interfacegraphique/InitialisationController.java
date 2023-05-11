package com.example.interfacegraphique;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitialisationController implements Initializable{
    @FXML
    ProgressBar progressBar = new ProgressBar();
    @FXML
    Label labelprogress = new Label();

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
                labelprogress.setText("Veuillez patienter "+t1+".....");
            }
        });
        new Thread(task).start();
        task.setOnSucceeded(ev->{
            labelprogress.setText("Welcome User");
            progressBar.getScene().getWindow().hide();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
