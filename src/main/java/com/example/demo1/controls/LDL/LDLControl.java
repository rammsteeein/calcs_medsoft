package com.example.demo1.controls.LDL;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class LDLControl extends StackPane implements Closeable, CalculatorControl {
    private final LDLModel model;

    private TextField nmrNonHDL;
    private TextField nmrTG;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

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

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("ХС-ЛНП на основании современной парадигмы метаболизма липидов"),
                nmrNonHDL, nmrTG, btnCalc, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Формула Фридевальда рассчитывает уровень холестерина ЛПНП (LDL) как разницу " +
                                "между общим холестерином, триглицеридами и HDL.\n\n" +
                                "Применимость:\n" +
                                "- Триглицериды < 4.5 ммоль/л\n" +
                                "- При более высоких значениях результат может быть неточным"
                )
        ));
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
        model.calc();
    }

    @Override
    public void close() {
        unbind();
    }
}
