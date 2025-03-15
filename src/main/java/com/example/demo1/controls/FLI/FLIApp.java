package com.example.demo1.controls.FLI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FLIApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        FLIModel model = FLIModel.builder().build();

        FLIControl control = new FLIControl(model);

        Scene scene = new Scene(control, 400, 300);
        primaryStage.setTitle("FLI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}