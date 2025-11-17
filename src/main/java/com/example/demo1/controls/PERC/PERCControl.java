package com.example.demo1.controls.PERC;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class PERCControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final PERCModel model;

    private TextField txtAge, txtHeartRate, txtSpO2;
    private CheckBox chkLegEdema, chkHemoptysis, chkTrauma, chkPrevVte, chkHormones;
    private TextArea txtResult;

    private final List<ChangeListener<?>> listeners = new ArrayList<>();

    public PERCControl(PERCModel model) {
        this.model = model;
        initialize();
        addListeners();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("Пульс (уд/мин)");
        txtSpO2 = new TextField(); txtSpO2.setPromptText("SpO₂ (%)");

        chkLegEdema = new CheckBox("Односторонний отёк ноги");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkTrauma = new CheckBox("Недавняя травма или операция");
        chkPrevVte = new CheckBox("Предшествующий ТГВ/ТЭЛА");
        chkHormones = new CheckBox("Приём гормонов");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Правило PERC"),
                txtAge, txtHeartRate, txtSpO2,
                chkLegEdema, chkHemoptysis, chkTrauma, chkPrevVte, chkHormones,
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "PERC (Pulmonary Embolism Rule-out Criteria)\n\n" +
                                "Позволяет исключить вероятность ТЭЛА у пациентов с низким риском.\n" +
                                "Если все критерии отрицательны — вероятность менее 2%."
                )
        ));
    }

    private void addListeners() {
        ChangeListener<String> ageListener = (obs, o, n) -> {
            try { model.setAge(Integer.parseInt(n)); } catch (Exception e) { model.setAge(0); }
            model.calc();
        };
        txtAge.textProperty().addListener(ageListener);
        listeners.add(ageListener);

        ChangeListener<String> heartRateListener = (obs, o, n) -> {
            try { model.setHeartRate(Integer.parseInt(n)); } catch (Exception e) { model.setHeartRate(0); }
            model.calc();
        };
        txtHeartRate.textProperty().addListener(heartRateListener);
        listeners.add(heartRateListener);

        ChangeListener<String> spO2Listener = (obs, o, n) -> {
            try { model.setSpo2(Integer.parseInt(n)); } catch (Exception e) { model.setSpo2(0); }
            model.calc();
        };
        txtSpO2.textProperty().addListener(spO2Listener);
        listeners.add(spO2Listener);

        ChangeListener<Boolean> legEdemaListener = (obs, o, n) -> { model.setUnilateralLegEdema(n); model.calc(); };
        chkLegEdema.selectedProperty().addListener(legEdemaListener);
        listeners.add(legEdemaListener);

        ChangeListener<Boolean> hemoptysisListener = (obs, o, n) -> { model.setHemoptysis(n); model.calc(); };
        chkHemoptysis.selectedProperty().addListener(hemoptysisListener);
        listeners.add(hemoptysisListener);

        ChangeListener<Boolean> traumaListener = (obs, o, n) -> { model.setRecentTraumaOrSurgery(n); model.calc(); };
        chkTrauma.selectedProperty().addListener(traumaListener);
        listeners.add(traumaListener);

        ChangeListener<Boolean> prevVteListener = (obs, o, n) -> { model.setPreviousVte(n); model.calc(); };
        chkPrevVte.selectedProperty().addListener(prevVteListener);
        listeners.add(prevVteListener);

        ChangeListener<Boolean> hormonesListener = (obs, o, n) -> { model.setHormoneUse(n); model.calc(); };
        chkHormones.selectedProperty().addListener(hormonesListener);
        listeners.add(hormonesListener);

        txtResult.textProperty().bind(model.resultProperty());
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener((ChangeListener<? super String>) listeners.get(0));
        txtHeartRate.textProperty().removeListener((ChangeListener<? super String>) listeners.get(1));
        txtSpO2.textProperty().removeListener((ChangeListener<? super String>) listeners.get(2));
        chkLegEdema.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(3));
        chkHemoptysis.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(4));
        chkTrauma.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(5));
        chkPrevVte.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(6));
        chkHormones.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(7));
        listeners.clear();
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultHeight() { return 400; }

    @Override
    public double getDefaultWidth() { return 520; }
}
