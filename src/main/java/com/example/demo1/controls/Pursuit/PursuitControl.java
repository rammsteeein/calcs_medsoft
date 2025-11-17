package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class PursuitControl extends BorderPane implements CalculatorControl, Closeable {

    private final PursuitModel model;

    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkAngina, chkHeartFailure;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (obs, oldVal, newVal) -> calculate();
    private final ChangeListener<Gender> genderListener = (obs, oldVal, newVal) -> calculate();
    private final ChangeListener<Boolean> anginaListener = (obs, oldVal, newVal) -> calculate();
    private final ChangeListener<Boolean> heartFailureListener = (obs, oldVal, newVal) -> calculate();
    private final ChangeListener<String> resultListener = (obs, oldVal, newVal) -> txtResult.setText(newVal);

    public PursuitControl(PursuitModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        chkAngina = new CheckBox("Стенокардия III-IV ФК");
        chkHeartFailure = new CheckBox("Сердечная недостаточность");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала Pursuit"),
                txtAge, cmbGender, chkAngina, chkHeartFailure, txtResult
        );

        VBox rightBox = new VBox(
                CalculatorDescription.createDescription(
                        "Шкала Pursuit оценивает риск смертности у пациентов с ОКС без подъема ST.\n\n" +
                                "Интерпретация:\n" +
                                "- <=12 баллов: низкий риск\n" +
                                "- 13-14 баллов: средний риск\n" +
                                "- >14 баллов: высокий риск"
                )
        );

        HBox mainBox = new HBox(20, leftBox, rightBox);
        this.setCenter(mainBox);
        this.setPrefSize(700, 600);
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        chkAngina.selectedProperty().bindBidirectional(model.hasAnginaProperty());
        chkHeartFailure.selectedProperty().bindBidirectional(model.hasHeartFailureProperty());
        model.resultProperty().addListener(resultListener);
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        chkAngina.selectedProperty().unbindBidirectional(model.hasAnginaProperty());
        chkHeartFailure.selectedProperty().unbindBidirectional(model.hasHeartFailureProperty());
        model.resultProperty().removeListener(resultListener);
    }

    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);
        cmbGender.valueProperty().addListener(genderListener);
        chkAngina.selectedProperty().addListener(anginaListener);
        chkHeartFailure.selectedProperty().addListener(heartFailureListener);
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);
        cmbGender.valueProperty().removeListener(genderListener);
        chkAngina.selectedProperty().removeListener(anginaListener);
        chkHeartFailure.selectedProperty().removeListener(heartFailureListener);
    }

    private void calculate() {
        try {
            if (txtAge.getText().isEmpty() || cmbGender.getValue() == null) {
                model.resultProperty().set("Введите все поля");
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
                return;
            }
            model.calc();
            ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 12, 14);
        } catch (Exception e) {
            model.resultProperty().set("Ошибка: " + e.getMessage());
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
        }
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 600;
    }
}
