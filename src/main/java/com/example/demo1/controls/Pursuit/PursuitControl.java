package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PursuitControl extends BorderPane implements CalculatorControl {

    private final PursuitModel model;

    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkAngina, chkHeartFailure;
    private TextArea txtResult;

    public PursuitControl(PursuitModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст");

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
        // Двустороннее связывание с моделью
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        chkAngina.selectedProperty().bindBidirectional(model.hasAnginaProperty());
        chkHeartFailure.selectedProperty().bindBidirectional(model.hasHeartFailureProperty());

        // Слушатели для автоподсчёта
        txtAge.textProperty().addListener((obs, o, n) -> calculate());
        cmbGender.valueProperty().addListener((obs, o, n) -> calculate());
        chkAngina.selectedProperty().addListener((obs, o, n) -> calculate());
        chkHeartFailure.selectedProperty().addListener((obs, o, n) -> calculate());

        // Обновление текстового поля результата при изменении модели
        model.resultProperty().addListener((obs, oldVal, newVal) -> txtResult.setText(newVal));
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
    public double getDefaultWidth() {
        return 700;
    }
}