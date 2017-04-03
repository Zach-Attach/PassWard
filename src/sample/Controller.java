package sample;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    @FXML
    TextField passwordField;
    @FXML
    ProgressBar difficultyBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
            System.out.println("You did something!\n");
        };

        passwordField.textProperty().addListener(changeListener);

    }
}
