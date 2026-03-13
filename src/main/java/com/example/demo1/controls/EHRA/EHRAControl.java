package com.example.demo1.controls.EHRA;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class EHRAControl extends StackPane implements AutoCloseable, CalculatorControl {

    private EHRAModel model;

    private ToggleGroup ehraGroup;
    private ToggleGroup mehraGroup;

    private List<RadioButton> radioButtons = new ArrayList<>();

    private TextArea txtResult;

    private final ChangeListener<Object> ehraListener = (o, ov, nv) -> {

        RadioButton selected = (RadioButton) ehraGroup.getSelectedToggle();

        if (selected != null) {
            model.statusProperty().set(selected.getText());
            calculate();
        }
    };

    private final ChangeListener<Object> mehraListener = (o, ov, nv) -> {

        RadioButton selected = (RadioButton) mehraGroup.getSelectedToggle();

        if (selected != null) {
            model.statusProperty().set(selected.getText());
            calculate();
        }
    };

    public EHRAControl(EHRAModel model) {

        this.model = model;

        initialize();
        bind();
        addListeners();
    }

    private void initialize() {

        ehraGroup = new ToggleGroup();
        mehraGroup = new ToggleGroup();

        VBox ehraBox = new VBox(8);

        String[] ehraStatuses = {
                "Симптомов нет",
                "Легкая симптоматика, обычная повседневная активность не нарушена",
                "Тяжелая симптоматика, обычная повседневная активность затруднена",
                "Инвалидизирующая симптоматика, обычная повседневная активность невозможна"
        };

        for (String status : ehraStatuses) {

            RadioButton rb = new RadioButton(status);
            rb.setToggleGroup(ehraGroup);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);

            radioButtons.add(rb);
            ehraBox.getChildren().add(rb);
        }

        VBox mehraBox = new VBox(8);

        String[] mehraStatuses = {
                "Симптомов нет",
                "Лёгкая симптоматика, симптомы не влияют на активность и не вызывают беспокойства",
                "Умеренная симптоматика, симптомы не влияют на активность, но вызывают беспокойство",
                "Тяжёлая симптоматика, обычная повседневная активность затруднена",
                "Инвалидизирующая симптоматика, нормальная повседневная активность невозможна"
        };

        for (String status : mehraStatuses) {

            RadioButton rb = new RadioButton(status);
            rb.setToggleGroup(mehraGroup);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);

            radioButtons.add(rb);
            mehraBox.getChildren().add(rb);
        }

        TabPane tabPane = new TabPane();

        Tab ehraTab = new Tab("EHRA", ehraBox);
        ehraTab.setClosable(false);

        Tab mehraTab = new Tab("mEHRA", mehraBox);
        mehraTab.setClosable(false);

        tabPane.getTabs().addAll(ehraTab, mehraTab);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(120);

        VBox left = new VBox(
                10,
                CalculatorHeader.createHeader("Шкала EHRA / mEHRA"),
                tabPane,
                txtResult
        );

        getChildren().add(

                new HBox(
                        20,
                        left,
                        CalculatorDescription.createDescription(

                                "Шкала EHRA используется для оценки выраженности симптомов при фибрилляции предсердий.\n\n" +

                                        "EHRA:\n" +
                                        "I — симптомов нет\n" +
                                        "II — легкая симптоматика\n" +
                                        "III — тяжелая симптоматика\n" +
                                        "IV — инвалидизирующая симптоматика\n\n" +

                                        "mEHRA — модифицированная версия шкалы.\n" +
                                        "В ней класс II разделён на:\n" +
                                        "IIa — симптомы не вызывают беспокойства\n" +
                                        "IIb — симптомы вызывают беспокойство пациента."
                        )
                )
        );
    }

    private void bind() {

        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void addListeners() {

        ehraGroup.selectedToggleProperty().addListener(ehraListener);
        mehraGroup.selectedToggleProperty().addListener(mehraListener);
    }

    private void removeListeners() {

        ehraGroup.selectedToggleProperty().removeListener(ehraListener);
        mehraGroup.selectedToggleProperty().removeListener(mehraListener);
    }

    private void unbind() {

        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {

        model.calc();
    }

    @Override
    public void close() {

        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 420;
    }
}