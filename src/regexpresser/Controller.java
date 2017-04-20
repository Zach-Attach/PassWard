package regexpresser;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by ZyLe on 4/19/17.
 */
public class Controller {

    @FXML
    TextField goodCharTxtFld, badCharTxtFld;
    @FXML
    CheckBox exclusiveCheck;

    @FXML
    void express() {
        char[] goodCharArray = goodCharTxtFld.getText().toCharArray(),
                badCharArray = badCharTxtFld.getText().toCharArray();
    }

    @FXML
    private void back() throws IOException {

        main.Main.mainScene();
    }
}
