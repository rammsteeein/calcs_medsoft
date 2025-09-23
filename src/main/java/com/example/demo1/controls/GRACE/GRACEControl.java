package com.example.demo1.controls.GRACE;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GRACEControl extends StackPane implements AutoCloseable {

    private GRACEModel model = GRACEModel.builder().build();

    private TextField txtAgePoints, txtHRPoints, txtSBPPoints, txtCreatininePoints, txtOtherPoints;
    private ComboBox<String> cmbKillip;
    private Button btnCalc;
    private TextArea txtResult;

    public GRACEControl() {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAgePoints = new TextField();
        txtAgePoints.setPromptText("Баллы за возраст");

        txtHRPoints = new TextField();
        txtHRPoints.setPromptText("Баллы за ЧСС");

        txtSBPPoints = new TextField();
        txtSBPPoints.setPromptText("Баллы за САД");

        txtCreatininePoints = new TextField();
        txtCreatininePoints.setPromptText("Баллы за креатинин");

        txtOtherPoints = new TextField();
        txtOtherPoints.setPromptText("Баллы за другие факторы");

        cmbKillip = new ComboBox<>();
        cmbKillip.getItems().addAll("I", "II", "III", "IV");
        cmbKillip.setPromptText("Класс Killip");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        this.getChildren().add(new VBox(10.0, new Node[]{
                txtAgePoints, txtHRPoints, txtSBPPoints, cmbKillip,
                txtCreatininePoints, txtOtherPoints,
                btnCalc, txtResult
        }));
    }

    private void bind() {
        model.agePointsProperty().bindBidirectional(txtAgePoints.textProperty());
        model.hrPointsProperty().bindBidirectional(txtHRPoints.textProperty());
        model.sbpPointsProperty().bindBidirectional(txtSBPPoints.textProperty());
        model.killipClassProperty().bindBidirectional(cmbKillip.valueProperty());
        model.creatininePointsProperty().bindBidirectional(txtCreatininePoints.textProperty());
        model.otherPointsProperty().bindBidirectional(txtOtherPoints.textProperty());
        model.resultProperty().bindBidirectional(txtResult.textProperty());
    }

    private void unbind() {
        model.agePointsProperty().unbindBidirectional(txtAgePoints.textProperty());
        model.hrPointsProperty().unbindBidirectional(txtHRPoints.textProperty());
        model.sbpPointsProperty().unbindBidirectional(txtSBPPoints.textProperty());
        model.killipClassProperty().unbindBidirectional(cmbKillip.valueProperty());
        model.creatininePointsProperty().unbindBidirectional(txtCreatininePoints.textProperty());
        model.otherPointsProperty().unbindBidirectional(txtOtherPoints.textProperty());
        model.resultProperty().unbindBidirectional(txtResult.textProperty());
    }

    private void calculateResult() {
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка ввода: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}