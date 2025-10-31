package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class CockroftControl extends StackPane implements Closeable, CalculatorControl {
    private final CockroftModel model;

    private ComboBox<Gender> cmbGender;
    private TextField nmrKreatinin;
    private TextField nmrAge;
    private TextField nmrWeight;
    private Button btnCalc;
    private TextArea txtResult;
    private ComboBox<Unit> cmbUnit;

    private static final String GENDER_TEXT = "Пол:";
    private static final String BUTTON_TEXT = "Рассчитать";

    public CockroftControl(CockroftModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText(GENDER_TEXT);

        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Креатинин");

        cmbUnit = new ComboBox<>();
        cmbUnit.getItems().addAll(Unit.forCkdEpi());
        cmbUnit.setValue(Unit.defaultForCkdEpi());

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        nmrWeight = new TextField();
        nmrWeight.setPromptText("Вес");

        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        cmbUnit.valueProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        nmrKreatinin.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        nmrAge.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());
        nmrWeight.textProperty().addListener((obs, oldVal, newVal) -> tryAutoCalc());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(
                new HBox(20,
                        new VBox(10,
                                CalculatorHeader.createHeader("Клиренс креатинина по формуле Cockroft"),
                                cmbGender,
                                new HBox(10, nmrKreatinin, cmbUnit),
                                nmrAge, nmrWeight, txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Формула Кокрофта-Голта позволяет оценить клиренс креатинина — показатель фильтрационной функции почек.\n" +
                                        "CrCl = ((140 - возраст) * вес) * (0.85 если женщина) / (72 * Scr)"
                        )
                )
        );
    }

    private void tryAutoCalc() {
        if (model == null) {
            return;
        }
        if (cmbGender.getValue() == null ||
                cmbUnit.getValue() == null ||
                nmrKreatinin.getText() == null || nmrKreatinin.getText().isEmpty() ||
                nmrAge.getText() == null || nmrAge.getText().isEmpty() ||
                nmrWeight.getText() == null || nmrWeight.getText().isEmpty()) {
            txtResult.setText("Введите все поля для расчёта");
            return;
        }

        try {
            Double.parseDouble(nmrKreatinin.getText());
            Double.parseDouble(nmrWeight.getText());
            Integer.parseInt(nmrAge.getText());
        } catch (NumberFormatException ex) {
            txtResult.setText("Некорректный ввод чисел");
            return;
        }
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        cmbUnit.valueProperty().bindBidirectional(model.creatininUnitProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());

        model.resultProperty().addListener((obs, oldVal, newVal) -> {
            txtResult.setText(newVal != null ? newVal.toString() : "");
        });
    }

    private void unbind() {
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        cmbUnit.valueProperty().unbindBidirectional(model.creatininUnitProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
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
