package com.example.demo1.controls.SAMSCI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class SAMSCIControl extends StackPane implements CalculatorControl, Closeable {

    private SAMSCIModel model;

    private ComboBox<String> cmbMuscleLocation;
    private ComboBox<String> cmbSymptomTiming;
    private ComboBox<String> cmbStatinDiscontinuation;
    private ComboBox<String> cmbStatinRechallenge;

    private TextField txtResult;

    private final ChangeListener<Number> muscleLocationListener = (obs, oldVal, newVal) -> {
        int index = newVal.intValue();
        switch (index) {
            case 0: model.setMuscleLocation(3); break;
            case 1: model.setMuscleLocation(2); break;
            case 2: model.setMuscleLocation(2); break;
            case 3: model.setMuscleLocation(1); break;
            default: model.setMuscleLocation(0); break;
        }
        calculate();
    };

    private final ChangeListener<Number> symptomTimingListener = (obs, oldVal, newVal) -> {
        int index = newVal.intValue();
        switch (index) {
            case 0: model.setSymptomTiming(3); break;
            case 1: model.setSymptomTiming(2); break;
            case 2: model.setSymptomTiming(1); break;
            default: model.setSymptomTiming(0); break;
        }
        calculate();
    };

    private final ChangeListener<Number> statinDiscontinuationListener = (obs, oldVal, newVal) -> {
        int index = newVal.intValue();
        switch (index) {
            case 0: model.setStatinDiscontinuation(2); break;
            case 1: model.setStatinDiscontinuation(1); break;
            case 2: model.setStatinDiscontinuation(0); break;
            default: model.setStatinDiscontinuation(0); break;
        }
        calculate();
    };

    private final ChangeListener<Number> statinRechallengeListener = (obs, oldVal, newVal) -> {
        int index = newVal.intValue();
        switch (index) {
            case 0: model.setStatinRechallenge(3); break;
            case 1: model.setStatinRechallenge(1); break;
            default: model.setStatinRechallenge(0); break;
        }
        calculate();
    };

    public SAMSCIControl(SAMSCIModel model) {
        this.model = model;
        initialize();
        addListeners();
        calculate();
    }

    private void initialize() {
        cmbMuscleLocation = createCombo("Локализация и характер боли",
                "Симметричная боль в мышцах бедер (3)",
                "Симметричная боль в икроножных мышцах (2)",
                "Симметричная боль в проксимальных мышцах рук (2)",
                "Неспецифическая асимметричная боль (1)");

        cmbSymptomTiming = createCombo("Время появления симптомов",
                "< 4 недель (3)",
                "4–12 недель (2)",
                "> 12 недель (1)");

        cmbStatinDiscontinuation = createCombo("Ответ на отмену статинов",
                "Уменьшение в течение 2 недель (2)",
                "Уменьшение в течение 2–4 недель (1)",
                "Нет улучшения > 4 недель (0)");

        cmbStatinRechallenge = createCombo("Повторное назначение статинов",
                "Симптомы снова < 4 недель (3)",
                "Симптомы снова 4–12 недель (1)");

        txtResult = new TextField();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("SAMS-CI – шкала оценки вероятности статин-индуцированной миалгии"),
                cmbMuscleLocation, cmbSymptomTiming, cmbStatinDiscontinuation, cmbStatinRechallenge,
                txtResult
        );

        this.getChildren().add(new HBox(
                20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала SAMSCI (SAMS-CI) — Клинический индекс статин-ассоциированных мышечных симптомов."
                )
        ));
    }

    private ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getItems().addAll(items);
        cmb.setPromptText(prompt);
        cmb.setMaxWidth(Double.MAX_VALUE);
        return cmb;
    }

    private void addListeners() {
        cmbMuscleLocation.getSelectionModel().selectedIndexProperty().addListener(muscleLocationListener);
        cmbSymptomTiming.getSelectionModel().selectedIndexProperty().addListener(symptomTimingListener);
        cmbStatinDiscontinuation.getSelectionModel().selectedIndexProperty().addListener(statinDiscontinuationListener);
        cmbStatinRechallenge.getSelectionModel().selectedIndexProperty().addListener(statinRechallengeListener);
    }

    private void removeListeners() {
        cmbMuscleLocation.getSelectionModel().selectedIndexProperty().removeListener(muscleLocationListener);
        cmbSymptomTiming.getSelectionModel().selectedIndexProperty().removeListener(symptomTimingListener);
        cmbStatinDiscontinuation.getSelectionModel().selectedIndexProperty().removeListener(statinDiscontinuationListener);
        cmbStatinRechallenge.getSelectionModel().selectedIndexProperty().removeListener(statinRechallengeListener);
    }

    private void calculate() {
        model.calc();
        txtResult.setText(model.getScoreWithWord());
        ResultStyler.applyStyleForValue(txtResult, model.scoreProperty().get(), 7.0, 8.0);
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 700; }

    @Override
    public double getDefaultHeight() { return 430; }
}
