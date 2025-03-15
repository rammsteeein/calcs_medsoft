package com.example.demo1.controls.Cockroft;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CockroftApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        CockroftModel model = CockroftModel.builder().build();

        CockroftControl control = new CockroftControl(model);

        Scene scene = new Scene(control, 400, 300);
        primaryStage.setTitle("Cockroft Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}