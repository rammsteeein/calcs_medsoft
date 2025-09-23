package com.example.demo1.controls.FIB4;

import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.Closeable;

public class FIB4Control extends StackPane implements Closeable {

    private FIB4Model model = FIB4Model.builder().build();
    private TextField txtAge, txtAst, txtAlt, txtPlatelets;
    private Button btnCalc;
    private TextArea txtResult;

    public FIB4Control(FIB4Model model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 15;");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtAst = new TextField();
        txtAst.setPromptText("АСТ (Ед/л)");

        txtAlt = new TextField();
        txtAlt.setPromptText("АЛТ (Ед/л)");

        txtPlatelets = new TextField();
        txtPlatelets.setPromptText("Тромбоциты (10^9/л)");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculate());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        vbox.getChildren().addAll(txtAge, txtAst, txtAlt, txtPlatelets, btnCalc, txtResult);

        getChildren().add(vbox);
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        txtAst.textProperty().bindBidirectional(model.astProperty());
        txtAlt.textProperty().bindBidirectional(model.altProperty());
        txtPlatelets.textProperty().bindBidirectional(model.plateletsProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        txtAst.textProperty().unbindBidirectional(model.astProperty());
        txtAlt.textProperty().unbindBidirectional(model.altProperty());
        txtPlatelets.textProperty().unbindBidirectional(model.plateletsProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        try {
            model.calc();
        } catch (Exception e) {
            txtResult.setText("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}