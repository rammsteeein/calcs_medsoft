package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.CreatininUnit;
import com.example.demo1.common.enums.Gender;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class CKDEPIControl extends StackPane implements Closeable {
    CKDEPIModel model = CKDEPIModel.builder().build();

    private ComboBox<Gender> cmbGender;
    private TextField nmrKreatinin;
    private ComboBox<CreatininUnit> cmbCreatininUnit;
    private TextField nmrAge;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String GENDER_TEXT = "Пол:";
    private static final String BUTTON_TEXT = "Рассчитать";

    public CKDEPIControl(CKDEPIModel model) {
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

        cmbCreatininUnit = new ComboBox<>();
        cmbCreatininUnit.getItems().addAll(CreatininUnit.values());
        cmbCreatininUnit.setPromptText("Ед. измерения");

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        btnCalc = new Button();
        btnCalc.setText(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, cmbGender, nmrKreatinin, cmbCreatininUnit, nmrAge, btnCalc, txtResult));
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
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        cmbCreatininUnit.valueProperty().unbindBidirectional(model.creatininUnitProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
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