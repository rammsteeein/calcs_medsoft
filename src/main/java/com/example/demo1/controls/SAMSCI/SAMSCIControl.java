package com.example.demo1.controls.SAMSCI;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
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
                "Симметричная боль в проксимальных мышцах рук (2)",
                "Неспецифическая асимметричная боль (1)"
        );
        cmbMuscleLocation.setPromptText("Локализация и характер боли");

        cmbSymptomTiming = new ComboBox<>();
        cmbSymptomTiming.getItems().addAll(
                "< 4 недель (3)", "4–12 недель (2)", "> 12 недель (1)"
        );
        cmbSymptomTiming.setPromptText("Время появления симптомов");

        cmbStatinDiscontinuation = new ComboBox<>();
        cmbStatinDiscontinuation.getItems().addAll(
                "Уменьшение в течение 2 недель (2)",
                "Уменьшение в течение 2–4 недель (1)",
                "Нет улучшения > 4 недель (0)"
        );
        cmbStatinDiscontinuation.setPromptText("Ответ на отмену статинов");

        cmbStatinRechallenge = new ComboBox<>();
        cmbStatinRechallenge.getItems().addAll(
                "Симптомы снова < 4 недель (3)",
                "Симптомы снова 4–12 недель (1)"
        );
        cmbStatinRechallenge.setPromptText("Повторное назначение статинов");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("SAMS-CI – шкала оценки вероятности статин-индуцированной миалгии"),
                cmbMuscleLocation,
                cmbSymptomTiming,
                cmbStatinDiscontinuation,
                cmbStatinRechallenge,
                txtResult
        );

        this.getChildren().add(new HBox(
                20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала SAMSCI (SAMS-CI) — это Клинический индекс статин-ассоциированных мышечных симптомов" +
                                " (Statins-Associated Muscle Symptoms – Clinical Index), используемый для оценки" +
                                " вероятности связи мышечных симптомов с приемом статинов. Индекс анализирует три" +
                                " ключевых фактора: время появления симптомов, их ответ на отмену статинов и повторное" +
                                " назначение препаратов. "
                )
        ));
    }

    private void bind() {
        cmbMuscleLocation.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0:
                    model.setMuscleLocation(3);
                    break;
                case 1:
                case 2:
                    model.setMuscleLocation(2);
                    break;
                case 3:
                    model.setMuscleLocation(1);
                    break;
                default:
                    model.setMuscleLocation(0);
                    break;
            }
            model.calc();
        });

        cmbSymptomTiming.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0:
                    model.setSymptomTiming(3);
                    break;
                case 1:
                    model.setSymptomTiming(2);
                    break;
                case 2:
                    model.setSymptomTiming(1);
                    break;
                default:
                    model.setSymptomTiming(0);
                    break;
            }
            model.calc();
        });

        cmbStatinDiscontinuation.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0:
                    model.setStatinDiscontinuation(2);
                    break;
                case 1:
                    model.setStatinDiscontinuation(1);
                    break;
                case 2:
                    model.setStatinDiscontinuation(0);
                    break;
                default:
                    model.setStatinDiscontinuation(0);
                    break;
            }
            model.calc();
        });

        cmbStatinRechallenge.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            switch (index) {
                case 0:
                    model.setStatinRechallenge(3);
                    break;
                case 1:
                    model.setStatinRechallenge(1);
                    break;
                default:
                    model.setStatinRechallenge(0);
                    break;
            }
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
