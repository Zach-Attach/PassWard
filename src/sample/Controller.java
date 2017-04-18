package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    TextField passwordField;
    @FXML
    ProgressBar strengthBar;
    @FXML
    Label strengthLbl;
    @FXML
    Label suggestionLbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {

            String strength;
            String[] atkArr = new String[3];

            if (passwordField.getText().length() > 0) {
                DifficultyCalculator calc = new DifficultyCalculator(passwordField.getText());

                strength = calc.getStrength();

                atkArr = calc.determineEstimatedTimesAsStrings();

                suggestionLbl.setText(String.format("Try adding:%s%s%s%s",
                        (calc.isMissingLower()) ? "\n\t* a lowercase letter" : "",
                        (calc.isMissingUpper()) ? "\n\t* an uppercase letter" : "",
                        (calc.isMissingNum()) ? "\n\t* a number" : "",
                        (calc.isMissingSym()) ? "\n\t* a symbol" : ""));

            } else {
                strength = "NONE";

                suggestionLbl.setText("");
            }

            switch (strength) {
                case "NONE":
                    strengthBar.setProgress(0);
                    break;
                case "WEAK":
                    strengthBar.setProgress(.25);
                    break;
                case "OK":
                    strengthBar.setProgress(.50);
                    break;
                case "GOOD":
                    strengthBar.setProgress(.75);
                    break;
                case "STRONG":
                    strengthBar.setProgress(1.00);
                    break;
            }

            if (strength.equals("NONE")) {
                strengthLbl.setText("");

            } else {
                strengthLbl.setText(String.format(
                        "Difficulty: %s \n\nTime to Crack Your Password: \n\t\tOnline Single Computer:\t%s \n\t\tOffline Fast Attack:\t%s \n\t\tMassive Cracking Attack:\t%s", strength, atkArr[0], atkArr[1], atkArr[2]));
            }


        });
    }
}
