package com.example.demo1.controls.FIB4;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import com.example.demo1.common.services.ResultStyler;

import java.io.Closeable;

public class FIB4Control extends StackPane implements Closeable, CalculatorControl {
    private final FIB4Model model;

    private TextField txtAge, txtAst, txtAlt, txtPlatelets;
    private TextArea txtResult;

    public FIB4Control(FIB4Model model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtAst = new TextField();
        txtAst.setPromptText("АСТ (Ед/л)");

        txtAlt = new TextField();
        txtAlt.setPromptText("АЛТ (Ед/л)");

        txtPlatelets = new TextField();
        txtPlatelets.setPromptText("Тромбоциты (10^9/л)");

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        txtAst.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        txtAlt.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        txtPlatelets.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс FIB-4"),
                txtAge, txtAst, txtAlt, txtPlatelets, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "FIB-4 используется для неинвазивной оценки фиброза печени.\n\n" +
                                "Формула:\n" +
                                "  FIB-4 = (Возраст × AST) / (Тромбоциты × √ALT)\n\n" +
                                "Клиническое применение:\n" +
                                "- Пациенты с хроническими гепатитами\n" +
                                "- Неалкогольная жировая болезнь печени (НАЖБП)\n\n" +
                                "Интерпретация:\n" +
                                "- Низкие значения: низкий риск значимого фиброза\n" +
                                "- Высокие значения: подозрение на выраженный фиброз"
                )
        ));
    }

    private void tryAutoCalc() {
        if (model == null) {
            return;
        }
        if (txtAge.getText().isEmpty() ||
                txtAlt.getText().isEmpty() ||
                txtAst.getText().isEmpty() ||
                txtPlatelets.getText().isEmpty()) {
            txtResult.setText("Введите все поля для расчёта");
            return;
        }

        try {
            Double.parseDouble(txtAlt.getText());
            Integer.parseInt(txtAge.getText());
            Double.parseDouble(txtPlatelets.getText());
            Double.parseDouble(txtAst.getText());
        } catch (NumberFormatException ex) {
            txtResult.setText("Некорректный ввод чисел");
            return;
        }
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        txtAst.textProperty().bindBidirectional(model.astProperty());
        txtAlt.textProperty().bindBidirectional(model.altProperty());
        txtPlatelets.textProperty().bindBidirectional(model.plateletsProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        txtAst.textProperty().unbindBidirectional(model.astProperty());
        txtAlt.textProperty().unbindBidirectional(model.altProperty());
        txtPlatelets.textProperty().unbindBidirectional(model.plateletsProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();

        double val = model.resultValueProperty().get(); 
        ResultStyler.applyStyleForValue(txtResult, val, 1.45, 3.25);
    }

    @Override
    public void close() {
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 430;
    }
}
