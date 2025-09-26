package com.example.demo1.controls.NoSAS;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NoSASControl extends StackPane {

    private final NoSASModel model;

    private TextField txtNeck;
    private TextField txtBmi;
    private CheckBox chkSnoring;
    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private Button btnCalc;
    private TextArea txtResult;

    public NoSASControl(NoSASModel model) {
        this.model = model;
        initialize();
        bind();
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

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> model.calc());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала NoSAS"),
                txtNeck, txtBmi, chkSnoring, txtAge, cmbGender, btnCalc, txtResult
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
    }

    private void bind() {
        txtNeck.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setNeckCircumference(Double.parseDouble(newVal)); } catch (Exception ignored) {}
        });
        txtBmi.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setBmi(Double.parseDouble(newVal)); } catch (Exception ignored) {}
        });
        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        chkSnoring.selectedProperty().bindBidirectional(model.hasSnoringProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());

        // Результат обновляется автоматически при пересчете
        txtResult.textProperty().bind(model.resultProperty());
    }
}
