package passward;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: Brittany Safford & Zachary Laborde
 */

public class Controller implements Initializable {

    @FXML
    TextField passwordField;
    @FXML
    ProgressBar strengthBar;
    @FXML
    Label strengthLbl;
    @FXML
    Label suggestionLbl;

    private static final String
            RED_BAR = "red-bar",
            ORANGE_BAR = "orange-bar",
            YELLOW_BAR = "yellow-bar",
            GREEN_BAR = "green-bar";
    private static final String[] ALL_BAR_COLORS = {RED_BAR, ORANGE_BAR, YELLOW_BAR, GREEN_BAR};


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

                suggestionLbl.setText("\n\n\n\n");
            }

            switch (strength) {
                case "NONE":
                    changeBar(0, RED_BAR);
                    break;
                case "WEAK":
                    changeBar(.25, RED_BAR);
                    break;
                case "OK":
                    changeBar(.50, ORANGE_BAR);
                    break;
                case "GOOD":
                    changeBar(.75, YELLOW_BAR);
                    break;
                case "STRONG":
                    changeBar(1, GREEN_BAR);
                    break;
            }

            if (strength.equals("NONE")) {
                strengthLbl.setText("");

            } else {
                strengthLbl.setText(String.format(
                        "%s\n\n\n%s\n%s\n%s", strength, atkArr[0], atkArr[1], atkArr[2]));
            }

        });
    }

    //changes the progress bar
    private void changeBar(double progress, String color) {
        strengthBar.getStyleClass().removeAll(ALL_BAR_COLORS);

        strengthBar.getStyleClass().add(color);
        strengthBar.setProgress(progress);
    }

    @FXML
    private void back() throws IOException {

        main.Main.mainScene();
    }
}
