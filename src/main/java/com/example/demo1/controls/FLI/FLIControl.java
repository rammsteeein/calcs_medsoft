package com.example.demo1.controls.FLI;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class FLIControl extends StackPane implements Closeable {
    private final FLIModel model;

    private TextField nmrTriglycerides;
    private TextField nmrBMI;
    private TextField nmrGGT;
    private TextField nmrWaistCircumference;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

    public FLIControl(FLIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrTriglycerides = new TextField();
        nmrTriglycerides.setPromptText("Триглицериды (ммоль/л)");

        nmrBMI = new TextField();
        nmrBMI.setPromptText("Индекс массы тела");

        nmrGGT = new TextField();
        nmrGGT.setPromptText("ГГТП (Ед/л)");

        nmrWaistCircumference = new TextField();
        nmrWaistCircumference.setPromptText("Окружность талии (см)");

        btnCalc = new Button();
        btnCalc.setText(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, nmrTriglycerides, nmrBMI, nmrGGT, nmrWaistCircumference, btnCalc, txtResult));
    }

    private void bind() {
        nmrTriglycerides.textProperty().bindBidirectional(model.triglyceridesProperty());
        nmrBMI.textProperty().bindBidirectional(model.bmiProperty());
        nmrGGT.textProperty().bindBidirectional(model.ggtProperty());
        nmrWaistCircumference.textProperty().bindBidirectional(model.waistCircumferenceProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        nmrTriglycerides.textProperty().unbindBidirectional(model.triglyceridesProperty());
        nmrBMI.textProperty().unbindBidirectional(model.bmiProperty());
        nmrGGT.textProperty().unbindBidirectional(model.ggtProperty());
        nmrWaistCircumference.textProperty().unbindBidirectional(model.waistCircumferenceProperty());
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
