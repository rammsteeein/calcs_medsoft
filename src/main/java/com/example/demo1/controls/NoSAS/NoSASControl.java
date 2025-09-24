package com.example.demo1.controls.NoSAS;

import com.example.demo1.common.enums.Gender;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NoSASControl extends StackPane {

    private final NoSASModel model;

    private TextField txtNeck;
    private TextField txtBmi;
    private CheckBox chkSnoring;
    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private TextArea txtResult;

    public NoSASControl(NoSASModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtNeck = new TextField(); txtNeck.setPromptText("Окружность шеи (см)");
        txtBmi = new TextField(); txtBmi.setPromptText("ИМТ");
        chkSnoring = new CheckBox("Храп");
        txtAge = new TextField(); txtAge.setPromptText("Возраст");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");
        cmbGender.setValue(model.getGender());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10, txtNeck, txtBmi, chkSnoring, txtAge, cmbGender, txtResult));
    }

    private void bind() {
        chkSnoring.selectedProperty().bindBidirectional(model.hasSnoringProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());

        txtNeck.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setNeckCircumference(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtBmi.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setBmi(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        chkSnoring.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> model.calc());

        txtResult.textProperty().bind(model.resultProperty());
    }
}
