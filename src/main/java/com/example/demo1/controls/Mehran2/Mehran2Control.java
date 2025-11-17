package com.example.demo1.controls.Mehran2;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.Closeable;

public class Mehran2Control extends BorderPane implements CalculatorControl, Closeable {

    private Mehran2Model model;

    private ComboBox<String> cmbOksType;
    private ComboBox<String> cmbDiabetesType;
    private CheckBox chkLvefLow;
    private CheckBox chkAnemia;
    private TextField txtAge;
    private TextField txtContrastVolume;
    private CheckBox chkBleeding;
    private TextArea txtResult;

    private final ChangeListener<Object> listener = (obs, oldVal, newVal) -> model.calc();

    public Mehran2Control(Mehran2Model model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
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
        chkBleeding = new CheckBox("Кровотечение во время ЧКВ");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtContrastVolume = new TextField();
        txtContrastVolume.setPromptText("Объём контраста (мл)");

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
        cmbDiabetesType.valueProperty().bindBidirectional(model.diabetesTypeProperty());
        chkLvefLow.selectedProperty().bindBidirectional(model.lvefLowProperty());
        chkAnemia.selectedProperty().bindBidirectional(model.anemiaProperty());
        chkBleeding.selectedProperty().bindBidirectional(model.bleedingProperty());
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {
        cmbOksType.valueProperty().addListener(listener);
        cmbDiabetesType.valueProperty().addListener(listener);
        chkLvefLow.selectedProperty().addListener(listener);
        chkAnemia.selectedProperty().addListener(listener);
        chkBleeding.selectedProperty().addListener(listener);

        txtAge.textProperty().addListener((obs, oldV, newV) -> {
            try { model.setAge(Integer.parseInt(newV)); } catch (Exception ignored) {}
            model.calc();
        });

        txtContrastVolume.textProperty().addListener((obs, oldV, newV) -> {
            try { model.setContrastVolume(Integer.parseInt(newV)); } catch (Exception ignored) {}
            model.calc();
        });
    }

    private void removeListeners() {
        cmbOksType.valueProperty().removeListener(listener);
        cmbDiabetesType.valueProperty().removeListener(listener);
        chkLvefLow.selectedProperty().removeListener(listener);
        chkAnemia.selectedProperty().removeListener(listener);
        chkBleeding.selectedProperty().removeListener(listener);
    }

    @Override
    public void close() {
        removeListeners();
        cmbOksType.valueProperty().unbindBidirectional(model.oksTypeProperty());
        cmbDiabetesType.valueProperty().unbindBidirectional(model.diabetesTypeProperty());
        chkLvefLow.selectedProperty().unbindBidirectional(model.lvefLowProperty());
        chkAnemia.selectedProperty().unbindBidirectional(model.anemiaProperty());
        chkBleeding.selectedProperty().unbindBidirectional(model.bleedingProperty());
    }

    @Override
    public double getDefaultHeight() {
        return 500;
    }
}
