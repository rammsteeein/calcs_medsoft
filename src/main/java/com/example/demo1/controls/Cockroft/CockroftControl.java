package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.CreatininUnit;
import com.example.demo1.common.enums.Gender;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

        btnCalc = new Button();
        btnCalc.setText(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, cmbGender, nmrKreatinin, nmrAge, nmrWeight, btnCalc, txtResult));
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
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
