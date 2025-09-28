package com.example.demo1.controls.Mehran2;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Mehran2Control extends BorderPane {

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

        VBox form = new VBox(10,
                CalculatorHeader.createHeader("Шкала Mehran-2"),
                chkHypotension, chkBalloonPump, chkHeartFailure,
                txtAge, chkAnemia, chkDiabetes,
                txtContrastVolume, txtGfr, txtResult
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);

        setCenter(scrollPane);
        setRight(CalculatorDescription.createDescription(
                """
                Шкала Mehran-2 используется для оценки риска контраст-индуцированной
                нефропатии (острого повреждения почек), возникающей после
                чрескожного коронарного вмешательства (ЧКВ).

                ▸ Критерии:
                • Гипотония САД <80 мм рт.ст ≥1 часа с инотропами или баллонным насосом (+5)
                • Внутриаортальный баллонный насос (+5)
                • ХСН III/IV или отек легких (+5)
                • Возраст >75 лет (+4)
                • Анемия (Ht <39% ♂, <36% ♀) (+3)
                • Сахарный диабет (+3)
                • Объем контраста: +1 балл за каждые 100 мл
                • СКФ (мл/мин/1.73 м²):
                    ≥60 — 0
                    40–59 — +2
                    20–39 — +4
                    <20 — +6
                """
        ));
    }

    private void bind() {
        chkHypotension.selectedProperty().bindBidirectional(model.hypotensionProperty());
        chkHypotension.selectedProperty().addListener((obs, o, n) -> model.calc());

        chkBalloonPump.selectedProperty().bindBidirectional(model.balloonPumpProperty());
        chkBalloonPump.selectedProperty().addListener((obs, o, n) -> model.calc());

        chkHeartFailure.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkHeartFailure.selectedProperty().addListener((obs, o, n) -> model.calc());

        chkAnemia.selectedProperty().bindBidirectional(model.anemiaProperty());
        chkAnemia.selectedProperty().addListener((obs, o, n) -> model.calc());

        chkDiabetes.selectedProperty().bindBidirectional(model.diabetesProperty());
        chkDiabetes.selectedProperty().addListener((obs, o, n) -> model.calc());

        txtAge.textProperty().addListener((obs, o, n) -> {
            try { model.setAge(Integer.parseInt(n)); } catch (Exception ignored) {}
            model.calc();
        });
        txtContrastVolume.textProperty().addListener((obs, o, n) -> {
            try { model.setContrastVolume(Double.parseDouble(n)); } catch (Exception ignored) {}
            model.calc();
        });
        txtGfr.textProperty().addListener((obs, o, n) -> {
            try { model.setGfr(Double.parseDouble(n)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
