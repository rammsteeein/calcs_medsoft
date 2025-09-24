package com.example.demo1.controls.REACH;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class REACHControl extends StackPane {

    private final REACHModel model;

    private TextField txtAge;
    private CheckBox chkPeripheral;
    private CheckBox chkHF;
    private CheckBox chkDiabetes;
    private CheckBox chkCholesterol;
    private CheckBox chkHypertension;
    private ComboBox<String> cmbSmoking;
    private ComboBox<String> cmbAntiplatelet;
    private CheckBox chkOAC;
    private TextArea txtResult;

    public REACHControl(REACHModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        chkPeripheral = new CheckBox("Периферический атеросклероз");
        chkHF = new CheckBox("Сердечная недостаточность");
        chkDiabetes = new CheckBox("Диабет");
        chkCholesterol = new CheckBox("Гиперхолестеринемия");
        chkHypertension = new CheckBox("Артериальная гипертония");

        cmbSmoking = new ComboBox<>();
        cmbSmoking.getItems().addAll("Никогда", "Раньше", "Продолжает");

        cmbAntiplatelet = new ComboBox<>();
        cmbAntiplatelet.getItems().addAll("Нет", "Аспирин", "Другие", "Комбинация");

        chkOAC = new CheckBox("Прием оральных антикоагулянтов");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        this.getChildren().add(new VBox(10,
                CalculatorHeader.createHeader("Индекс REACH"),
                txtAge, chkPeripheral, chkHF, chkDiabetes,
                chkCholesterol, chkHypertension, cmbSmoking, cmbAntiplatelet, chkOAC, txtResult));
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());

        chkPeripheral.selectedProperty().bindBidirectional(model.peripheralAtherosclerosisProperty());
        chkHF.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkDiabetes.selectedProperty().bindBidirectional(model.diabetesProperty());
        chkCholesterol.selectedProperty().bindBidirectional(model.hypercholesterolemiaProperty());
        chkHypertension.selectedProperty().bindBidirectional(model.hypertensionProperty());
        chkOAC.selectedProperty().bindBidirectional(model.oralAnticoagulantProperty());

        cmbSmoking.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            model.setSmokingStatus(newVal.intValue());
            model.calc();
        });

        cmbAntiplatelet.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            model.setAntiplatelet(newVal.intValue());
            model.calc();
        });

        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        txtAge.textProperty().addListener(recalcListener);
        chkPeripheral.selectedProperty().addListener(recalcListener);
        chkHF.selectedProperty().addListener(recalcListener);
        chkDiabetes.selectedProperty().addListener(recalcListener);
        chkCholesterol.selectedProperty().addListener(recalcListener);
        chkHypertension.selectedProperty().addListener(recalcListener);
        chkOAC.selectedProperty().addListener(recalcListener);

        txtResult.textProperty().bind(model.resultProperty());
    }
}