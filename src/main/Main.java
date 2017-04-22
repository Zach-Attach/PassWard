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
        stage.setScene(new Scene(root, 300, 200));
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
    private void stopRepeatingYourself() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../stoprepeatingyourself/StyleSheet.fxml"));

        stage.setTitle("Stop Repeating Yourself!");
        stage.setScene(new Scene(root, 800, 650));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void startBrittany() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../matchingFloatValues/StyleSheet.fxml"));///////////

        stage.setTitle("Matching Float Value");////////////
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}