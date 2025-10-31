package com.example.demo1.controls.AKI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AKIControl extends BorderPane implements CalculatorControl {

    private final AKIModel model;

    private TextField txtBaselineCreatinine;
    private TextField txtCurrentCreatinine;
    private TextField txtUrineOutput;
    private TextField txtWeight;
    private TextField txtHours;
    private TextArea txtResult;

    public AKIControl(AKIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtBaselineCreatinine = new TextField(); txtBaselineCreatinine.setPromptText("Исходный креатинин (мг/дл)");
        txtCurrentCreatinine = new TextField(); txtCurrentCreatinine.setPromptText("Текущий креатинин (мг/дл)");
        txtUrineOutput = new TextField(); txtUrineOutput.setPromptText("Объем мочи (мл)");
        txtWeight = new TextField(); txtWeight.setPromptText("Вес (кг)");
        txtHours = new TextField(); txtHours.setPromptText("Время наблюдения (ч)");
        txtResult = new TextArea(); txtResult.setEditable(false);

        VBox form = new VBox(10,
                CalculatorHeader.createHeader("Алгоритм оценки острого повреждения почек"),
                txtBaselineCreatinine,
                txtCurrentCreatinine,
                txtUrineOutput,
                txtWeight,
                txtHours,
                txtResult
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);

        setCenter(scrollPane);
        setRight(CalculatorDescription.createDescription(
                "Калькулятор для оценки степени острого повреждения почек (AKI).\n" +
                        "Используется уровень креатинина и темп диуреза.\n" +
                        "Помогает клиницисту классифицировать стадию AKI по критериям KDIGO."
        ));
    }

    private void bind() {
        txtBaselineCreatinine.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setBaselineCreatinine(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtCurrentCreatinine.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setCurrentCreatinine(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtUrineOutput.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setUrineOutput(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtWeight.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setWeightKg(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtHours.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setHours(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
