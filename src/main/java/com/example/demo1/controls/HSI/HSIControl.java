package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HSIControl extends StackPane {

    private final HSIModel model;

    private TextField txtALT;
    private TextField txtAST;
    private TextField txtBMI;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkDiabetes;
    private TextArea txtResult;

    public HSIControl(HSIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtALT = new TextField();
        txtALT.setPromptText("Введите АЛТ");

        txtAST = new TextField();
        txtAST.setPromptText("Введите АСТ");

        txtBMI = new TextField();
        txtBMI.setPromptText("Введите ИМТ");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");
        cmbGender.setValue(Gender.MALE); // дефолтный пол

        chkDiabetes = new CheckBox("Сахарный диабет");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");


        VBox vbox = new VBox(10,
                new VBox(new Label("АЛТ (Ед/л)"), txtALT),
                new VBox(new Label("АСТ (Ед/л)"), txtAST),
                new VBox(new Label("ИМТ"), txtBMI),
                new VBox(new Label("Пол"), cmbGender),
                chkDiabetes,
                txtResult
        );

        this.getChildren().add(vbox);
    }


    private void bind() {
        // ALT
        txtALT.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAlt(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        // AST
        txtAST.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAst(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        // BMI
        txtBMI.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setBmi(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        // Gender
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> model.calc());

        // Diabetes
        chkDiabetes.selectedProperty().bindBidirectional(model.hasDiabetesProperty());
        chkDiabetes.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        // Result
        txtResult.textProperty().bind(model.resultProperty());
    }
}
