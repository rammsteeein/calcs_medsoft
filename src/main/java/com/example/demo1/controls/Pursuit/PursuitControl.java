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

        model.setOnResultStyled(res -> {
            if (res.getScore() <= 12) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
            else if (res.getScore() <= 14) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            else ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        });
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        chkAngina = new CheckBox("Стенокардия III-IV ФК");
        chkHeartFailure = new CheckBox("Сердечная недостаточность");

        txtAge.textProperty().addListener((obs, oldV, newV) -> tryAutoCalc());
        cmbGender.valueProperty().addListener((obs, oldV, newV) -> tryAutoCalc());
        chkAngina.selectedProperty().addListener((obs, oldV, newV) -> tryAutoCalc());
        chkHeartFailure.selectedProperty().addListener((obs, oldV, newV) -> tryAutoCalc());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала Pursuit (оценка риска при ОКС без подъема ST)"),
                txtAge, cmbGender, chkAngina, chkHeartFailure, txtResult
        );

        VBox rightBox = new VBox(
                CalculatorDescription.createDescription(
                        "Шкала Pursuit оценивает риск смертности у пациентов с ОКС без подъема ST.\n\n" +
                                "Интерпретация:\n" +
                                "- <=12 баллов: низкий риск (<10% смертности)\n" +
                                "- 13-14 баллов: средний риск (10-19%)\n" +
                                "- >14 баллов: высокий риск (>19%)\n\n" +
                                "Примечание: суммируются баллы по всем критериям для определения категории риска."
                )
        );

        HBox mainBox = new HBox(20, leftBox, rightBox);
        this.setCenter(mainBox);
        this.setPrefSize(700, 600);
    }

    private void tryAutoCalc() {
        if (txtAge.getText().isEmpty() || cmbGender.getValue() == null) {
            model.resultProperty().set("Введите все поля");
            return;
        }

        try {
            Integer.parseInt(txtAge.getText());
            model.calc();
        } catch (NumberFormatException e) {
            model.resultProperty().set("Некорректный ввод чисел");
        } catch (Exception e) {
            model.resultProperty().set("Ошибка: " + e.getMessage());
        }
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        chkAngina.selectedProperty().bindBidirectional(model.hasAnginaProperty());
        chkHeartFailure.selectedProperty().bindBidirectional(model.hasHeartFailureProperty());
        txtResult.textProperty().bind(model.resultProperty());
    }

    @Override
    public double getDefaultWidth() {
        return 600;
    }
}
