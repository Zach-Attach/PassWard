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
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void passWard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../passward/StyleSheet.fxml"));

        stage.setTitle("PassWard");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void regExpresser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../stoprepeatingyourself/StyleSheet.fxml"));

        stage.setTitle("Stop Repeating Yourself!");
        stage.setScene(new Scene(root, 800, 700));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void startBrittany() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../brittanyPackage/StyleSheet.fxml"));

<<<<<<< HEAD
        stage.setTitle("Matching Float Value");
=======
        stage.setTitle("Matching Float Value");////////////
>>>>>>> 51ac83d77e8e493f920b832926f81d87924f530f
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
        stage.centerOnScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
