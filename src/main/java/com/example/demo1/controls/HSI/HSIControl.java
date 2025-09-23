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
        txtALT = new TextField(); txtALT.setPromptText("АЛТ");
        txtAST = new TextField(); txtAST.setPromptText("АСТ");
        txtBMI = new TextField(); txtBMI.setPromptText("ИМТ");
        cmbGender = new ComboBox<>(); cmbGender.getItems().addAll(Gender.values()); cmbGender.setPromptText("Пол");
        chkDiabetes = new CheckBox("СД");
        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10, txtALT, txtAST, txtBMI, cmbGender, chkDiabetes, txtResult));
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        chkDiabetes.selectedProperty().bindBidirectional(model.hasDiabetesProperty());

        txtALT.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAlt(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtAST.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAst(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtBMI.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setBmi(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
