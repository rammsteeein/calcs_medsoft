package com.example.demo1.controls.CDS;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CDSControl extends StackPane implements AutoCloseable {
    private final CDSModel model;

    private ComboBox<String> cmbAppearance;
    private ComboBox<String> cmbEyes;
    private ComboBox<String> cmbMucous;
    private ComboBox<String> cmbTears;
    private Button btnCalc;
    private TextArea txtResult;

    public CDSControl(CDSModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbAppearance = new ComboBox<>();
        cmbAppearance.getItems().addAll(
                "Нормальный",
                "Жажда, беспокойство, раздражительность",
                "Вялость, сонливость"
        );
        cmbAppearance.setPromptText("Внешний вид");

        cmbEyes = new ComboBox<>();
        cmbEyes.getItems().addAll(
                "Тургор нормальный",
                "Слегка запавшие",
                "Запавшие"
        );
        cmbEyes.setPromptText("Глазные яблоки");

        cmbMucous = new ComboBox<>();
        cmbMucous.getItems().addAll(
                "Влажные",
                "Липкие, суховатые",
                "Сухие"
        );
        cmbMucous.setPromptText("Слизистые оболочки");

        cmbTears = new ComboBox<>();
        cmbTears.getItems().addAll(
                "Слезоотделение в норме",
                "Слезоотделение снижено",
                "Слёзы отсутствуют"
        );
        cmbTears.setPromptText("Слёзы");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала CDS (оценка дегидратации)"),
                cmbAppearance, cmbEyes, cmbMucous, cmbTears,
                btnCalc, txtResult
        );

        this.getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала Clinical Dehydration Scale (CDS) используется для оценки степени обезвоживания.\n\n" +
                                "Параметры:\n" +
                                "1. Внешний вид (норма, раздражительность, вялость)\n" +
                                "2. Глазные яблоки (норма, слегка запавшие, запавшие)\n" +
                                "3. Слизистые оболочки (влажные, липкие, сухие)\n" +
                                "4. Слезы (норма, снижены, отсутствуют)\n\n" +
                                "Интерпретация:\n" +
                                "- 0 баллов — дегидратация отсутствует\n" +
                                "- 1–4 балла — лёгкая дегидратация\n" +
                                "- 5–8 баллов — средняя или тяжёлая дегидратация"
                )
        ));
    }

    private void bind() {
        cmbAppearance.valueProperty().bindBidirectional(model.appearanceProperty());
        cmbEyes.valueProperty().bindBidirectional(model.eyesProperty());
        cmbMucous.valueProperty().bindBidirectional(model.mucousProperty());
        cmbTears.valueProperty().bindBidirectional(model.tearsProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        cmbAppearance.valueProperty().unbindBidirectional(model.appearanceProperty());
        cmbEyes.valueProperty().unbindBidirectional(model.eyesProperty());
        cmbMucous.valueProperty().unbindBidirectional(model.mucousProperty());
        cmbTears.valueProperty().unbindBidirectional(model.tearsProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculateResult() {
        model.calc();
    }

    @Override
    public void close() {
        unbind();
    }
}
