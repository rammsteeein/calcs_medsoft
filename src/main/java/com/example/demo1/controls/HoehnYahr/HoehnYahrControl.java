package com.example.demo1.controls.HoehnYahr;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class HoehnYahrControl extends StackPane implements AutoCloseable, CalculatorControl {

    private HoehnYahrModel model;

    private ToggleGroup statusGroup;
    private List<RadioButton> radioButtons;

    private TextArea txtResult;

    private final ChangeListener<Object> statusListener = (o, ov, nv) -> {

        RadioButton selected = (RadioButton) statusGroup.getSelectedToggle();

        if (selected != null) {
            model.statusProperty().set(selected.getText());
            calculate();
        }
    };

    private final ChangeListener<String> resultListener = (o, ov, nv) -> applyStyle();

    public HoehnYahrControl(HoehnYahrModel model) {

        this.model = model;

        initialize();
        bind();
        addListeners();
    }

    private void initialize() {

        statusGroup = new ToggleGroup();
        radioButtons = new ArrayList<>();

        String[] statuses = {

                "Симптомы отсутствуют",

                "Односторонние двигательные нарушения, практически не мешающие самообслуживанию",

                "Двусторонняя симптоматика без нарушения равновесия",

                "Легкая или умеренная симптоматика, есть постуральная неустойчивость",

                "Тяжелая симптоматика, но способен ходить и стоять без поддержки",

                "Без посторонней помощи прикован к инвалидному креслу или постели"
        };

        VBox radioBox = new VBox(5);

        for (String status : statuses) {

            RadioButton rb = new RadioButton(status);
            rb.setToggleGroup(statusGroup);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);

            radioButtons.add(rb);
            radioBox.getChildren().add(rb);
        }

        ScrollPane scrollPane = new ScrollPane(radioBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(120);

        VBox left = new VBox(
                10,
                CalculatorHeader.createHeader("Шкала Хен и Яра"),
                scrollPane,
                txtResult
        );

        getChildren().add(

                new HBox(
                        20,
                        left,
                        CalculatorDescription.createDescription(

                                "Шкала Хен и Яра используется для оценки стадии болезни Паркинсона.\n\n" +

                                        "Стадии:\n\n" +

                                        "0 — симптомы отсутствуют\n" +
                                        "1 — односторонние двигательные нарушения\n" +
                                        "2 — двусторонние симптомы без нарушения равновесия\n" +
                                        "3 — постуральная неустойчивость\n" +
                                        "4 — тяжелая симптоматика, пациент ходит самостоятельно\n" +
                                        "5 — пациент прикован к креслу или постели\n\n" +

                                        "Шкала помогает оценить прогрессирование заболевания и эффективность терапии."
                        )
                )
        );
    }

    private void bind() {

        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultProperty().addListener(resultListener);
    }

    private void addListeners() {

        statusGroup.selectedToggleProperty().addListener(statusListener);
    }

    private void removeListeners() {

        statusGroup.selectedToggleProperty().removeListener(statusListener);
        model.resultProperty().removeListener(resultListener);
    }

    private void unbind() {

        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {

        model.calc();
        applyStyle();
    }

    private void applyStyle() {

        double stage = model.resultValueProperty().get();

        if (stage <= 1) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
        }
        else if (stage >= 4) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        }
        else {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
        }
    }

    @Override
    public void close() {

        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 900;
    }

    @Override
    public double getDefaultHeight() {
        return 500;
    }
}