package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by: Brittany Safford & Zachary Laborde
 */

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        mainScene();
    }

    public static void mainScene() throws IOException {

        Parent root = FXMLLoader.load(main.Main.class.getResource("../main/StyleSheet.fxml"));


        stage.setTitle("Brittany & Zach's Project - CPSC 2740");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();


    }

    @FXML
    private void passWard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../passward/StyleSheet.fxml"));

        stage.setTitle("PassWard");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    @FXML
    private void regExpresser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../regexpresser/StyleSheet.fxml")); ///////////

        stage.setTitle("RegExpresser");///////////
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    @FXML
    private void startBrittany() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../brittanyPackage/StyleSheet.fxml"));///////////

        stage.setTitle("");////////////
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
