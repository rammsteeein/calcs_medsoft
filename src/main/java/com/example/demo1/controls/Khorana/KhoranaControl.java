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
                        "Шкала Хорана (Khorana) прогнозирует риск развития венозных тромбоэмболически осложнений (ВТЭО) у онкологических пациентов, которые начинают курс химиотерапии. Главная задача — определить пациентов с высоким риском и уделить им особое внимание.\n" +
                                "\n" +
                                "Шкала не применима к пациентам с опухолями головного мозга или миеломами."
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
