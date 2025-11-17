package com.example.demo1.controls.AKI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AKIControl extends BorderPane implements CalculatorControl, AutoCloseable {

    private AKIModel model;

    private TextField txtBaselineCreatinine;
    private TextField txtCurrentCreatinine;
    private TextField txtUrineOutput;
    private TextField txtWeight;
    private TextField txtHours;
    private TextArea txtResult;

    private final ChangeListener<String> baselineChange = (obs, o, n) -> update(() -> model.setBaselineCreatinine(parse(n)));
    private final ChangeListener<String> currentChange  = (obs, o, n) -> update(() -> model.setCurrentCreatinine(parse(n)));
    private final ChangeListener<String> urineChange    = (obs, o, n) -> update(() -> model.setUrineOutput(parse(n)));
    private final ChangeListener<String> weightChange   = (obs, o, n) -> update(() -> model.setWeightKg(parse(n)));
    private final ChangeListener<String> hoursChange    = (obs, o, n) -> update(() -> model.setHours(parse(n)));

    public AKIControl(AKIModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtBaselineCreatinine = new TextField(); txtBaselineCreatinine.setPromptText("Исходный креатинин (мг/дл)");
        txtCurrentCreatinine  = new TextField(); txtCurrentCreatinine.setPromptText("Текущий креатинин (мг/дл)");
        txtUrineOutput        = new TextField(); txtUrineOutput.setPromptText("Объем мочи (мл)");
        txtWeight             = new TextField(); txtWeight.setPromptText("Вес (кг)");
        txtHours              = new TextField(); txtHours.setPromptText("Время наблюдения (ч)");
        txtResult             = new TextArea(); txtResult.setEditable(false);

        VBox form = new VBox(
                10,
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
                        "Использует уровень креатинина и темп диуреза.\n" +
                        "Определяет стадию AKI по критериям KDIGO."
        ));
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void unbind() {
        txtResult.textProperty().unbind();
    }

    private void addListeners() {
        txtBaselineCreatinine.textProperty().addListener(baselineChange);
        txtCurrentCreatinine.textProperty().addListener(currentChange);
        txtUrineOutput.textProperty().addListener(urineChange);
        txtWeight.textProperty().addListener(weightChange);
        txtHours.textProperty().addListener(hoursChange);
    }

    private void removeListeners() {
        txtBaselineCreatinine.textProperty().removeListener(baselineChange);
        txtCurrentCreatinine.textProperty().removeListener(currentChange);
        txtUrineOutput.textProperty().removeListener(urineChange);
        txtWeight.textProperty().removeListener(weightChange);
        txtHours.textProperty().removeListener(hoursChange);
    }

    private double parse(String v) {
        try { return Double.parseDouble(v); }
        catch (Exception ignored) { return 0d; }
    }

    private void update(Runnable r) {
        r.run();
        model.calc();
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }
}
