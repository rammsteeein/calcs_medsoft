package com.example.demo1.controls.HASBLED;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HASBLEDControl extends StackPane implements AutoCloseable, CalculatorControl {
    private final HASBLEDModel model;

    private CheckBox chkHypertension;
    private CheckBox chkRenalLiver;
    private CheckBox chkStroke;
    private CheckBox chkBleeding;
    private CheckBox chkINR;
    private CheckBox chkAge;
    private CheckBox chkDrugsAlcohol;
    private TextArea txtResult;

    public HASBLEDControl(HASBLEDModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkHypertension = createCheck(
                "Артериальная гипертензия (>160 мм рт. ст.)",
                "Отметьте, если у пациента стойкое систолическое давление выше 160 мм рт. ст."
        );
        chkRenalLiver = createCheck(
                "Нарушение функции почек и/или печени",
                "Отметьте, если есть хроническое заболевание почек или печени."
        );
        chkStroke = createCheck(
                "Перенесённый инсульт",
                "Отметьте, если пациент когда-либо переносил ишемический или геморрагический инсульт."
        );
        chkBleeding = createCheck(
                "История кровотечений",
                "Отметьте, если ранее были желудочно-кишечные, внутричерепные или другие значимые кровотечения."
        );
        chkINR = createCheck(
                "Лабильное МНО (при приёме варфарина)",
                "Отметьте, если INR часто вне целевого диапазона."
        );
        chkAge = createCheck(
                "Возраст ≥65 лет",
                "Отметьте, если пациенту 65 лет и больше."
        );
        chkDrugsAlcohol = createCheck(
                "Приём НПВП, антиагрегантов или алкоголя",
                "Отметьте, если пациент принимает препараты или алкоголь, повышающие риск кровотечений."
        );

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");


        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала HAS-BLED (оценка риска кровотечений)"),
                chkHypertension, chkRenalLiver, chkStroke, chkBleeding,
                chkINR, chkAge, chkDrugsAlcohol, txtResult
        );

        this.getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "HAS-BLED — шкала оценки риска кровотечений у пациентов на антикоагулянтах.\n\n" +
                                "Компоненты (по 1 баллу):\n" +
                                "H — Гипертензия\n" +
                                "A — Почки/печень нарушены\n" +
                                "S — Инсульт в анамнезе\n" +
                                "B — Кровотечения\n" +
                                "L — Лабильное МНО\n" +
                                "E — Возраст ≥65 лет\n" +
                                "D — Препараты / алкоголь\n\n" +
                                "Интерпретация:\n" +
                                "- 0–2 балла — низкий/умеренный риск\n" +
                                "- ≥3 баллов — высокий риск, требуется осторожность"
                )
        ));
    }

    private CheckBox createCheck(String text, String tooltipText) {
        CheckBox chk = new CheckBox(text);
        chk.setTooltip(new Tooltip(tooltipText));
        chk.setMaxWidth(Double.MAX_VALUE);
        return chk;
    }

    private void bind() {
        bindCheck(chkHypertension, model.hypertensionProperty());
        bindCheck(chkRenalLiver, model.renalLiverProperty());
        bindCheck(chkStroke, model.strokeProperty());
        bindCheck(chkBleeding, model.bleedingProperty());
        bindCheck(chkINR, model.inrProperty());
        bindCheck(chkAge, model.ageProperty());
        bindCheck(chkDrugsAlcohol, model.drugsAlcoholProperty());

        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultProperty().addListener((obs, o, n) -> {
            txtResult.setText(n != null ? n : "");
        });
    }

    private void bindCheck(CheckBox chk, javafx.beans.property.StringProperty prop) {
        chk.selectedProperty().addListener((obs, oldVal, newVal) -> {
            prop.set(newVal ? "Да" : "Нет");
            calculate();
        });

        prop.addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                chk.setSelected("Да".equals(newVal));
            } else {
                chk.setSelected(false);
            }
        });

        if ("Да".equals(prop.get())) {
            chk.setSelected(true);
        } else {
            chk.setSelected(false);
        }
    }

    private void unbind() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();
        double val = model.resultValueProperty().get();
        ResultStyler.applyStyleForValue(txtResult, val, 2, 2.5);
    }

    @Override
    public void close() {
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 720;
    }

    @Override
    public double getDefaultHeight() {
        return 460;
    }
}
