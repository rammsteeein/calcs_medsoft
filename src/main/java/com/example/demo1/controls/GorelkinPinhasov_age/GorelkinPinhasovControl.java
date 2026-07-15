package com.example.demo1.controls.GorelkinPinhasov_age;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class GorelkinPinhasovControl extends StackPane implements Closeable, CalculatorControl {

    private GorelkinPinhasovModel model;

    private ComboBox<Gender> cmbGender;

    private TextField txtAge;
    private TextField txtHeight;
    private TextField txtWeight;
    private TextField txtWaist;
    private TextField txtHips;

    private TextArea txtResult;

    private final ChangeListener<String> autoCalcListener =
            (obs, oldVal, newVal) -> model.calc();

    private final ChangeListener<Gender> genderListener =
            (obs, oldVal, newVal) -> model.calc();

    public GorelkinPinhasovControl(GorelkinPinhasovModel model) {

        this.model = model;

        initialize();
        bind();
        addListeners();
    }

    private void initialize() {

        setPadding(new Insets(15));

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtHeight = new TextField();
        txtHeight.setPromptText("Рост (см)");

        txtWeight = new TextField();
        txtWeight.setPromptText("Масса тела (кг)");

        txtWaist = new TextField();
        txtWaist.setPromptText("Окружность талии (см)");

        txtHips = new TextField();
        txtHips.setPromptText("Окружность бедер (см)");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setWrapText(true);
        txtResult.setPrefHeight(260);
        txtResult.setPromptText("Результат расчета");

        VBox inputBox = new VBox(
                10,
                cmbGender,
                txtAge,
                txtHeight,
                txtWeight,
                txtWaist,
                txtHips,
                txtResult
        );

        inputBox.setPrefWidth(360);

        getChildren().add(

                new HBox(
                        20,

                        new VBox(
                                10,
                                CalculatorHeader.createHeader(
                                        "Биологический возраст\n(Горелкин–Пинхасов)"
                                ),
                                inputBox
                        ),

                        CalculatorDescription.createDescription(
                                "Калькулятор рассчитывает коэффициент скорости старения "
                                        + "(КСС) и биологический возраст по формулам "
                                        + "Горелкина–Пинхасова.\n\n"

                                        + "Также автоматически выполняется интерпретация "
                                        + "темпа старения и оценка риска по шкале "
                                        + "AnthropoAge."
                        )

                )

        );

    }

    private void bind() {

        cmbGender.valueProperty().bindBidirectional(model.genderProperty());

        txtAge.textProperty().bindBidirectional(model.ageProperty());

        txtHeight.textProperty().bindBidirectional(model.heightProperty());

        txtWeight.textProperty().bindBidirectional(model.weightProperty());

        txtWaist.textProperty().bindBidirectional(model.waistProperty());

        txtHips.textProperty().bindBidirectional(model.hipsProperty());

        txtResult.textProperty().bindBidirectional(model.resultProperty());

    }

    private void unbind() {

        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());

        txtAge.textProperty().unbindBidirectional(model.ageProperty());

        txtHeight.textProperty().unbindBidirectional(model.heightProperty());

        txtWeight.textProperty().unbindBidirectional(model.weightProperty());

        txtWaist.textProperty().unbindBidirectional(model.waistProperty());

        txtHips.textProperty().unbindBidirectional(model.hipsProperty());

        txtResult.textProperty().unbindBidirectional(model.resultProperty());

    }

    private void addListeners() {

        cmbGender.valueProperty().addListener(genderListener);

        txtAge.textProperty().addListener(autoCalcListener);

        txtHeight.textProperty().addListener(autoCalcListener);

        txtWeight.textProperty().addListener(autoCalcListener);

        txtWaist.textProperty().addListener(autoCalcListener);

        txtHips.textProperty().addListener(autoCalcListener);

    }

    private void removeListeners() {

        cmbGender.valueProperty().removeListener(genderListener);

        txtAge.textProperty().removeListener(autoCalcListener);

        txtHeight.textProperty().removeListener(autoCalcListener);

        txtWeight.textProperty().removeListener(autoCalcListener);

        txtWaist.textProperty().removeListener(autoCalcListener);

        txtHips.textProperty().removeListener(autoCalcListener);

    }

    @Override
    public void close() {

        removeListeners();
        unbind();

    }

    @Override
    public double getDefaultWidth() {
        return 520;
    }

    @Override
    public double getDefaultHeight() {
        return 560;
    }

}