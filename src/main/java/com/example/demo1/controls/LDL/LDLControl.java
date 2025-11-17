package com.example.demo1.controls.LDL;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class LDLControl extends StackPane implements Closeable, CalculatorControl {

    private final LDLModel model;

    private TextField nmrNonHDL;
    private TextField nmrTG;
    private TextArea txtResult;

    private final ChangeListener<String> inputListener = (obs, oldV, newV) -> calculate();

    public LDLControl(LDLModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        nmrNonHDL = new TextField();
        nmrNonHDL.setPromptText("ХС-неЛВП");

        nmrTG = new TextField();
        nmrTG.setPromptText("Триглицериды");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("ХС-ЛНП на основании современной парадигмы метаболизма липидов"),
                nmrNonHDL, nmrTG, txtResult
        );

        HBox mainBox = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Формула Фридевальда рассчитывает уровень холестерина ЛПНП (LDL) как разницу " +
                                "между общим холестерином, триглицеридами и HDL.\n\n" +
                                "Применимость:\n" +
                                "- Триглицериды < 4.5 ммоль/л\n" +
                                "- При более высоких значениях результат может быть неточным"
                )
        );

        getChildren().add(mainBox);
    }

    private void bind() {
        nmrNonHDL.textProperty().bindBidirectional(model.nonHDLProperty());
        nmrTG.textProperty().bindBidirectional(model.tgProperty());
        model.resultProperty().addListener((obs, oldVal, newVal) -> txtResult.setText(newVal));
    }

    private void addListeners() {
        nmrNonHDL.textProperty().addListener(inputListener);
        nmrTG.textProperty().addListener(inputListener);
    }

    private void removeListeners() {
        nmrNonHDL.textProperty().removeListener(inputListener);
        nmrTG.textProperty().removeListener(inputListener);
    }

    private void calculate() {
        if (nmrNonHDL.getText().isEmpty() || nmrTG.getText().isEmpty()) {
            model.setResult("Введите все поля для расчёта");
            return;
        }

        try {
            Double.parseDouble(nmrNonHDL.getText());
            Double.parseDouble(nmrTG.getText());
            model.calc();
        } catch (NumberFormatException e) {
            model.setResult("Некорректный ввод чисел");
        } catch (Exception e) {
            model.setResult("Ошибка: " + e.getMessage());
        }
    }

    private void unbind() {
        nmrNonHDL.textProperty().unbindBidirectional(model.nonHDLProperty());
        nmrTG.textProperty().unbindBidirectional(model.tgProperty());
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }
}
