package com.example.demo1.controls.Mehran2;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Mehran2Control extends BorderPane implements CalculatorControl {

    private final Mehran2Model model;

    private ComboBox<String> cmbOksType;
    private ComboBox<String> cmbDiabetesType;
    private CheckBox chkLvefLow;
    private CheckBox chkAnemia;
    private TextField txtAge;
    private TextField txtContrastVolume;
    private CheckBox chkBleeding;
    private TextArea txtResult;

    public Mehran2Control(Mehran2Model model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbOksType = new ComboBox<>();
        cmbOksType.getItems().addAll("STEMI", "NSTEMI", "Нестабильная стенокардия");
        cmbOksType.setValue("STEMI");

        cmbDiabetesType = new ComboBox<>();
        cmbDiabetesType.getItems().addAll("Инсулинозависимый", "Неинсулинозависимый");
        cmbDiabetesType.setValue("Инсулинозависимый");

        chkLvefLow = new CheckBox("LVEF <40%");
        chkAnemia = new CheckBox("Анемия (Hb <11 г/дл)");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtContrastVolume = new TextField();
        txtContrastVolume.setPromptText("Объём контраста (мл)");

        chkBleeding = new CheckBox("Кровотечение во время ЧКВ");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox form = new VBox(10,
                CalculatorHeader.createHeader("Шкала Mehran-2"),
                new Label("Тип ОКС"), cmbOksType,
                new Label("Тип диабета"), cmbDiabetesType,
                chkLvefLow,
                chkAnemia,
                new Label("Возраст"), txtAge,
                new Label("Объём контраста"), txtContrastVolume,
                chkBleeding,
                txtResult
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);

        setCenter(scrollPane);
        setRight(CalculatorDescription.createDescription(
                "Шкала Mehran-2 (обновлённая) для оценки риска контраст-индуцированной нефропатии после ЧКВ.\n\n" +
                        "Интерпретация по сумме баллов:\n" +
                        "0–2 — Низкий\n" +
                        "3–7 — Средний\n" +
                        "8–11 — Высокий\n" +
                        "≥12 — Очень высокий"
        ));
    }

    private void bind() {
        cmbOksType.valueProperty().bindBidirectional(model.oksTypeProperty());
        cmbOksType.valueProperty().addListener((obs, o, n) -> model.calc());

        cmbDiabetesType.valueProperty().bindBidirectional(model.diabetesTypeProperty());
        cmbDiabetesType.valueProperty().addListener((obs, o, n) -> model.calc());

        chkLvefLow.selectedProperty().bindBidirectional(model.lvefLowProperty());
        chkLvefLow.selectedProperty().addListener((obs, o, n) -> model.calc());

        chkAnemia.selectedProperty().bindBidirectional(model.anemiaProperty());
        chkAnemia.selectedProperty().addListener((obs, o, n) -> model.calc());

        chkBleeding.selectedProperty().bindBidirectional(model.bleedingProperty());
        chkBleeding.selectedProperty().addListener((obs, o, n) -> model.calc());

        txtAge.textProperty().addListener((obs, o, n) -> {
            try { model.setAge(Integer.parseInt(n)); } catch (Exception ignored) {}
            model.calc();
        });

        txtContrastVolume.textProperty().addListener((obs, o, n) -> {
            try { model.setContrastVolume(Integer.parseInt(n)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }

    @Override
    public double getDefaultHeight() {
        return 500;
    }
}