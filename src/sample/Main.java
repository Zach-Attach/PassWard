package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by: Brittany Stafford & Zachary Laborde
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PassWard");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    int getSpaceDepth(String s) {

        //TODO - FINISH THIS USING REGEXXXXX :P

//        Space Depth (SD) - num = 10, alpha=26, symbol=33; sum of these
//  Ex: 1a# = 10+26+33 = 69

        return 0;
    }

    long getSearchSpace(int spaceDepth, int length) {

        // Search Space Size = SD^n + SD^(n-1) + ... + SD^1

        long rVal = 0;

        for (; length > 0; length--) {
            rVal += Math.pow(spaceDepth, length);
        }

        return rVal;
    }

    double[] getTimeEstimations(long searchSpace) {

/*        TIME

        Online Attack Scenario - 1,000 guesses/sec

        Offline Fast Attack Scenario - 1 x 10^9 guesses/sec   (1 billion)

        Massive Cracking Array Scenario - 1 x 10^12 guesses/sec (1 trillion)*/

        return new double[]{
                searchSpace / Math.pow(10, 9),
                searchSpace / Math.pow(10, 9),
                searchSpace / Math.pow(10, 12)
        };

    }

    /*



COLOR INDICATION (cutoff points)

SUGGESTIONS (you should use a symbol or whatever)
*/
}
