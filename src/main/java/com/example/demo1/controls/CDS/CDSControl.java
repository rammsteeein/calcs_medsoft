package com.example.demo1.controls.CDS;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CDSControl extends StackPane implements AutoCloseable {
    private CDSModel model = CDSModel.builder().build();

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
        txtResult.setPromptText("Результат расчёта");

        this.getChildren().add(new VBox(10.0, new Node[]{
                CalculatorHeader.createHeader("Шкала CDS"),
                cmbAppearance, cmbEyes, cmbMucous, cmbTears, btnCalc, txtResult
        }));
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
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}
