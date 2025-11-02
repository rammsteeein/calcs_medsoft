package com.example.demo1.controls.FLI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class FLIControl extends StackPane implements Closeable, CalculatorControl {
    private final FLIModel model;

    private TextField nmrTriglycerides;
    private TextField nmrBMI;
    private TextField nmrGGT;
    private TextField nmrWaistCircumference;
    private TextArea txtResult;

    public FLIControl(FLIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrTriglycerides = new TextField(); nmrTriglycerides.setPromptText("Триглицериды (ммоль/л)");
        nmrBMI = new TextField(); nmrBMI.setPromptText("Индекс массы тела");
        nmrGGT = new TextField(); nmrGGT.setPromptText("ГГТП (Ед/л)");
        nmrWaistCircumference = new TextField(); nmrWaistCircumference.setPromptText("Окружность талии (см)");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс стеатоза печени FLI"),
                nmrTriglycerides, nmrBMI, nmrGGT, nmrWaistCircumference, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Назначение: Оценка степени стеатоза (жировой инфильтрации) печени.\n" +
                                "Формула: (e^(0.953*ln(ТГ) + 0.139*ИМТ + 0.718*ln(ГГТП) + 0.053*ОТ - 15.745)) /\n" +
                                "(1 + e^(0.953*ln(ТГ) + 0.139*ИМТ + 0.718*ln(ГГТП) + 0.053*ОТ - 15.745)) * 100\n" +
                                "Интерпретация:\n<30 — стеатоза нет\n30–59 — «серая зона»\n≥60 — высокий риск стеатоза"
                )
        ));
    }

    private void bind() {
        nmrTriglycerides.textProperty().bindBidirectional(model.triglyceridesProperty());
        nmrBMI.textProperty().bindBidirectional(model.bmiProperty());
        nmrGGT.textProperty().bindBidirectional(model.ggtProperty());
        nmrWaistCircumference.textProperty().bindBidirectional(model.waistCircumferenceProperty());

        nmrTriglycerides.textProperty().addListener((obs, o, n) -> calculate());
        nmrBMI.textProperty().addListener((obs, o, n) -> calculate());
        nmrGGT.textProperty().addListener((obs, o, n) -> calculate());
        nmrWaistCircumference.textProperty().addListener((obs, o, n) -> calculate());

        model.resultProperty().addListener((obs, oldVal, newVal) -> txtResult.setText(newVal));
    }

    private void calculate() {
        model.calc();
        double val = model.resultValueProperty().get();
        ResultStyler.applyStyleForValue(txtResult, val, 30, 60);
    }

    private void unbind() {
        nmrTriglycerides.textProperty().unbindBidirectional(model.triglyceridesProperty());
        nmrBMI.textProperty().unbindBidirectional(model.bmiProperty());
        nmrGGT.textProperty().unbindBidirectional(model.ggtProperty());
        nmrWaistCircumference.textProperty().unbindBidirectional(model.waistCircumferenceProperty());
    }

    @Override
    public void close() {
        unbind();
    }

    @Override
    public double getDefaultHeight() {
        return 350;
    }
    @Override
    public double getDefaultWidth() {
        return 500;
    }
}
