package com.example.demo1.controls.POAK_doze;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class POAKControl extends StackPane implements Closeable, CalculatorControl {

    private POAKModel model;
    private ComboBox<String> cmbDrug;
    private TextField txtClearance;
    private TextField txtAge;
    private TextField txtWeight;
    private TextField txtCreatinine;
    private CheckBox chkBleedingRisk;
    private TextArea txtResult;

    private final ChangeListener<String> autoCalcListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<String> drugChangeListener = (obs, oldVal, newVal) -> {
        updateVisibleFields(newVal);
        model.calc();
    };
    private final ChangeListener<Boolean> bleedingRiskListener = (obs, oldVal, newVal) -> model.calc();

    public POAKControl(POAKModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        setPadding(new Insets(15));

        cmbDrug = new ComboBox<>();
        cmbDrug.getItems().addAll("Дабигатран", "Апиксабан", "Ривароксабан");
        cmbDrug.setPromptText("Выберите препарат");

        txtClearance = new TextField();
        txtClearance.setPromptText("Клиренс креатинина (мл/мин)");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");
        txtAge.setVisible(false);

        txtWeight = new TextField();
        txtWeight.setPromptText("Вес (кг)");
        txtWeight.setVisible(false);

        txtCreatinine = new TextField();
        txtCreatinine.setPromptText("Креатинин (мкмоль/л)");
        txtCreatinine.setVisible(false);

        chkBleedingRisk = new CheckBox("Факторы риска кровотечений");
        chkBleedingRisk.setVisible(false);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");
        txtResult.setPrefHeight(150);
        txtResult.setWrapText(true);

        VBox inputs = new VBox(10,
                cmbDrug,
                txtClearance,
                txtAge,
                txtWeight,
                txtCreatinine,
                chkBleedingRisk,
                txtResult
        );

        getChildren().add(new HBox(20,
                new VBox(10,
                        CalculatorHeader.createHeader("Калькулятор дозы ПОАК"),
                        inputs
                ),
                CalculatorDescription.createDescription(
                        "Определяет рекомендуемую дозу пероральных антикоагулянтов, таких как дабигатран, ривароксабан и апиксабан" +
                                " в зависимости от клиренса креатинина, возраста и других факторов."
                )
        ));
    }

    private void bind() {
        cmbDrug.valueProperty().bindBidirectional(model.drugProperty());
        txtClearance.textProperty().bindBidirectional(model.clearanceProperty());
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        txtWeight.textProperty().bindBidirectional(model.weightProperty());
        txtCreatinine.textProperty().bindBidirectional(model.creatinineProperty());
        chkBleedingRisk.selectedProperty().bindBidirectional(model.bleedingRiskProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        cmbDrug.valueProperty().unbindBidirectional(model.drugProperty());
        txtClearance.textProperty().unbindBidirectional(model.clearanceProperty());
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        txtWeight.textProperty().unbindBidirectional(model.weightProperty());
        txtCreatinine.textProperty().unbindBidirectional(model.creatinineProperty());
        chkBleedingRisk.selectedProperty().unbindBidirectional(model.bleedingRiskProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void addListeners() {
        cmbDrug.valueProperty().addListener(drugChangeListener);
        txtClearance.textProperty().addListener(autoCalcListener);
        txtAge.textProperty().addListener(autoCalcListener);
        txtWeight.textProperty().addListener(autoCalcListener);
        txtCreatinine.textProperty().addListener(autoCalcListener);
        chkBleedingRisk.selectedProperty().addListener(bleedingRiskListener);
    }

    private void removeListeners() {
        cmbDrug.valueProperty().removeListener(drugChangeListener);
        txtClearance.textProperty().removeListener(autoCalcListener);
        txtAge.textProperty().removeListener(autoCalcListener);
        txtWeight.textProperty().removeListener(autoCalcListener);
        txtCreatinine.textProperty().removeListener(autoCalcListener);
        chkBleedingRisk.selectedProperty().removeListener(bleedingRiskListener);
    }

    private void updateVisibleFields(String drug) {
        boolean isDabigatran = "Дабигатран".equalsIgnoreCase(drug);
        boolean isApixaban = "Апиксабан".equalsIgnoreCase(drug);

        txtAge.setVisible(isDabigatran || isApixaban);
        txtWeight.setVisible(isApixaban);
        txtCreatinine.setVisible(isApixaban);
        chkBleedingRisk.setVisible(isDabigatran);
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 500;
    }

    @Override
    public double getDefaultHeight() {
        return 430;
    }
}
