package com.example.demo1.controls.LDL;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class LDLControl extends StackPane implements Closeable {
    private final LDLModel model;
    private TextField nmrNonHDL;
    private TextField nmrTG;
    private Button btnCalc;
    private TextArea txtResult;

    public LDLControl(LDLModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrNonHDL = new TextField();
        nmrNonHDL.setPromptText("ХС-неЛВП");

        nmrTG = new TextField();
        nmrTG.setPromptText("Триглицериды");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10,
                CalculatorHeader.createHeader("ХС-ЛНП на основании современной парадигмы метаболизма липидов"),
                nmrNonHDL, nmrTG, btnCalc, txtResult));
    }

    private void bind() {
        nmrNonHDL.textProperty().bindBidirectional(model.nonHDLProperty());
        nmrTG.textProperty().bindBidirectional(model.tgProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        nmrNonHDL.textProperty().unbindBidirectional(model.nonHDLProperty());
        nmrTG.textProperty().unbindBidirectional(model.tgProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculateResult() {
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}