package com.example.demo1.controls.GRACE;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class GRACEControl extends StackPane implements Closeable {

    private GRACEModel model;

    private TextField txtAgePoints, txtHRPoints, txtSBPPoints, txtCreatininePoints, txtOtherPoints;
    private ComboBox<String> cmbKillip;
    private Button btnCalc;
    private TextArea txtResult;

    public GRACEControl(GRACEModel model) {
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

        getChildren().add(new VBox(10, txtAgePoints, txtHRPoints, txtSBPPoints, cmbKillip,
                txtCreatininePoints, txtOtherPoints, btnCalc, txtResult));
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
        } catch (Exception e) {
            txtResult.setText("Ошибка ввода: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}