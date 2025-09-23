package com.example.demo1.controls.PESI;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PESIControl extends StackPane {

    private final PESIModel model;

    private TextField txtAge;
    private CheckBox chkMale;
    private CheckBox chkCancer;
    private CheckBox chkCHF;
    private CheckBox chkChronicLung;
    private TextField txtHeartRate;
    private TextField txtSystolicBP;
    private TextField txtRespiratoryRate;
    private TextField txtTemperature;
    private CheckBox chkAlteredMental;
    private TextField txtOxygenSaturation;
    private TextArea txtResult;

    public PESIControl(PESIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (годы)");
        chkMale = new CheckBox("Мужской пол");
        chkCancer = new CheckBox("Рак");
        chkCHF = new CheckBox("Хроническая сердечная недостаточность");
        chkChronicLung = new CheckBox("Хроническое заболевание легких");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("ЧСС (уд/мин)");
        txtSystolicBP = new TextField(); txtSystolicBP.setPromptText("Систолическое АД (мм рт.ст.)");
        txtRespiratoryRate = new TextField(); txtRespiratoryRate.setPromptText("Частота дыхания (раз/мин)");
        txtTemperature = new TextField(); txtTemperature.setPromptText("Температура (°С)");
        chkAlteredMental = new CheckBox("Нарушение сознания");
        txtOxygenSaturation = new TextField(); txtOxygenSaturation.setPromptText("Сатурация (%)");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");
        txtResult.setPrefHeight(100);

        VBox content = new VBox(10,
                txtAge, chkMale, chkCancer, chkCHF, chkChronicLung,
                txtHeartRate, txtSystolicBP, txtRespiratoryRate, txtTemperature,
                chkAlteredMental, txtOxygenSaturation, txtResult
        );
        content.setPrefWidth(350); // ширина окна
        content.setPrefHeight(600); // минимальная высота

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true); // чтобы элементы растягивались по ширине
        scrollPane.setPrefHeight(600); // высота видимой области

        this.getChildren().add(scrollPane);
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkMale.selectedProperty().bindBidirectional(model.isMaleProperty());
        chkCancer.selectedProperty().bindBidirectional(model.hasCancerProperty());
        chkCHF.selectedProperty().bindBidirectional(model.hasCHFProperty());
        chkChronicLung.selectedProperty().bindBidirectional(model.hasChronicLungDiseaseProperty());
        chkAlteredMental.selectedProperty().bindBidirectional(model.alteredMentalStatusProperty());

        chkMale.selectedProperty().addListener(recalcListener);
        chkCancer.selectedProperty().addListener(recalcListener);
        chkCHF.selectedProperty().addListener(recalcListener);
        chkChronicLung.selectedProperty().addListener(recalcListener);
        chkAlteredMental.selectedProperty().addListener(recalcListener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtHeartRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setHeartRate(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtSystolicBP.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setSystolicBP(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtRespiratoryRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setRespiratoryRate(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtTemperature.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setTemperature(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtOxygenSaturation.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setOxygenSaturation(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
