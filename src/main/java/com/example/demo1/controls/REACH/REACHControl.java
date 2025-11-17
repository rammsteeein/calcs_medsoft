package com.example.demo1.controls.REACH;

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

public class REACHControl extends StackPane implements CalculatorControl, Closeable {

    private REACHModel model;

    private TextField txtAge;
    private CheckBox chkPeripheral, chkHF, chkDiabetes, chkCholesterol, chkHypertension, chkOAC;
    private ComboBox<String> cmbSmoking, cmbAntiplatelet;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> peripheralListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> hfListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> diabetesListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> cholesterolListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> hypertensionListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> oacListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Number> smokingListener = (obs, oldVal, newVal) -> {
        model.smokingStatusProperty().set(newVal.intValue());
        model.calc();
    };
    private final ChangeListener<Number> antiplateletListener = (obs, oldVal, newVal) -> {
        model.antiplateletProperty().set(newVal.intValue());
        model.calc();
    };

    public REACHControl(REACHModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
        model.setOnResultStyled(res -> {
            if (res.getScore() <= 6) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
            else if (res.getScore() <= 10) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            else ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        });
        model.calc();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        chkPeripheral = new CheckBox("Периферический атеросклероз");
        chkHF = new CheckBox("Сердечная недостаточность");
        chkDiabetes = new CheckBox("Диабет");
        chkCholesterol = new CheckBox("Гиперхолестеринемия");
        chkHypertension = new CheckBox("Артериальная гипертония");
        chkOAC = new CheckBox("Прием оральных антикоагулянтов");

        cmbSmoking = new ComboBox<>();
        cmbSmoking.getItems().addAll("Никогда", "Раньше", "Продолжает");
        cmbSmoking.getSelectionModel().select(0);

        cmbAntiplatelet = new ComboBox<>();
        cmbAntiplatelet.getItems().addAll("Нет", "Аспирин", "Другие", "Комбинация");
        cmbAntiplatelet.getSelectionModel().select(0);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала REACH"),
                txtAge,
                chkPeripheral,
                chkHF,
                chkDiabetes,
                chkCholesterol,
                chkHypertension,
                new VBox(new Label("Курение"), cmbSmoking),
                new VBox(new Label("Прием антиагрегантов"), cmbAntiplatelet),
                chkOAC,
                txtResult
        );

        VBox rightBox = new VBox(
                CalculatorDescription.createDescription(
                        "Шкала REACH для оценки риска кровотечений у пациентов с атеросклерозом.\n" +
                                "Позволяет прогнозировать риск и корректировать терапию."
                )
        );

        this.getChildren().add(new HBox(20, leftBox, rightBox));
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        chkPeripheral.selectedProperty().bindBidirectional(model.peripheralAtherosclerosisProperty());
        chkHF.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkDiabetes.selectedProperty().bindBidirectional(model.diabetesProperty());
        chkCholesterol.selectedProperty().bindBidirectional(model.hypercholesterolemiaProperty());
        chkHypertension.selectedProperty().bindBidirectional(model.hypertensionProperty());
        chkOAC.selectedProperty().bindBidirectional(model.oralAnticoagulantProperty());
        cmbSmoking.getSelectionModel().selectedIndexProperty().addListener(smokingListener);
        cmbAntiplatelet.getSelectionModel().selectedIndexProperty().addListener(antiplateletListener);
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        chkPeripheral.selectedProperty().unbindBidirectional(model.peripheralAtherosclerosisProperty());
        chkHF.selectedProperty().unbindBidirectional(model.heartFailureProperty());
        chkDiabetes.selectedProperty().unbindBidirectional(model.diabetesProperty());
        chkCholesterol.selectedProperty().unbindBidirectional(model.hypercholesterolemiaProperty());
        chkHypertension.selectedProperty().unbindBidirectional(model.hypertensionProperty());
        chkOAC.selectedProperty().unbindBidirectional(model.oralAnticoagulantProperty());
        txtResult.textProperty().unbind();
    }

    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);
        chkPeripheral.selectedProperty().addListener(peripheralListener);
        chkHF.selectedProperty().addListener(hfListener);
        chkDiabetes.selectedProperty().addListener(diabetesListener);
        chkCholesterol.selectedProperty().addListener(cholesterolListener);
        chkHypertension.selectedProperty().addListener(hypertensionListener);
        chkOAC.selectedProperty().addListener(oacListener);
        cmbSmoking.getSelectionModel().selectedIndexProperty().addListener(smokingListener);
        cmbAntiplatelet.getSelectionModel().selectedIndexProperty().addListener(antiplateletListener);
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);
        chkPeripheral.selectedProperty().removeListener(peripheralListener);
        chkHF.selectedProperty().removeListener(hfListener);
        chkDiabetes.selectedProperty().removeListener(diabetesListener);
        chkCholesterol.selectedProperty().removeListener(cholesterolListener);
        chkHypertension.selectedProperty().removeListener(hypertensionListener);
        chkOAC.selectedProperty().removeListener(oacListener);
        cmbSmoking.getSelectionModel().selectedIndexProperty().removeListener(smokingListener);
        cmbAntiplatelet.getSelectionModel().selectedIndexProperty().removeListener(antiplateletListener);
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 800;
    }

    @Override
    public double getDefaultHeight() {
        return 500;
    }
}
