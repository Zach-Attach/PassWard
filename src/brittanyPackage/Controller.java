package brittanyPackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by: Brittany Safford & Zachary Laborde
 */

public class Controller {

    public TextField txtValue;
    public Label lblResult;
    public Button btnCheck;

    private static MatchingFloatValues mfv = new MatchingFloatValues();

    @FXML
    private void back() throws IOException {
        main.Main.mainScene();
    }

    public void checkValue(ActionEvent actionEvent) {
        String input = txtValue.getText().toString();
        boolean isFloat = mfv.isFloatingPointValue(input);

        if (isFloat)
            lblResult.setText(String.format("%s is a floating point value.", input));
        else
            lblResult.setText(String.format("%s is not a floating point value.", input));

    }
}
