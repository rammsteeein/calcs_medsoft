package com.example.demo1.controls.UAS7;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class UAS7Control extends StackPane implements CalculatorControl, AutoCloseable {

    private final UAS7Model model;

    private final ComboBox<String>[] whealsBoxes = new ComboBox[7];
    private final ComboBox<String>[] itchBoxes = new ComboBox[7];

    private final ChangeListener<String> comboListener = (obs, oldVal, newVal) -> calculate();

    private TextArea txtResult;

    private static final String[] WHEALS_OPTIONS = {
            "0 - Нет волдырей",
            "1 - <20 волдырей",
            "2 - 20–50 волдырей",
            "3 - >50 / сливные волдыри"
    };

    private static final String[] ITCH_OPTIONS = {
            "0 - Нет зуда",
            "1 - Легкий зуд",
            "2 - Умеренный зуд",
            "3 - Выраженный зуд"
    };

    public UAS7Control(UAS7Model model) {

        this.model = model;

        initialize();
        bind();
        addListeners();

        calculate();
    }

    private void initialize() {

        TabPane tabPane = new TabPane();

        for (int i = 0; i < 7; i++) {

            VBox dayBox = new VBox(15);

            ComboBox<String> whealsCombo = new ComboBox<>();
            whealsCombo.getItems().addAll(WHEALS_OPTIONS);
            whealsCombo.setValue(WHEALS_OPTIONS[0]);
            whealsCombo.setPrefWidth(320);

            ComboBox<String> itchCombo = new ComboBox<>();
            itchCombo.getItems().addAll(ITCH_OPTIONS);
            itchCombo.setValue(ITCH_OPTIONS[0]);
            itchCombo.setPrefWidth(320);

            whealsBoxes[i] = whealsCombo;
            itchBoxes[i] = itchCombo;

            dayBox.getChildren().addAll(
                    new VBox(5,
                            new Label("Количество волдырей"),
                            whealsCombo
                    ),
                    new VBox(5,
                            new Label("Кожный зуд"),
                            itchCombo
                    )
            );

            dayBox.setStyle("-fx-padding: 15;");

            Tab tab = new Tab("День " + (i + 1), dayBox);
            tab.setClosable(false);

            tabPane.getTabs().add(tab);
        }

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(90);

        VBox leftBox = new VBox(
                15,
                CalculatorHeader.createHeader("UAS7 — Индекс активности крапивницы"),
                tabPane,
                txtResult
        );

        getChildren().add(
                new HBox(
                        20,
                        leftBox,
                        CalculatorDescription.createDescription(
                                "UAS7 — шкала оценки активности хронической крапивницы за 7 дней.\n\n" +

                                        "Каждый день оцениваются:\n" +
                                        "• количество волдырей\n" +
                                        "• выраженность кожного зуда\n\n" +

                                        "Каждый параметр:\n" +
                                        "0–3 балла\n\n" +

                                        "Итоговая сумма за неделю:\n" +
                                        "0–42 балла\n\n" +

                                        "Интерпретация:\n" +
                                        "0 — отсутствие симптомов\n" +
                                        "1–6 — хорошо контролируемая крапивница\n" +
                                        "7–15 — легкая степень\n" +
                                        "16–27 — средняя степень\n" +
                                        "28–42 — тяжелое течение"
                        )
                )
        );
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {

        for (int i = 0; i < 7; i++) {

            whealsBoxes[i].valueProperty().addListener(comboListener);
            itchBoxes[i].valueProperty().addListener(comboListener);
        }
    }

    private void removeListeners() {

        for (int i = 0; i < 7; i++) {

            whealsBoxes[i].valueProperty().removeListener(comboListener);
            itchBoxes[i].valueProperty().removeListener(comboListener);
        }
    }

    private void calculate() {

        for (int i = 0; i < 7; i++) {

            model.whealsProperty(i).set(
                    whealsBoxes[i].getSelectionModel().getSelectedIndex()
            );

            model.itchProperty(i).set(
                    itchBoxes[i].getSelectionModel().getSelectedIndex()
            );
        }

        model.calc();

        int score = model.resultValueProperty().get();

        if (score <= 6) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
        }
        else if (score <= 27) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
        }
        else {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        }
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 650;
    }

    @Override
    public double getDefaultHeight() {
        return 410;
    }
}