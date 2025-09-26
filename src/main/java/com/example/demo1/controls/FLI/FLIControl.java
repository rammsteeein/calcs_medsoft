package com.example.demo1.controls.FLI;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс стеатоза печени FLI"),
                nmrTriglycerides, nmrBMI, nmrGGT, nmrWaistCircumference,
                btnCalc, txtResult
        );

        getChildren().add(
                new HBox(
                        20,
                        leftBox,
                        CalculatorDescription.createDescription(
                                "FLI (Fatty Liver Index) оценивает вероятность жировой болезни печени.\n\n" +
                                        "Формула:\n" +
                                        "FLI = [ e^(0.953 * ln(триглицериды) + 0.139 * ИМТ + 0.718 * ln(GGT) + 0.053 * окружность талии - 15.745) ] /\n" +
                                        "[ 1 + e^(0.953 * ln(триглицериды) + 0.139 * ИМТ + 0.718 * ln(GGT) + 0.053 * окружность талии - 15.745) ] × 100\n\n" +
                                        "Интерпретация:\n" +
                                        "- FLI < 30: низкая вероятность стеатоза\n" +
                                        "- 30 ≤ FLI ≤ 59: промежуточная вероятность\n" +
                                        "- FLI ≥ 60: высокая вероятность\n\n" +
                                        "Примечание: неинвазивный скрининг для выявления стеатоза печени."
                        )
                )
        );
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
