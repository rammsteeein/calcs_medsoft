package com.example.demo1.controls.SAMSCI;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SAMSCIControl extends StackPane {

    private final SAMSCIModel model;

    private ComboBox<String> cmbMuscleLocation;
    private ComboBox<String> cmbSymptomTiming;
    private ComboBox<String> cmbStatinDiscontinuation;
    private ComboBox<String> cmbStatinRechallenge;

    private TextField txtScore;
    private TextField txtInterpretation;

    public SAMSCIControl(SAMSCIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbMuscleLocation = new ComboBox<String>();
        cmbMuscleLocation.getItems().addAll(
                "Симметричная боль в мышцах бедер (3)",
                "Симметричная боль в икроножных мышцах (2)",
                "Симметричная боль в проксимальных мышцах рук (2)",
                "Неспецифическая асимметричная боль (1)"
        );
        cmbMuscleLocation.setPromptText("Локализация и характер боли");

        cmbSymptomTiming = new ComboBox<String>();
        cmbSymptomTiming.getItems().addAll(
                "< 4 недель (3)", "4–12 недель (2)", "> 12 недель (1)"
        );
        cmbSymptomTiming.setPromptText("Время появления симптомов");

        cmbStatinDiscontinuation = new ComboBox<String>();
        cmbStatinDiscontinuation.getItems().addAll(
                "Уменьшение в течение 2 недель (2)",
                "Уменьшение в течение 2–4 недель (1)",
                "Нет улучшения > 4 недель (0)"
        );
        cmbStatinDiscontinuation.setPromptText("Ответ на отмену статинов");

        cmbStatinRechallenge = new ComboBox<String>();
        cmbStatinRechallenge.getItems().addAll(
                "Симптомы снова < 4 недель (3)",
                "Симптомы снова 4–12 недель (1)"
        );
        cmbStatinRechallenge.setPromptText("Повторное назначение статинов");

        txtScore = new TextField();
        txtScore.setEditable(false);
        txtScore.setPromptText("Баллы");

        txtInterpretation = new TextField();
        txtInterpretation.setEditable(false);
        txtInterpretation.setPromptText("Интерпретация");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("SAMS-CI – шкала оценки вероятности статин-индуцированной миалгии"),
                cmbMuscleLocation,
                cmbSymptomTiming,
                cmbStatinDiscontinuation,
                cmbStatinRechallenge,
                txtScore,
                txtInterpretation
        );

        this.getChildren().add(new HBox(
                20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала SAMSCI (SAMS-CI) — Клинический индекс статин-ассоциированных мышечных симптомов."
                )
        ));
    }

    private void bind() {
        cmbMuscleLocation.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0: model.setMuscleLocation(3); break;
                case 1: model.setMuscleLocation(2); break;
                case 2: model.setMuscleLocation(2); break;
                case 3: model.setMuscleLocation(1); break;
                default: model.setMuscleLocation(0); break;
            }
            model.calc();
        });

        cmbSymptomTiming.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0: model.setSymptomTiming(3); break;
                case 1: model.setSymptomTiming(2); break;
                case 2: model.setSymptomTiming(1); break;
                default: model.setSymptomTiming(0); break;
            }
            model.calc();
        });

        cmbStatinDiscontinuation.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0: model.setStatinDiscontinuation(2); break;
                case 1: model.setStatinDiscontinuation(1); break;
                case 2: model.setStatinDiscontinuation(0); break;
                default: model.setStatinDiscontinuation(0); break;
            }
            model.calc();
        });

        cmbStatinRechallenge.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0: model.setStatinRechallenge(3); break;
                case 1: model.setStatinRechallenge(1); break;
                default: model.setStatinRechallenge(0); break;
            }
            model.calc();
        });

        txtScore.textProperty().bind(model.scoreProperty().asString());
        txtInterpretation.textProperty().bind(model.interpretationProperty());

        model.interpretationProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                if (newVal.contains("Достоверная")) {
                    ResultStyler.applyStyle(txtInterpretation, ResultStyler.Zone.HIGH);
                } else if (newVal.contains("Возможная")) {
                    ResultStyler.applyStyle(txtInterpretation, ResultStyler.Zone.GRAY);
                } else {
                    ResultStyler.applyStyle(txtInterpretation, ResultStyler.Zone.LOW);
                }
            }
        });

    }
}
