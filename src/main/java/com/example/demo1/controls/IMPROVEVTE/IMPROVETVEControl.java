package com.example.demo1.controls.IMPROVEVTE;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IMPROVETVEControl extends StackPane {

    private final IMPROVETVEModel model;

    private CheckBox chkPriorVTE;
    private CheckBox chkKnownThrombophilia;
    private CheckBox chkLowerLimbParalysis;
    private CheckBox chkActiveCancer;
    private CheckBox chkImmobilization7Days;
    private CheckBox chkICUstay;
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

        this.getChildren().add(new VBox(10,
                chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis,
                chkActiveCancer, chkImmobilization7Days, chkICUstay,
                txtAge, txtResult));
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkPriorVTE.selectedProperty().bindBidirectional(model.priorVTEProperty());
        chkKnownThrombophilia.selectedProperty().bindBidirectional(model.knownThrombophiliaProperty());
        chkLowerLimbParalysis.selectedProperty().bindBidirectional(model.lowerLimbParalysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkImmobilization7Days.selectedProperty().bindBidirectional(model.immobilization7DaysProperty());
        chkICUstay.selectedProperty().bindBidirectional(model.ICUstayProperty());

        chkPriorVTE.selectedProperty().addListener(recalcListener);
        chkKnownThrombophilia.selectedProperty().addListener(recalcListener);
        chkLowerLimbParalysis.selectedProperty().addListener(recalcListener);
        chkActiveCancer.selectedProperty().addListener(recalcListener);
        chkImmobilization7Days.selectedProperty().addListener(recalcListener);
        chkICUstay.selectedProperty().addListener(recalcListener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
