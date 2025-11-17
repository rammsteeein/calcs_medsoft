package com.example.demo1.controls.IDAChronicAnemia;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IDAChronicAnemiaControl extends StackPane implements CalculatorControl, AutoCloseable {

    private IDAChronicAnemiaModel model;

    private TextField txtSerumIron;
    private TextField txtTIBC;
    private TextField txtTransferrinSat;
    private TextField txtFerritin;
    private TextArea txtResult;

    private final ChangeListener<String> serumIronListener = (obs, oldVal, newVal) -> updateDouble(model::setSerumIron, newVal);
    private final ChangeListener<String> tibcListener = (obs, oldVal, newVal) -> updateDouble(model::setTIBC, newVal);
    private final ChangeListener<String> transferrinListener = (obs, oldVal, newVal) -> updateDouble(model::setTransferrinSat, newVal);
    private final ChangeListener<String> ferritinListener = (obs, oldVal, newVal) -> updateDouble(model::setFerritin, newVal);

    public IDAChronicAnemiaControl(IDAChronicAnemiaModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtSerumIron = new TextField();
        txtSerumIron.setPromptText("Сывороточное железо (мкмоль/л)");

        txtTIBC = new TextField();
        txtTIBC.setPromptText("ОЖСС (мкмоль/л)");

        txtTransferrinSat = new TextField();
        txtTransferrinSat.setPromptText("НТЖ (%)");

        txtFerritin = new TextField();
        txtFerritin.setPromptText("Ферритин (нг/мл)");

        txtResult = new TextArea();
        txtResult.setEditable(false);

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

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {
        txtSerumIron.textProperty().addListener(serumIronListener);
        txtTIBC.textProperty().addListener(tibcListener);
        txtTransferrinSat.textProperty().addListener(transferrinListener);
        txtFerritin.textProperty().addListener(ferritinListener);
    }

    private void removeListeners() {
        txtSerumIron.textProperty().removeListener(serumIronListener);
        txtTIBC.textProperty().removeListener(tibcListener);
        txtTransferrinSat.textProperty().removeListener(transferrinListener);
        txtFerritin.textProperty().removeListener(ferritinListener);
    }

    private void updateDouble(java.util.function.DoubleConsumer setter, String newVal) {
        try {
            setter.accept(Double.parseDouble(newVal));
        } catch (Exception ignored) {}
        model.calc();
    }

    @Override
    public void close() {
        removeListeners();
        txtResult.textProperty().unbind();
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
