package com.example.demo1.controls.GuptaMICA;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class GuptaMICAControl extends StackPane implements CalculatorControl, Closeable {

    private final GuptaMICAModel model;

    private TextField txtAge;
    private ComboBox<String> cmbFunctionalStatus;
    private ComboBox<String> cmbAsaStatus;
    private ComboBox<String> cmbCreatinine;
    private ComboBox<String> cmbSurgeryType;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (obs,o,n) -> calculate();
    private final ChangeListener<String> functionalListener = (obs,o,n) -> calculate();
    private final ChangeListener<String> asaListener = (obs,o,n) -> calculate();
    private final ChangeListener<String> creatinineListener = (obs,o,n) -> calculate();
    private final ChangeListener<String> surgeryListener = (obs,o,n) -> calculate();

    public GuptaMICAControl(GuptaMICAModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
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

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Расчет риска MICA (Myocardial Infarction or Cardiac Arrest) по шкале Gupta"),
                new VBox(new Label("Возраст (годы)"), txtAge),
                new VBox(new Label("Функциональное состояние"), cmbFunctionalStatus),
                new VBox(new Label("ASA статус"), cmbAsaStatus),
                new VBox(new Label("Креатинин"), cmbCreatinine),
                new VBox(new Label("Тип операции"), cmbSurgeryType),
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Gupta MICA — модель оценки риска инфаркта миокарда или остановки сердца у пациентов после операции.\n" +
                                "Используемые параметры: возраст, функциональное состояние, ASA статус, креатинин, тип операции."
                )
        ));
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
        model.riskPercentProperty().addListener((obs,o,n) -> applyStyle(n.doubleValue()));
    }

    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);
        cmbFunctionalStatus.valueProperty().addListener(functionalListener);
        cmbAsaStatus.valueProperty().addListener(asaListener);
        cmbCreatinine.valueProperty().addListener(creatinineListener);
        cmbSurgeryType.valueProperty().addListener(surgeryListener);
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);
        cmbFunctionalStatus.valueProperty().removeListener(functionalListener);
        cmbAsaStatus.valueProperty().removeListener(asaListener);
        cmbCreatinine.valueProperty().removeListener(creatinineListener);
        cmbSurgeryType.valueProperty().removeListener(surgeryListener);
    }

    private void calculate() {
        try { model.setAgeYears(Double.parseDouble(txtAge.getText())); } catch (Exception ignored) {}
        model.setFunctionalStatus(cmbFunctionalStatus.getValue());
        model.setAsaStatus(cmbAsaStatus.getValue());
        model.setCreatinine(cmbCreatinine.getValue());
        model.setSurgeryType(cmbSurgeryType.getValue());
        model.calc();
    }

    private void applyStyle(double risk) {
        if (Double.isNaN(risk)) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
        else if (risk < 0.05) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
        else if (risk < 0.14) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
        else if (risk < 1.47) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
        else if (risk < 2.60) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        else ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 800; }

    @Override
    public double getDefaultHeight() { return 500; }
}
