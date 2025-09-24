package com.example.demo1.controls.Khorana;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class KhoranaControl extends StackPane implements AutoCloseable {

    private KhoranaModel model = KhoranaModel.builder().build();

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
        cmbTumor.setPromptText("Выберите локализацию опухоли");

        cbPlatelets = new CheckBox("Тромбоциты > 350 х10⁹/л");
        cbHemoglobin = new CheckBox("Гемоглобин < 100 г/л или эритропоэтин");
        cbLeukocytes = new CheckBox("Лейкоциты > 11 х10⁹/л");
        cbHighBMI = new CheckBox("ИМТ ≥ 35 кг/м²");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        this.getChildren().add(new VBox(10.0, new Node[]{
                cmbTumor, cbPlatelets, cbHemoglobin, cbLeukocytes, cbHighBMI,
                btnCalc, txtResult
        }));
    }

    private void bind() {
        cmbTumor.valueProperty().bindBidirectional(model.tumorLocationProperty());
    }

    private void unbind() {
        cmbTumor.valueProperty().unbindBidirectional(model.tumorLocationProperty());
    }

    private void calculateResult() {
        model.getPatientFactors().clear();
        if (cbPlatelets.isSelected()) model.getPatientFactors().add(cbPlatelets.getText());
        if (cbHemoglobin.isSelected()) model.getPatientFactors().add(cbHemoglobin.getText());
        if (cbLeukocytes.isSelected()) model.getPatientFactors().add(cbLeukocytes.getText());
        if (cbHighBMI.isSelected()) model.getPatientFactors().add(cbHighBMI.getText());

        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }

        txtResult.setText(model.resultProperty().get());
    }

    @Override
    public void close() {
        unbind();
    }
}