package com.example.demo1.controls.GuptaMICA;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GuptaMICAControl extends StackPane {

    private final GuptaMICAModel model;

    private TextField txtAge;
    private ComboBox<String> cmbFunctionalStatus;
    private ComboBox<String> cmbAsaStatus;
    private ComboBox<String> cmbCreatinine;
    private ComboBox<String> cmbSurgeryType;
    private TextArea txtResult;

    public GuptaMICAControl(GuptaMICAModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (годы)");

        cmbFunctionalStatus = new ComboBox<>();
        cmbFunctionalStatus.getItems().addAll(GuptaMICAModel.functionalStatusMap.keySet());
        cmbFunctionalStatus.setPromptText("Функциональное состояние");

        cmbAsaStatus = new ComboBox<>();
        cmbAsaStatus.getItems().addAll(GuptaMICAModel.asaStatusMap.keySet());
        cmbAsaStatus.setPromptText("ASA статус");

        cmbCreatinine = new ComboBox<>();
        cmbCreatinine.getItems().addAll(GuptaMICAModel.creatinineMap.keySet());
        cmbCreatinine.setPromptText("Креатинин");

        cmbSurgeryType = new ComboBox<>();
        cmbSurgeryType.getItems().addAll(GuptaMICAModel.surgeryTypeMap.keySet());
        cmbSurgeryType.setPromptText("Тип операции");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10,
                CalculatorHeader.createHeader("Расчет риска MICA (Myocardial Infarction or Cardiac Arrest) по шкале Gupta"),
                txtAge, cmbFunctionalStatus, cmbAsaStatus, cmbCreatinine, cmbSurgeryType, txtResult));
    }

    private void bind() {
        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAgeYears(Double.parseDouble(newVal)); } catch (Exception e) {}
            model.calc();
        });

        cmbFunctionalStatus.valueProperty().addListener((obs, oldVal, newVal) -> { model.setFunctionalStatus(newVal); model.calc(); });
        cmbAsaStatus.valueProperty().addListener((obs, oldVal, newVal) -> { model.setAsaStatus(newVal); model.calc(); });
        cmbCreatinine.valueProperty().addListener((obs, oldVal, newVal) -> { model.setCreatinine(newVal); model.calc(); });
        cmbSurgeryType.valueProperty().addListener((obs, oldVal, newVal) -> { model.setSurgeryType(newVal); model.calc(); });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
