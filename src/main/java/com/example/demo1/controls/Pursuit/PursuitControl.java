package com.example.demo1.controls.Pursuit;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class PursuitControl extends StackPane implements Closeable {
    private final PursuitModel model;
    private TextField txtAge;
    private CheckBox chkMale, chkAngina, chkHeartFailure;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

    public PursuitControl(PursuitModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        chkMale = new CheckBox("Мужчина");
        chkAngina = new CheckBox("Стенокардия III-IV ФК");
        chkHeartFailure = new CheckBox("Сердечная недостаточность");

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, txtAge, chkMale, chkAngina, chkHeartFailure, btnCalc, txtResult));
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        chkMale.selectedProperty().bindBidirectional(model.isMaleProperty());
        chkAngina.selectedProperty().bindBidirectional(model.hasAnginaProperty());
        chkHeartFailure.selectedProperty().bindBidirectional(model.hasHeartFailureProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        chkMale.selectedProperty().unbindBidirectional(model.isMaleProperty());
        chkAngina.selectedProperty().unbindBidirectional(model.hasAnginaProperty());
        chkHeartFailure.selectedProperty().unbindBidirectional(model.hasHeartFailureProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculateResult() {
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка ввода: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}