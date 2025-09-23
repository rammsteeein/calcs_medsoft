package com.example.demo1.controls.Friedwald;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class FriedwaldControl extends StackPane implements Closeable {
    private final POAKModel model;
    private TextField nmrKreatinin;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

    public FriedwaldControl(POAKModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Клиренс креатинина");

        btnCalc = new Button();
        btnCalc.setText(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, nmrKreatinin, btnCalc, txtResult));
    }

    private void bind() {
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
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
