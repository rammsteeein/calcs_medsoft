package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CKDEPIApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создание модели для контрола
        CKDEPIControl.CKDEPIModel model = new CKDEPIControl.CKDEPIModel();

        // Создание контрола и передача модели
        CKDEPIControl control = new CKDEPIControl(model);

        // Создание сцены и её установка
        Scene scene = new Scene(control, 400, 300);
        primaryStage.setTitle("CKD-EPI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX-приложения
    }
}