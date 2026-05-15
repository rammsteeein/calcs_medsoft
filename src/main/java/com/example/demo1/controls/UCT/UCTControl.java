package com.example.demo1.controls.UCT;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class UCTControl extends StackPane
        implements AutoCloseable, CalculatorControl {

    private final UCTModel model;

    private ComboBox<String> cmbQ1;
    private ComboBox<String> cmbQ2;
    private ComboBox<String> cmbQ3;
    private ComboBox<String> cmbQ4;

    private TextArea txtResult;

    public UCTControl(UCTModel model) {

        this.model = model;

        initialize();
        bind();
    }

    private void initialize() {

        Label lblTitle = new Label(
                "Тест контроля крапивницы (UCT)"
        );

        lblTitle.setStyle(
                "-fx-font-weight: bold; " +
                        "-fx-font-size: 14px;"
        );

        cmbQ1 = new ComboBox<>();
        cmbQ1.getItems().addAll(
                "Очень сильно",
                "Сильно",
                "Достаточно",
                "Немного",
                "Не беспокоили"
        );

        cmbQ2 = new ComboBox<>();
        cmbQ2.getItems().addAll(
                "Очень сильно",
                "Сильно",
                "Достаточно",
                "Немного",
                "Не ухудшила"
        );

        cmbQ3 = new ComboBox<>();
        cmbQ3.getItems().addAll(
                "Очень часто",
                "Часто",
                "Иногда",
                "Редко",
                "Ни разу"
        );

        cmbQ4 = new ComboBox<>();
        cmbQ4.getItems().addAll(
                "Не удавалось",
                "Немного",
                "Достаточно",
                "Хорошо",
                "Очень хорошо"
        );

        VBox questions = new VBox(
                15,

                new VBox(
                        5,
                        new Label(
                                "1. Насколько сильно Вас беспокоили " +
                                        "проявления крапивницы?"
                        ),
                        cmbQ1
                ),

                new VBox(
                        5,
                        new Label(
                                "2. Насколько сильно крапивница " +
                                        "ухудшила качество жизни?"
                        ),
                        cmbQ2
                ),

                new VBox(
                        5,
                        new Label(
                                "3. Как часто лечение было " +
                                        "недостаточным?"
                        ),
                        cmbQ3
                ),

                new VBox(
                        5,
                        new Label(
                                "4. Насколько успешно удавалось " +
                                        "контролировать крапивницу?"
                        ),
                        cmbQ4
                )
        );

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(100);

        VBox content = new VBox(
                15,
                lblTitle,
                questions,
                new Separator(),
                txtResult
        );

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        getChildren().add(
                new HBox(
                        20,

                        new VBox(
                                10,
                                CalculatorHeader.createHeader(
                                        "UCT — Urticaria Control Test"
                                ),
                                scrollPane
                        ),

                        CalculatorDescription.createDescription(
                                "Тест контроля крапивницы (UCT) — " +
                                        "опросник для оценки контроля " +
                                        "хронической и индуцированной " +
                                        "крапивницы за последние 4 недели.\n\n" +

                                        "Каждый вопрос оценивается " +
                                        "от 0 до 4 баллов.\n\n" +

                                        "Максимальный балл: 16\n" +
                                        "Порог контроля: 12 баллов\n\n" +

                                        "Интерпретация:\n" +
                                        "• 12–16 — контролируемое течение\n" +
                                        "• ≤11 — неконтролируемое течение"
                        )
                )
        );
    }

    private void bind() {

        cmbQ1.getSelectionModel()
                .selectedIndexProperty()
                .addListener((o, ov, nv) -> {

                    model.q1Property().set(
                            nv.intValue()
                    );

                    calculate();
                });

        cmbQ2.getSelectionModel()
                .selectedIndexProperty()
                .addListener((o, ov, nv) -> {

                    model.q2Property().set(
                            nv.intValue()
                    );

                    calculate();
                });

        cmbQ3.getSelectionModel()
                .selectedIndexProperty()
                .addListener((o, ov, nv) -> {

                    model.q3Property().set(
                            nv.intValue()
                    );

                    calculate();
                });

        cmbQ4.getSelectionModel()
                .selectedIndexProperty()
                .addListener((o, ov, nv) -> {

                    model.q4Property().set(
                            nv.intValue()
                    );

                    calculate();
                });

        txtResult.textProperty().bindBidirectional(
                model.resultProperty()
        );
    }

    private void calculate() {

        model.calc();

        double score = model.resultValueProperty().get();

        if (score >= 12) {

            ResultStyler.applyStyle(
                    txtResult,
                    ResultStyler.Zone.LOW
            );
        }
        else {

            ResultStyler.applyStyle(
                    txtResult,
                    ResultStyler.Zone.HIGH
            );
        }
    }

    @Override
    public void close() {

        txtResult.textProperty().unbindBidirectional(
                model.resultProperty()
        );
    }

    @Override
    public double getDefaultWidth() {
        return 600;
    }

    @Override
    public double getDefaultHeight() {
        return 450;
    }
}