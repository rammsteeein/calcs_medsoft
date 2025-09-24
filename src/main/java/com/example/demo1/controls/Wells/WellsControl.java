package com.example.demo1.controls.Wells;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WellsControl extends StackPane {

    private final WellsModel model;

    private CheckBox chkPrevPEorDVT;
    private CheckBox chkTachycardia;
    private CheckBox chkSurgeryOrImmobilization;
    private CheckBox chkHemoptysis;
    private CheckBox chkActiveCancer;
    private CheckBox chkClinicalDVT;
    private CheckBox chkAlternativeLessLikely;
    private TextArea txtResult;

    public WellsControl(WellsModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkPrevPEorDVT = new CheckBox("Предшествующие ТЭЛА или тромбозы глубоких вен");
        chkTachycardia = new CheckBox("ЧСС > 100 в минуту");
        chkSurgeryOrImmobilization = new CheckBox("Хирургические операции или иммобилизация в последние 4 недели");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkActiveCancer = new CheckBox("Активное злокачественное новообразование");
        chkClinicalDVT = new CheckBox("Клинические признаки тромбоза глубоких вен");
        chkAlternativeLessLikely = new CheckBox("Альтернативный диагноз менее вероятен, чем ТЭЛА");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10, chkPrevPEorDVT, chkTachycardia, chkSurgeryOrImmobilization,
                chkHemoptysis, chkActiveCancer, chkClinicalDVT, chkAlternativeLessLikely, txtResult));
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkTachycardia.selectedProperty().bindBidirectional(model.tachycardiaProperty());
        chkSurgeryOrImmobilization.selectedProperty().bindBidirectional(model.surgeryOrImmobilizationProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkClinicalDVT.selectedProperty().bindBidirectional(model.clinicalDVTProperty());
        chkAlternativeLessLikely.selectedProperty().bindBidirectional(model.alternativeLessLikelyProperty());

        chkPrevPEorDVT.selectedProperty().addListener(recalcListener);
        chkTachycardia.selectedProperty().addListener(recalcListener);
        chkSurgeryOrImmobilization.selectedProperty().addListener(recalcListener);
        chkHemoptysis.selectedProperty().addListener(recalcListener);
        chkActiveCancer.selectedProperty().addListener(recalcListener);
        chkClinicalDVT.selectedProperty().addListener(recalcListener);
        chkAlternativeLessLikely.selectedProperty().addListener(recalcListener);

        txtResult.textProperty().bind(model.resultProperty());
    }
}