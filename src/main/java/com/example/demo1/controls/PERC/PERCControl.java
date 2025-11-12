package com.example.demo1.controls.PERC;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PERCControl extends StackPane implements CalculatorControl {

    private final PERCModel model;

    private TextField txtAge, txtHeartRate, txtSpO2;
    private CheckBox chkLegEdema, chkHemoptysis, chkTrauma, chkPrevVte, chkHormones;
    private TextArea txtResult;

    public PERCControl(PERCModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtHeartRate = new TextField();
        txtHeartRate.setPromptText("Пульс (уд/мин)");

        txtSpO2 = new TextField();
        txtSpO2.setPromptText("SpO₂ (%)");

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

    private void bind() {
        txtAge.textProperty().addListener((obs, o, n) -> {
            try {
                model.setAge(Integer.parseInt(n));
            } catch (Exception e) {
                model.setAge(0);
            }
            model.calc();
        });

        txtHeartRate.textProperty().addListener((obs, o, n) -> {
            try {
                model.setHeartRate(Integer.parseInt(n));
            } catch (Exception e) {
                model.setHeartRate(0);
            }
            model.calc();
        });

        txtSpO2.textProperty().addListener((obs, o, n) -> {
            try {
                model.setSpo2(Integer.parseInt(n));
            } catch (Exception e) {
                model.setSpo2(0);
            }
            model.calc();
        });

        chkLegEdema.selectedProperty().addListener((obs, o, n) -> {
            model.setUnilateralLegEdema(n);
            model.calc();
        });
        chkHemoptysis.selectedProperty().addListener((obs, o, n) -> {
            model.setHemoptysis(n);
            model.calc();
        });
        chkTrauma.selectedProperty().addListener((obs, o, n) -> {
            model.setRecentTraumaOrSurgery(n);
            model.calc();
        });
        chkPrevVte.selectedProperty().addListener((obs, o, n) -> {
            model.setPreviousVte(n);
            model.calc();
        });
        chkHormones.selectedProperty().addListener((obs, o, n) -> {
            model.setHormoneUse(n);
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }

    @Override
    public double getDefaultHeight() {
        return 400;
    }

    @Override
    public double getDefaultWidth() {
        return 520;
    }
}
