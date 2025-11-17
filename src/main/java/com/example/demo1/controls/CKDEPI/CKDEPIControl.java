package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.Unit;
import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class CKDEPIControl extends StackPane implements Closeable, CalculatorControl {

    private CKDEPIModel model;

    private ComboBox<Gender> cmbGender;
    private TextField txtCreatinine;
    private ComboBox<Unit> cmbUnit;
    private TextField txtAge;
    private TextArea txtResult;

    private final ChangeListener<Gender> genderListener =
            (o, ov, nv) -> { model.setGender(nv); model.tryCalcAuto(); };

    private final ChangeListener<Unit> unitListener =
            (o, ov, nv) -> { model.setCreatininUnit(nv); model.tryCalcAuto(); };

    private final ChangeListener<String> creatinineListener =
            (o, ov, nv) -> { model.setKreatinin(nv); model.tryCalcAuto(); };

    private final ChangeListener<String> ageListener =
            (o, ov, nv) -> { model.setAge(nv); model.tryCalcAuto(); };

    private final ChangeListener<String> resultListener =
            (o, ov, nv) -> txtResult.setText(nv);

    public CKDEPIControl(CKDEPIModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        txtCreatinine = new TextField();
        txtCreatinine.setPromptText("Креатинин");

        cmbUnit = new ComboBox<>(FXCollections.observableArrayList(Unit.forCkdEpi()));
        cmbUnit.setValue(Unit.defaultForCkdEpi());

        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(100);

        getChildren().add(
                new HBox(20,
                        new VBox(10,
                                CalculatorHeader.createHeader("СКФ по формуле CKD-EPI (2021)"),
                                cmbGender,
                                txtCreatinine,
                                cmbUnit,
                                txtAge,
                                txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Формула CKD-EPI оценивает скорость клубочковой фильтрации " +
                                        "по креатинину, возрасту и полу пациента."
                        )
                )
        );
    }

    private void bind() {
        model.resultProperty().addListener(resultListener);
        txtResult.setText(model.getResult());
    }

    private void addListeners() {
        cmbGender.valueProperty().addListener(genderListener);
        cmbUnit.valueProperty().addListener(unitListener);
        txtCreatinine.textProperty().addListener(creatinineListener);
        txtAge.textProperty().addListener(ageListener);
    }

    private void removeListeners() {
        cmbGender.valueProperty().removeListener(genderListener);
        cmbUnit.valueProperty().removeListener(unitListener);
        txtCreatinine.textProperty().removeListener(creatinineListener);
        txtAge.textProperty().removeListener(ageListener);
        model.resultProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbind();
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 650; }

    @Override
    public double getDefaultHeight() { return 420; }
}
