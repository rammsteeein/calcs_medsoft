package com.example.demo1.controls.CDS;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CDSControl extends StackPane implements AutoCloseable, CalculatorControl {
    private final CDSModel model;

    private ComboBox<String> cmbAppearance;
    private ComboBox<String> cmbEyes;
    private ComboBox<String> cmbMucous;
    private ComboBox<String> cmbTears;
    private TextArea txtResult;

    public CDSControl(CDSModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbAppearance = createCombo("Внешний вид",
                "Нормальный",
                "Жажда, беспокойство, раздражительность",
                "Вялость, сонливость");

        cmbEyes = createCombo("Глазные яблоки",
                "Тургор нормальный",
                "Слегка запавшие",
                "Запавшие");

        cmbMucous = createCombo("Слизистые оболочки",
                "Влажные",
                "Липкие, суховатые",
                "Сухие");

        cmbTears = createCombo("Слёзы",
                "Слезоотделение в норме",
                "Слезоотделение снижено",
                "Слёзы отсутствуют");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала CDS (оценка дегидратации)"),
                cmbAppearance, cmbEyes, cmbMucous, cmbTears, txtResult
        );

        this.getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала Clinical Dehydration Scale (CDS) используется для оценки степени обезвоживания.\n\n" +
                                "Параметры:\n" +
                                "1. Внешний вид (норма, раздражительность, вялость)\n" +
                                "2. Глазные яблоки (норма, слегка запавшие, запавшие)\n" +
                                "3. Слизистые оболочки (влажные, липкие, сухие)\n" +
                                "4. Слезы (норма, снижены, отсутствуют)\n\n" +
                                "Интерпретация:\n" +
                                "- 0 баллов — дегидратация отсутствует\n" +
                                "- 1–4 балла — лёгкая дегидратация\n" +
                                "- 5–8 баллов — средняя или тяжёлая дегидратация"
                )
        ));
    }

    private ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getItems().addAll(items);
        cmb.setPromptText(prompt);
        cmb.setMaxWidth(Double.MAX_VALUE);
        return cmb;
    }

    private void bind() {
        bindCombo(cmbAppearance, model.appearanceProperty());
        bindCombo(cmbEyes, model.eyesProperty());
        bindCombo(cmbMucous, model.mucousProperty());
        bindCombo(cmbTears, model.tearsProperty());

        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultProperty().addListener((obs, o, n) -> txtResult.setText(n != null ? n : ""));
    }

    private void bindCombo(ComboBox<String> combo, javafx.beans.property.StringProperty prop) {
        combo.valueProperty().addListener((obs, oldVal, newVal) -> {
            prop.set(newVal);
            calculate();
        });

        prop.addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(combo.getValue())) {
                combo.setValue(newVal);
            }
        });
    }

    private void unbind() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();
        double val = model.resultValueProperty().get();
        ResultStyler.applyStyleForValue(txtResult, val, 0.5, 4.5);
    }

    @Override
    public void close() {
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 430;
    }
}
