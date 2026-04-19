package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.Closeable;

public class CockroftControl extends StackPane implements Closeable, CalculatorControl {

    private CockroftModel model;

    private ComboBox<Gender> cmbGender;
    private TextField nmrKreatinin;
    private TextField nmrAge;
    private TextField nmrWeight;
    private ComboBox<Unit> cmbUnit;
    private TextArea txtResult;

    private final ChangeListener<Number> resultListener =
            (o, ov, nv) -> applyStyle();

    public CockroftControl(CockroftModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Креатинин");

        cmbUnit = new ComboBox<>();
        cmbUnit.getItems().addAll(Unit.forCkdEpi());
        cmbUnit.setValue(Unit.defaultForCkdEpi());

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        nmrWeight = new TextField();
        nmrWeight.setPromptText("Вес");

        txtResult = new TextArea();
        txtResult.setEditable(false);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Клиренс креатинина (Cockcroft-Gault)"),
                cmbGender,
                new HBox(10, nmrKreatinin, cmbUnit),
                nmrAge,
                nmrWeight,
                txtResult
        );

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Формула Cockcroft–Gault используется для оценки клиренса креатинина (CrCl) — показателя фильтрационной функции почек.\n\n" +

                                "Формула:\n" +
                                "CrCl = ((140 − возраст) × масса тела × K) / (72 × Scr)\n" +
                                "где:\n" +
                                "• Scr — креатинин сыворотки (мг/дл)\n" +
                                "• K = 1.0 для мужчин и 0.85 для женщин\n\n" +

                                "Интерпретация (приближённо соответствует стадиям ХБП):\n" +
                                "• ≥ 90 мл/мин — нормальная функция (С1)\n" +
                                "• 60–89 мл/мин — незначительное снижение (С2)\n" +
                                "• 45–59 мл/мин — умеренное снижение (С3a)\n" +
                                "• 30–44 мл/мин — выраженное снижение (С3b)\n" +
                                "• 15–29 мл/мин — тяжёлое снижение (С4)\n" +
                                "• < 15 мл/мин — терминальная стадия (С5)\n\n" +

                                "Особенности:\n" +
                                "• Используется у взрослых пациентов\n" +
                                "• Учитывает массу тела (в отличие от CKD-EPI)\n" +
                                "• Может переоценивать функцию почек при ожирении или отёках\n" +
                                "• Менее точна при нестабильном уровне креатинина\n\n"
                )
        ));
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());
        cmbUnit.valueProperty().bindBidirectional(model.creatininUnitProperty());
        txtResult.textProperty().bind(model.resultStringProperty());
    }

    private void addListeners() {
        cmbGender.valueProperty().addListener((o, ov, nv) -> calculate());
        cmbUnit.valueProperty().addListener((o, ov, nv) -> calculate());
        nmrKreatinin.textProperty().addListener((o, ov, nv) -> calculate());
        nmrAge.textProperty().addListener((o, ov, nv) -> calculate());
        nmrWeight.textProperty().addListener((o, ov, nv) -> calculate());

        model.resultValueProperty().addListener(resultListener);
    }

    private void calculate() {
        model.calc();
    }

    private void applyStyle() {
        double value = model.resultValueProperty().get();
        if (Double.isNaN(value)) return;

        ResultStyler.applyStyleForValueReversed(
                txtResult,
                value,
                30,
                90
        );
    }

    private void removeListeners() {
        model.resultValueProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbind();
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }
    @Override
    public double getDefaultWidth() {
        return 800;
    }

    @Override
    public double getDefaultHeight() {
        return 540;
    }
}