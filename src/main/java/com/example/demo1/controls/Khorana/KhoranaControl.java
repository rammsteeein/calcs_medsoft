package com.example.demo1.controls.Khorana;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class KhoranaControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final KhoranaModel model;

    private ComboBox<String> cmbTumor;
    private CheckBox cbPlatelets, cbHemoglobin, cbLeukocytes, cbHighBMI;
    private TextArea txtResult;

    private final ChangeListener<String> tumorListener = (obs, oldV, newV) -> calculate();
    private final ChangeListener<Boolean> factorListener = (obs, oldV, newV) -> updateFactors();

    public KhoranaControl(KhoranaModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
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

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала Khorana"),
                cmbTumor, cbPlatelets, cbHemoglobin, cbLeukocytes, cbHighBMI, txtResult
        );

        HBox mainBox = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала Хорана (Khorana) прогнозирует риск развития венозных тромбоэмболических осложнений (ВТЭО) " +
                                "у онкологических пациентов, начинающих курс химиотерапии."
                )
        );

        getChildren().add(mainBox);
    }

    private void bind() {
        cmbTumor.valueProperty().bindBidirectional(model.tumorLocationProperty());
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {
        cmbTumor.valueProperty().addListener(tumorListener);
        cbPlatelets.selectedProperty().addListener(factorListener);
        cbHemoglobin.selectedProperty().addListener(factorListener);
        cbLeukocytes.selectedProperty().addListener(factorListener);
        cbHighBMI.selectedProperty().addListener(factorListener);
    }

    private void removeListeners() {
        cmbTumor.valueProperty().removeListener(tumorListener);
        cbPlatelets.selectedProperty().removeListener(factorListener);
        cbHemoglobin.selectedProperty().removeListener(factorListener);
        cbLeukocytes.selectedProperty().removeListener(factorListener);
        cbHighBMI.selectedProperty().removeListener(factorListener);
    }

    private void updateFactors() {
        model.getPatientFactors().clear();
        if (cbPlatelets.isSelected()) model.getPatientFactors().add("Тромбоциты");
        if (cbHemoglobin.isSelected()) model.getPatientFactors().add("Гемоглобин");
        if (cbLeukocytes.isSelected()) model.getPatientFactors().add("Лейкоциты");
        if (cbHighBMI.isSelected()) model.getPatientFactors().add("ИМТ");

        calculate();
    }

    private void calculate() {
        if (cmbTumor.getValue() == null || cmbTumor.getValue().isEmpty()) {
            model.setResult("Введите локализацию опухоли");
            return;
        }
        model.calc();
    }

    private void unbind() {
        cmbTumor.valueProperty().unbindBidirectional(model.tumorLocationProperty());
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
        txtResult.textProperty().unbind();
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
