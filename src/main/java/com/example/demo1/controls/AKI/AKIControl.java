package com.example.demo1.controls.AKI;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AKIControl extends StackPane {

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
        txtUrineOutput = new TextField(); txtUrineOutput.setPromptText("Диурез (мл/кг/ч)");
        txtWeight = new TextField(); txtWeight.setPromptText("Вес (кг)");
        txtHours = new TextField(); txtHours.setPromptText("Часы наблюдения");
        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10,
                CalculatorHeader.createHeader("Алгоритм оценки острого повреждения почек"),
                txtBaselineCreatinine,
                txtCurrentCreatinine,
                txtUrineOutput,
                txtWeight,
                txtHours,
                txtResult));
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
