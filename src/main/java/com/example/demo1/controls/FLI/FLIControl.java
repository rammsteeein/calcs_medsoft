package com.example.demo1.controls.FLI;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
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
                                "Назначение: Оценка степени стеатоза (количества жира) в печени у пациентов с НАЖБП\n" +
                                        "\n" +
                                        "Расчетная формула: (e0,953 x loge (ТГ) + 0,139 x (ИМТ) + 0,718 x loge (ГГТП) + 0,053 x (ОТ) - 15,745) / (1 + e0,953 x loge (ТГ) + 0,139 x (ИМТ) + 0,718 x loge (ГГТП) + 0,053 x (ОТ) - 15,745) x 100, где ТГ - триглицериды, ИМТ - индекс массы тела, ГГТП - гамма-глутамилтранспептидаза, ОТ - окружность талии.\n" +
                                        "\n" +
                                        "Ключ (интерпретация): Результат < 30 свидетельствует об отсутствии стеатоза печени; от 30 до 59 - \"серая зона\"; >= 60 - предиктор стеатоза (при значении FLI >= 60 вероятность стеатоза > 78%."
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

            String text = model.resultProperty().get();

            double fliValue = -1;
            try {
                if (text.startsWith("FLI:")) {
                    String[] parts = text.split("\\n")[0].split(":");
                    fliValue = Double.parseDouble(parts[1].trim());
                }
            } catch (Exception ignored) {}

            if (fliValue >= 0) {
                ResultStyler.applyStyleForValue(txtResult, fliValue, 30, 60);
            } else {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            }

        } catch (Exception ex) {
            txtResult.setText("Ошибка ввода: " + ex.getMessage());
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
        }
    }

    @Override
    public void close() {
        unbind();
    }
}
