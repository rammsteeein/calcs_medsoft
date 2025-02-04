package com.example.demo1.controls.CKDEPI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CKDEPIApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        CKDEPIModel model = CKDEPIModel.builder().build();

        CKDEPIControl control = new CKDEPIControl(model);

        Scene scene = new Scene(control, 400, 300);
        primaryStage.setTitle("CKD-EPI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX-приложения
    }
}