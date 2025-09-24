package com.example.demo1.controls.RCRI;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RCRIControl extends StackPane {

    private final RCRIModel model;

    private CheckBox chkHighRiskSurgery;
    private CheckBox chkIschemicHeartDisease;
    private CheckBox chkHeartFailure;
    private CheckBox chkCerebrovascularDisease;
    private CheckBox chkInsulinTreatment;
    private CheckBox chkHighCreatinine;
    private TextArea txtResult;

    public RCRIControl(RCRIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkHighRiskSurgery = new CheckBox("Хирургия повышенного риска");
        chkIschemicHeartDisease = new CheckBox("Анамнез ИБС");
        chkHeartFailure = new CheckBox("Застойная СН в анамнезе");
        chkCerebrovascularDisease = new CheckBox("Анамнез цереброваскулярных заболеваний");
        chkInsulinTreatment = new CheckBox("Предоперационное лечение инсулином");
        chkHighCreatinine = new CheckBox("Предоперационный креатинин >176,8 мкмоль/л");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10,
                CalculatorHeader.createHeader("Индекс RCRI"),
                chkHighRiskSurgery, chkIschemicHeartDisease, chkHeartFailure,
                chkCerebrovascularDisease, chkInsulinTreatment, chkHighCreatinine,
                txtResult));
    }

    private void bind() {
        chkHighRiskSurgery.selectedProperty().bindBidirectional(model.highRiskSurgeryProperty());
        chkIschemicHeartDisease.selectedProperty().bindBidirectional(model.ischemicHeartDiseaseProperty());
        chkHeartFailure.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkCerebrovascularDisease.selectedProperty().bindBidirectional(model.cerebrovascularDiseaseProperty());
        chkInsulinTreatment.selectedProperty().bindBidirectional(model.insulinTreatmentProperty());
        chkHighCreatinine.selectedProperty().bindBidirectional(model.highCreatinineProperty());

        model.calc();
        txtResult.textProperty().bind(model.resultProperty());

        // Вызываем пересчет при изменении галочек
        chkHighRiskSurgery.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkIschemicHeartDisease.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkHeartFailure.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkCerebrovascularDisease.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkInsulinTreatment.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkHighCreatinine.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
    }
}
