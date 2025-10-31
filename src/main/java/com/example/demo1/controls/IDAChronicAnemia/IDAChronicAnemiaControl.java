package com.example.demo1.controls.IDAChronicAnemia;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IDAChronicAnemiaControl extends StackPane implements CalculatorControl {

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
        txtTIBC = new TextField(); txtTIBC.setPromptText("ОЖСС (мкмоль/л)");
        txtTransferrinSat = new TextField(); txtTransferrinSat.setPromptText("НТЖ (%)");
        txtFerritin = new TextField(); txtFerritin.setPromptText("Ферритин (нг/мл)");
        txtResult = new TextArea(); txtResult.setEditable(false);

        txtSerumIron.textProperty().addListener((obs, oldV, newV) -> tryAutoCalc());
        txtTIBC.textProperty().addListener((obs, oldV, newV) -> tryAutoCalc());
        txtTransferrinSat.textProperty().addListener((obs, oldV, newV) -> tryAutoCalc());
        txtFerritin.textProperty().addListener((obs, oldV, newV) -> tryAutoCalc());


        getChildren().add(
                new HBox(20,
                        new VBox(10,
                                CalculatorHeader.createHeader("Дифференциальная диагностика ЖДА и АХЗ"),
                                txtSerumIron, txtTIBC, txtTransferrinSat, txtFerritin, txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Калькулятор используется для различения железодефицитной анемии (ЖДА) " +
                                        "и анемии хронических заболеваний (АХЗ) на основании лабораторных показателей:\n" +
                                        "- Сывороточное железо\n" +
                                        "- Общая железосвязывающая способность сыворотки (ОЖСС)\n" +
                                        "- Насыщение трансферрина железом (НТЖ)\n" +
                                        "- Ферритин\n\n" +
                                        "ЖДА чаще сопровождается снижением железа, ферритина и НТЖ с повышением ОЖСС.\n" +
                                        "АХЗ — нормальным/сниженным железом, нормальным/сниженным ОЖСС и нормальным/повышенным ферритином."
                        )
                )
        );
    }

    private void tryAutoCalc() {
        if (txtSerumIron.getText().isEmpty() ||
                txtTIBC.getText().isEmpty() ||
                txtTransferrinSat.getText().isEmpty() ||
                txtFerritin.getText().isEmpty()) {
            model.resultProperty().set("Введите все поля");
            return;
        }

        try {
            model.setSerumIron(Double.parseDouble(txtSerumIron.getText()));
            model.setTIBC(Double.parseDouble(txtTIBC.getText()));
            model.setTransferrinSat(Double.parseDouble(txtTransferrinSat.getText()));
            model.setFerritin(Double.parseDouble(txtFerritin.getText()));

            model.calc();
        } catch (NumberFormatException e) {
            model.resultProperty().set("Некорректный ввод чисел");
        } catch (Exception e) {
            model.resultProperty().set("Ошибка: " + e.getMessage());
        }
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

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 400;
    }
}
