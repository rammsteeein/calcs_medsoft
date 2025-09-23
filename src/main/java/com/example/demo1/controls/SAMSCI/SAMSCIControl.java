package com.example.demo1.controls.SAMSCI;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SAMSCIControl extends StackPane {

    private final SAMSCIModel model;

    private ComboBox<String> cmbMuscleLocation;
    private ComboBox<String> cmbSymptomTiming;
    private ComboBox<String> cmbStatinDiscontinuation;
    private ComboBox<String> cmbStatinRechallenge;
    private TextArea txtResult;

    public SAMSCIControl(SAMSCIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbMuscleLocation = new ComboBox<>();
        cmbMuscleLocation.getItems().addAll(
                "Симметричная боль в мышцах бедер (3)",
                "Симметричная боль в икроножных мышцах (2)",
                "Симметричная боль в проксимальных мышцах верхних конечностей (2)",
                "Неспецифическая асимметричная боль (1)"
        );

        cmbSymptomTiming = new ComboBox<>();
        cmbSymptomTiming.getItems().addAll(
                "< 4 недели (3)",
                "4-12 недель (2)",
                "> 12 недель (1)"
        );

        cmbStatinDiscontinuation = new ComboBox<>();
        cmbStatinDiscontinuation.getItems().addAll(
                "Уменьшение проявлений в течение 2 недель (2)",
                "Уменьшение проявлений 2-4 недели (1)",
                "Не уменьшается даже через 4 недели (0)"
        );

        cmbStatinRechallenge = new ComboBox<>();
        cmbStatinRechallenge.getItems().addAll(
                "Симптомы снова < 4 недель (3)",
                "Симптомы снова 4-12 недель (1)"
        );

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        this.getChildren().add(new VBox(10,
                cmbMuscleLocation,
                cmbSymptomTiming,
                cmbStatinDiscontinuation,
                cmbStatinRechallenge,
                txtResult
        ));
    }

    private void bind() {
        ChangeListener<Number> recalcListener = (obs, oldVal, newVal) -> model.calc();

        cmbMuscleLocation.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            switch (newVal.intValue()) {
                case 0 -> model.setMuscleLocation(3);
                case 1, 2 -> model.setMuscleLocation(2);
                case 3 -> model.setMuscleLocation(1);
                default -> model.setMuscleLocation(0);
            }
            model.calc();
        });

        cmbSymptomTiming.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            switch (newVal.intValue()) {
                case 0 -> model.setSymptomTiming(3);
                case 1 -> model.setSymptomTiming(2);
                case 2 -> model.setSymptomTiming(1);
                default -> model.setSymptomTiming(0);
            }
            model.calc();
        });

        cmbStatinDiscontinuation.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            switch (newVal.intValue()) {
                case 0 -> model.setStatinDiscontinuation(2);
                case 1 -> model.setStatinDiscontinuation(1);
                case 2 -> model.setStatinDiscontinuation(0);
                default -> model.setStatinDiscontinuation(0);
            }
            model.calc();
        });

        cmbStatinRechallenge.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            switch (newVal.intValue()) {
                case 0 -> model.setStatinRechallenge(3);
                case 1 -> model.setStatinRechallenge(1);
                default -> model.setStatinRechallenge(0);
            }
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}