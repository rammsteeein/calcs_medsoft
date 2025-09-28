package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class CockroftControl extends StackPane implements Closeable {
    private final CockroftModel model;

    private ComboBox<Gender> cmbGender;
    private TextField nmrKreatinin;
    private TextField nmrAge;
    private TextField nmrWeight;
    private Button btnCalc;
    private TextArea txtResult;

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
        nmrKreatinin.setPromptText("Креатинин (мг/дл)");

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        nmrWeight = new TextField();
        nmrWeight.setPromptText("Вес");

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(
                new HBox(20,
                        new VBox(10,
                                CalculatorHeader.createHeader("Клиренс креатинина по формуле Cockroft"),
                                cmbGender, nmrKreatinin, nmrAge, nmrWeight, btnCalc, txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Формула Кокрофта-Голта позволяет оценить клиренс креатинина — показатель фильтрационной функции почек.\n" +
                                        "CrCl = ((140 - возраст) * вес) * (0.85 если женщина) / (72 * Scr)"
                        )
                )
        );
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());

        model.resultProperty().addListener((obs, oldVal, newVal) -> {
            txtResult.setText(newVal != null ? newVal.toString() : "");
        });
    }

    private void unbind() {
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
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
