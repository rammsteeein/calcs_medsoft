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
    private ComboBox<Unit> cmbUnit;
    private TextArea txtResult;

    public CockroftControl(CockroftModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Креатинин");

        cmbUnit = new ComboBox<>();
        cmbUnit.getItems().addAll(Unit.forCkdEpi());
        cmbUnit.setValue(Unit.defaultForCkdEpi());

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        nmrWeight = new TextField();
        nmrWeight.setPromptText("Вес");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(
                new HBox(20,
                        new VBox(10,
                                CalculatorHeader.createHeader("Клиренс креатинина (Cockcroft-Gault)"),
                                cmbGender,
                                new HBox(10, nmrKreatinin, cmbUnit),
                                nmrAge, nmrWeight, txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Формула Кокрофта-Голта используется для оценки клиренса креатинина — показателя фильтрационной функции почек.\n\n" +
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
        cmbUnit.valueProperty().bindBidirectional(model.creatininUnitProperty());
        txtResult.textProperty().bind(model.resultStringProperty());

        cmbGender.valueProperty().addListener((obs, o, n) -> model.calc());
        cmbUnit.valueProperty().addListener((obs, o, n) -> model.calc());

        nmrKreatinin.textProperty().addListener((obs, o, n) -> model.calc());
        nmrAge.textProperty().addListener((obs, o, n) -> model.calc());
        nmrWeight.textProperty().addListener((obs, o, n) -> model.calc());
    }

    @Override
    public void close() {
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
        cmbUnit.valueProperty().unbindBidirectional(model.creatininUnitProperty());
        txtResult.textProperty().unbind();
    }
}
