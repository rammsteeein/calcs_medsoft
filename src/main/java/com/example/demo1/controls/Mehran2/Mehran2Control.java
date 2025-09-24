package com.example.demo1.controls.Mehran2;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Mehran2Control extends StackPane {

    private final Mehran2Model model;

    private CheckBox chkHypotension;
    private CheckBox chkBalloonPump;
    private CheckBox chkHeartFailure;
    private TextField txtAge;
    private CheckBox chkAnemia;
    private CheckBox chkDiabetes;
    private TextField txtContrastVolume;
    private TextField txtGfr;
    private TextArea txtResult;

    public Mehran2Control(Mehran2Model model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkHypotension = new CheckBox("Гипотония САД <80 мм рт.ст.");
        chkBalloonPump = new CheckBox("Внутриаортальный баллонный насос");
        chkHeartFailure = new CheckBox("ХСН III/IV или отек легких");
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");
        chkAnemia = new CheckBox("Анемия");
        chkDiabetes = new CheckBox("СД");
        txtContrastVolume = new TextField(); txtContrastVolume.setPromptText("Объём контраста (мл)");
        txtGfr = new TextField(); txtGfr.setPromptText("СКФ (мл/мин/1,73 м²)");
        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10,
                chkHypotension, chkBalloonPump, chkHeartFailure,
                txtAge, chkAnemia, chkDiabetes,
                txtContrastVolume, txtGfr, txtResult));
    }

    private void bind() {
        // --- чекбоксы ---
        chkHypotension.selectedProperty().bindBidirectional(model.hypotensionProperty());
        chkHypotension.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        chkBalloonPump.selectedProperty().bindBidirectional(model.balloonPumpProperty());
        chkBalloonPump.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        chkHeartFailure.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkHeartFailure.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        chkAnemia.selectedProperty().bindBidirectional(model.anemiaProperty());
        chkAnemia.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        chkDiabetes.selectedProperty().bindBidirectional(model.diabetesProperty());
        chkDiabetes.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtContrastVolume.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setContrastVolume(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtGfr.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setGfr(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
