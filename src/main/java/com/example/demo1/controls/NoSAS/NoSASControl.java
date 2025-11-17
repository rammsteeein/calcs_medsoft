package com.example.demo1.controls.NoSAS;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class NoSASControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final NoSASModel model;

    private TextField txtNeck;
    private TextField txtBmi;
    private CheckBox chkSnoring;
    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private TextArea txtResult;

    private final List<ChangeListener<?>> listeners = new ArrayList<>();

    public NoSASControl(NoSASModel model) {
        this.model = model;
        initialize();
        addListeners();
    }

    private void initialize() {
        txtNeck = new TextField(); txtNeck.setPromptText("Окружность шеи (см)");
        txtBmi = new TextField(); txtBmi.setPromptText("ИМТ");
        chkSnoring = new CheckBox("Храп");
        txtAge = new TextField(); txtAge.setPromptText("Возраст");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");
        cmbGender.setValue(model.getGender());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала NoSAS"),
                txtNeck, txtBmi, chkSnoring, txtAge, cmbGender, txtResult
        );

        HBox container = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала NoSAS используется для оценки риска обструктивного апноэ сна.\n\n" +
                                "Критерии:\n" +
                                "- Окружность шеи ≥ 40 см: 4 балла\n" +
                                "- ИМТ ≥ 30: 5 баллов, 25–29.9: 3 балла\n" +
                                "- Наличие храпа: 2 балла\n" +
                                "- Возраст > 55 лет: 4 балла\n" +
                                "- Мужской пол: 2 балла\n\n" +
                                "Интерпретация:\n" +
                                "- ≥8 баллов: высокий риск\n" +
                                "- <8 баллов: низкий риск"
                )
        );

        getChildren().add(container);

        bind();
    }

    private void bind() {
        chkSnoring.selectedProperty().bindBidirectional(model.hasSnoringProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        txtResult.textProperty().bind(model.resultProperty());

        model.resultProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isEmpty()) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
                return;
            }
            if (newVal.contains("Высокий риск")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
            } else if (newVal.contains("Низкий риск")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
            } else {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            }
        });
    }

    private void addListeners() {
        ChangeListener<String> neckListener = (obs, oldVal, newVal) -> tryAutoCalc();
        txtNeck.textProperty().addListener(neckListener);
        listeners.add(neckListener);

        ChangeListener<String> bmiListener = (obs, oldVal, newVal) -> tryAutoCalc();
        txtBmi.textProperty().addListener(bmiListener);
        listeners.add(bmiListener);

        ChangeListener<String> ageListener = (obs, oldVal, newVal) -> tryAutoCalc();
        txtAge.textProperty().addListener(ageListener);
        listeners.add(ageListener);

        ChangeListener<Gender> genderListener = (obs, oldVal, newVal) -> tryAutoCalc();
        cmbGender.valueProperty().addListener(genderListener);
        listeners.add(genderListener);

        ChangeListener<Boolean> snoringListener = (obs, oldVal, newVal) -> tryAutoCalc();
        chkSnoring.selectedProperty().addListener(snoringListener);
        listeners.add(snoringListener);
    }

    private void removeListeners() {
        txtNeck.textProperty().removeListener((ChangeListener<? super String>) listeners.get(0));
        txtBmi.textProperty().removeListener((ChangeListener<? super String>) listeners.get(1));
        txtAge.textProperty().removeListener((ChangeListener<? super String>) listeners.get(2));
        cmbGender.valueProperty().removeListener((ChangeListener<? super Gender>) listeners.get(3));
        chkSnoring.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(4));
        listeners.clear();
    }

    private void tryAutoCalc() {
        if (txtNeck.getText().isEmpty() || txtBmi.getText().isEmpty() || txtAge.getText().isEmpty() || cmbGender.getValue() == null) {
            model.setResult("Введите все поля для расчёта");
            return;
        }

        try {
            model.setNeckCircumference(Double.parseDouble(txtNeck.getText()));
            model.setBmi(Double.parseDouble(txtBmi.getText()));
            model.setAge(Integer.parseInt(txtAge.getText()));
            model.calc();
        } catch (NumberFormatException e) {
            model.setResult("Некорректный ввод чисел");
        } catch (Exception e) {
            model.setResult("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        removeListeners();
    }
}
