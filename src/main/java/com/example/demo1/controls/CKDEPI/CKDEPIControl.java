package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.Unit;
import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class CKDEPIControl extends StackPane implements Closeable, CalculatorControl {

    private final CKDEPIModel model;

    private ComboBox<Gender> cmbGender;
    private TextField nmrKreatinin;
    private ComboBox<Unit> cmbCreatininUnit;
    private TextField nmrAge;
    private TextArea txtResult;

    public CKDEPIControl(CKDEPIModel model) {
        this.model = model;
        initialize();
        bind();
        setupAutoCalc();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Креатинин");

        cmbCreatininUnit = new ComboBox<>(FXCollections.observableArrayList(Unit.forCkdEpi()));
        cmbCreatininUnit.setValue(Unit.defaultForCkdEpi());
        cmbCreatininUnit.setPromptText("Ед. измерения");

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");
        txtResult.setPrefHeight(100);

        getChildren().add(
                new HBox(20,
                        new VBox(10,
                                CalculatorHeader.createHeader("СКФ по формуле CKD-EPI (2021)"),
                                cmbGender,
                                nmrKreatinin,
                                cmbCreatininUnit,
                                nmrAge,
                                txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Формула CKD-EPI используется для оценки скорости клубочковой фильтрации (СКФ), " +
                                        "что является важным показателем функции почек. Формула учитывает уровень " +
                                        "креатинина в крови, возраст и пол пациента."
                        )
                )
        );
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        cmbCreatininUnit.valueProperty().bindBidirectional(model.creatininUnitProperty());
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        cmbCreatininUnit.valueProperty().unbindBidirectional(model.creatininUnitProperty());
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void setupAutoCalc() {
        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> model.tryCalcAuto());
        cmbCreatininUnit.valueProperty().addListener((obs, oldVal, newVal) -> model.tryCalcAuto());
        nmrKreatinin.textProperty().addListener((obs, oldVal, newVal) -> model.tryCalcAuto());
        nmrAge.textProperty().addListener((obs, oldVal, newVal) -> model.tryCalcAuto());
    }

    @Override
    public void close() {
        unbind();
    }
}
