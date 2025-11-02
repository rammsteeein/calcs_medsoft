package com.example.demo1.controls.FIB4;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class FIB4Control extends StackPane implements Closeable, CalculatorControl {
    private final FIB4Model model;

    private TextField txtAge, txtAst, txtAlt, txtPlatelets;
    private TextArea txtResult;

    public FIB4Control(FIB4Model model) {
        this.model = model;
        initialize();
        bind();
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

        txtAge.textProperty().addListener((obs, o, n) -> model.calc());
        txtAst.textProperty().addListener((obs, o, n) -> model.calc());
        txtAlt.textProperty().addListener((obs, o, n) -> model.calc());
        txtPlatelets.textProperty().addListener((obs, o, n) -> model.calc());

        model.resultProperty().addListener((obs, o, n) -> txtResult.setText(n));
        model.resultValueProperty().addListener((obs, o, n) ->
                ResultStyler.applyStyleForValue(txtResult, n.doubleValue(), 1.45, 3.25)
        );
    }

    private void unbind() {
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        txtAst.textProperty().unbindBidirectional(model.astProperty());
        txtAlt.textProperty().unbindBidirectional(model.altProperty());
        txtPlatelets.textProperty().unbindBidirectional(model.plateletsProperty());
        model.resultProperty().removeListener((obs, o, n) -> txtResult.setText(n));
        model.resultValueProperty().removeListener((obs, o, n) ->
                ResultStyler.applyStyleForValue(txtResult, n.doubleValue(), 1.45, 3.25)
        );
    }

    @Override
    public void close() {
        unbind();
    }

    @Override
    public double getDefaultWidth() { return 700; }

    @Override
    public double getDefaultHeight() { return 430; }
}
