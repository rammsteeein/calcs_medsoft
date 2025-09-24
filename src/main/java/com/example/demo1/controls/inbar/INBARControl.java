package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class INBARControl extends StackPane implements Closeable {
    private final INBARModel model;

    private TextField nmrAge;
    private TextField nmrWeight;
    private ComboBox<Gender> cmbGender;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

    public INBARControl(INBARModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        nmrWeight = new TextField();
        nmrWeight.setPromptText("Вес (кг)");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setValue(Gender.MALE);

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, nmrAge, nmrWeight, cmbGender, btnCalc, txtResult));
    }

    private void bind() {
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
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
