package com.example.demo1.controls.IDAChronicAnemia;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IDAChronicAnemiaControl extends StackPane {

    private final IDAChronicAnemiaModel model;

    private TextField txtSerumIron;
    private TextField txtTIBC;
    private TextField txtTransferrinSat;
    private TextField txtFerritin;
    private TextArea txtResult;

    public IDAChronicAnemiaControl(IDAChronicAnemiaModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtSerumIron = new TextField(); txtSerumIron.setPromptText("Сывороточное железо (мкмоль/л)");
        txtTIBC = new TextField(); txtTIBC.setPromptText("ОЖСС (TIBC) мкмоль/л");
        txtTransferrinSat = new TextField(); txtTransferrinSat.setPromptText("НТЖ (%)");
        txtFerritin = new TextField(); txtFerritin.setPromptText("Ферритин (нг/мл)");
        txtResult = new TextArea(); txtResult.setEditable(false);

        Button btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> model.calc());

        this.getChildren().add(new VBox(5,
                CalculatorHeader.createHeader("Дифференц. диагностика железодефиц. анемии и анемии хронических заболеваний"),
                txtSerumIron, txtTIBC, txtTransferrinSat, txtFerritin, btnCalc, txtResult));
    }

    private void bind() {
        txtSerumIron.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setSerumIron(Double.parseDouble(newVal)); } catch(Exception ignored){}
        });
        txtTIBC.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setTIBC(Double.parseDouble(newVal)); } catch(Exception ignored){}
        });
        txtTransferrinSat.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setTransferrinSat(Double.parseDouble(newVal)); } catch(Exception ignored){}
        });
        txtFerritin.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setFerritin(Double.parseDouble(newVal)); } catch(Exception ignored){}
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
