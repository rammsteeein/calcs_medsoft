package com.example.demo1.controls.FIB4;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class FIB4Control extends StackPane implements Closeable, CalculatorControl {

    private FIB4Model model;

    private TextField txtAge, txtAst, txtAlt, txtPlatelets;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (obs, o, n) -> model.calc();
    private final ChangeListener<String> astListener = (obs, o, n) -> model.calc();
    private final ChangeListener<String> altListener = (obs, o, n) -> model.calc();
    private final ChangeListener<String> plateletsListener = (obs, o, n) -> model.calc();
    private final ChangeListener<String> resultListener = (obs, o, n) -> txtResult.setText(n);
    private final ChangeListener<Number> resultValueListener = (obs, o, n) ->
            ResultStyler.applyStyleForValue(txtResult, n.doubleValue(), 1.45, 3.25);

    public FIB4Control(FIB4Model model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");
        txtAst = new TextField(); txtAst.setPromptText("АСТ (Ед/л)");
        txtAlt = new TextField(); txtAlt.setPromptText("АЛТ (Ед/л)");
        txtPlatelets = new TextField(); txtPlatelets.setPromptText("Тромбоциты (10^9/л)");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс FIB-4"),
                txtAge, txtAst, txtAlt, txtPlatelets, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "FIB-4 используется для неинвазивной оценки фиброза печени.\n\n" +
                                "Формула:\n" +
                                "  FIB-4 = (Возраст × AST) / (Тромбоциты × √ALT)\n\n" +
                                "Клиническое применение:\n" +
                                "- Пациенты с хроническими гепатитами\n" +
                                "- Неалкогольная жировая болезнь печени (НАЖБП)\n\n" +
                                "Интерпретация:\n" +
                                "- Низкие значения: низкий риск значимого фиброза\n" +
                                "- Высокие значения: подозрение на выраженный фиброз"
                )
        ));
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        txtAst.textProperty().bindBidirectional(model.astProperty());
        txtAlt.textProperty().bindBidirectional(model.altProperty());
        txtPlatelets.textProperty().bindBidirectional(model.plateletsProperty());
        model.resultProperty().addListener(resultListener);
        model.resultValueProperty().addListener(resultValueListener);
    }

    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);
        txtAst.textProperty().addListener(astListener);
        txtAlt.textProperty().addListener(altListener);
        txtPlatelets.textProperty().addListener(plateletsListener);
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);
        txtAst.textProperty().removeListener(astListener);
        txtAlt.textProperty().removeListener(altListener);
        txtPlatelets.textProperty().removeListener(plateletsListener);
        model.resultProperty().removeListener(resultListener);
        model.resultValueProperty().removeListener(resultValueListener);
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        txtAst.textProperty().unbindBidirectional(model.astProperty());
        txtAlt.textProperty().unbindBidirectional(model.altProperty());
        txtPlatelets.textProperty().unbindBidirectional(model.plateletsProperty());
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }

    @Override
    public double getDefaultWidth() { return 700; }

    @Override
    public double getDefaultHeight() { return 430; }
}
