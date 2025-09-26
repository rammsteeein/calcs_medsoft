package com.example.demo1.controls.Khorana;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class KhoranaControl extends StackPane implements AutoCloseable {

    private final KhoranaModel model;

    private ComboBox<String> cmbTumor;
    private CheckBox cbPlatelets, cbHemoglobin, cbLeukocytes, cbHighBMI;
    private Button btnCalc;
    private TextArea txtResult;

    public KhoranaControl(KhoranaModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbTumor = new ComboBox<>();
        cmbTumor.getItems().addAll(
                "Желудок", "Поджелудочная железа", "Легкие", "Лимфома",
                "Кровь", "Яички", "Яичники", "Матка"
        );
        cmbTumor.setPromptText("Локализация опухоли");

        cbPlatelets = new CheckBox("Тромбоциты > 350 × 10⁹/л");
        cbHemoglobin = new CheckBox("Гемоглобин < 100 г/л или ЭПО");
        cbLeukocytes = new CheckBox("Лейкоциты > 11 × 10⁹/л");
        cbHighBMI = new CheckBox("ИМТ ≥ 35 кг/м²");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала Khorana"),
                cmbTumor, cbPlatelets, cbHemoglobin, cbLeukocytes, cbHighBMI,
                btnCalc, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала Khorana используется для оценки риска ВТЭО у онкологических пациентов.\n\n" +
                                "Баллы:\n" +
                                "- Локализация: желудок/ПЖ → 2; легкие/лимфома/кровь/яички/яичники/матка → 1\n" +
                                "- Тромбоциты > 350 × 10⁹/л → 1\n" +
                                "- Гемоглобин < 100 или ЭПО → 1\n" +
                                "- Лейкоциты > 11 × 10⁹/л → 1\n" +
                                "- ИМТ ≥ 35 → 1\n\n" +
                                "Интерпретация:\n" +
                                "0 → низкий риск (0.3–0.8%)\n" +
                                "1–2 → умеренный риск (1.8–2.0%)\n" +
                                "≥3 → высокий риск (6.7–7.1%)"
                )
        ));
    }

    private void bind() {
        cmbTumor.valueProperty().bindBidirectional(model.tumorLocationProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        cmbTumor.valueProperty().unbindBidirectional(model.tumorLocationProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculateResult() {
        model.getPatientFactors().clear();
        if (cbPlatelets.isSelected()) model.getPatientFactors().add(cbPlatelets.getText());
        if (cbHemoglobin.isSelected()) model.getPatientFactors().add(cbHemoglobin.getText());
        if (cbLeukocytes.isSelected()) model.getPatientFactors().add(cbLeukocytes.getText());
        if (cbHighBMI.isSelected()) model.getPatientFactors().add(cbHighBMI.getText());
        model.calc();
    }

    @Override
    public void close() {
        unbind();
    }
}
