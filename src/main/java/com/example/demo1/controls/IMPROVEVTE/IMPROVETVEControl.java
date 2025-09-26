package com.example.demo1.controls.IMPROVEVTE;

import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IMPROVETVEControl extends StackPane {

    private final IMPROVETVEModel model;

    private CheckBox chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis;
    private CheckBox chkActiveCancer, chkImmobilization7Days, chkICUstay;
    private TextField txtAge;
    private TextArea txtResult;

    public IMPROVETVEControl(IMPROVETVEModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkPriorVTE = new CheckBox("ВТЭО в анамнезе");
        chkKnownThrombophilia = new CheckBox("Известная тромбофилия");
        chkLowerLimbParalysis = new CheckBox("Парез или паралич нижних конечностей");
        chkActiveCancer = new CheckBox("Активный рак");
        chkImmobilization7Days = new CheckBox("Иммобилизация ≥7 дней");
        chkICUstay = new CheckBox("Пребывание в ICU");
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");
        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("IMPROVE VTE"),
                chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis,
                chkActiveCancer, chkImmobilization7Days, chkICUstay,
                txtAge, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "IMPROVE VTE — оценка 3-месячного риска венозной тромбоэмболии у госпитализированных пациентов.\n\n" +
                                "Факторы риска и баллы:\n" +
                                "- ВТЭО в анамнезе: 3\n" +
                                "- Тромбофилия: 2\n" +
                                "- Парез/паралич ног: 2\n" +
                                "- Активный рак: 2\n" +
                                "- Иммобилизация ≥7 дней: 1\n" +
                                "- Пребывание в ICU: 1\n" +
                                "- Возраст >60 лет: 1\n\n" +
                                "Оценка риска:\n" +
                                "0: 0,4%, 1: 0,6%, 2: 1%, 3: 1,7%, 4: 2,9%, ≥5: 7,2%"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> model.calc();

        chkPriorVTE.selectedProperty().bindBidirectional(model.priorVTEProperty());
        chkKnownThrombophilia.selectedProperty().bindBidirectional(model.knownThrombophiliaProperty());
        chkLowerLimbParalysis.selectedProperty().bindBidirectional(model.lowerLimbParalysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkImmobilization7Days.selectedProperty().bindBidirectional(model.immobilization7DaysProperty());
        chkICUstay.selectedProperty().bindBidirectional(model.ICUstayProperty());

        chkPriorVTE.selectedProperty().addListener(listener);
        chkKnownThrombophilia.selectedProperty().addListener(listener);
        chkLowerLimbParalysis.selectedProperty().addListener(listener);
        chkActiveCancer.selectedProperty().addListener(listener);
        chkImmobilization7Days.selectedProperty().addListener(listener);
        chkICUstay.selectedProperty().addListener(listener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.ageProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
