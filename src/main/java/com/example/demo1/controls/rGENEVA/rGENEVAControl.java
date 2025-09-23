package com.example.demo1.controls.rGENEVA;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class rGENEVAControl extends StackPane {

    private final rGENEVAModel model;

    private CheckBox chkPrevPEorDVT;
    private TextField txtHeartRate;
    private CheckBox chkSurgeryOrFracture;
    private CheckBox chkHemoptysis;
    private CheckBox chkActiveCancer;
    private CheckBox chkLegPain;
    private CheckBox chkPainAndSwelling;
    private TextField txtAge;
    private TextArea txtResult;

    public rGENEVAControl(rGENEVAModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkPrevPEorDVT = new CheckBox("Предшествующие ТЭЛА или тромбозы глубоких вен");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("ЧСС (уд/мин)");
        chkSurgeryOrFracture = new CheckBox("Хирургические операции или переломы в предшествующий 1 мес.");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkActiveCancer = new CheckBox("Активное злокачественное новообразование");
        chkLegPain = new CheckBox("Боль в 1 нижней конечности");
        chkPainAndSwelling = new CheckBox("Боль при пальпации глубоких вен и односторонний отек");
        txtAge = new TextField(); txtAge.setPromptText("Возраст");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10, chkPrevPEorDVT, txtHeartRate, chkSurgeryOrFracture,
                chkHemoptysis, chkActiveCancer, chkLegPain, chkPainAndSwelling, txtAge, txtResult));
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkSurgeryOrFracture.selectedProperty().bindBidirectional(model.surgeryOrFractureProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkLegPain.selectedProperty().bindBidirectional(model.legPainProperty());
        chkPainAndSwelling.selectedProperty().bindBidirectional(model.painAndSwellingProperty());

        chkPrevPEorDVT.selectedProperty().addListener(recalcListener);
        chkSurgeryOrFracture.selectedProperty().addListener(recalcListener);
        chkHemoptysis.selectedProperty().addListener(recalcListener);
        chkActiveCancer.selectedProperty().addListener(recalcListener);
        chkLegPain.selectedProperty().addListener(recalcListener);
        chkPainAndSwelling.selectedProperty().addListener(recalcListener);

        txtHeartRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setHeartRate(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}