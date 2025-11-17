package com.example.demo1.controls.RCRI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class RCRIControl extends BorderPane implements CalculatorControl, Closeable {

    private RCRIModel model;

    private CheckBox chkHighRiskSurgery;
    private CheckBox chkIschemicHeartDisease;
    private CheckBox chkHeartFailure;
    private CheckBox chkCerebrovascularDisease;
    private CheckBox chkInsulinTreatment;
    private CheckBox chkHighCreatinine;
    private TextArea txtResult;

    private final ChangeListener<Boolean> highRiskSurgeryListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> ischemicHeartDiseaseListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> heartFailureListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> cerebrovascularDiseaseListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> insulinTreatmentListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> highCreatinineListener = (obs, oldVal, newVal) -> model.calc();

    public RCRIControl(RCRIModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
        model.calc();
    }

    private void initialize() {
        chkHighRiskSurgery = new CheckBox("Хирургия повышенного риска");
        chkIschemicHeartDisease = new CheckBox("Анамнез ИБС");
        chkHeartFailure = new CheckBox("Застойная СН в анамнезе");
        chkCerebrovascularDisease = new CheckBox("Анамнез цереброваскулярных заболеваний");
        chkInsulinTreatment = new CheckBox("Предоперационное лечение инсулином");
        chkHighCreatinine = new CheckBox("Предоперационный креатинин >176,8 мкмоль/л");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefRowCount(4);

        VBox form = new VBox(10,
                CalculatorHeader.createHeader("Индекс RCRI"),
                chkHighRiskSurgery, chkIschemicHeartDisease, chkHeartFailure,
                chkCerebrovascularDisease, chkInsulinTreatment, chkHighCreatinine,
                txtResult
        );

        setCenter(form);

        setRight(CalculatorDescription.createDescription(
                "Шкала RCRI (Revised Cardiac Risk Index) используется для оценки\n" +
                        "кардиологического риска перед несердечной операцией.\n\n" +
                        "Критерии начисления баллов:\n" +
                        "- Хирургия повышенного риска\n" +
                        "- Анамнез ишемической болезни сердца\n" +
                        "- Застойная сердечная недостаточность\n" +
                        "- Анамнез цереброваскулярных заболеваний\n" +
                        "- Предоперационное лечение инсулином\n" +
                        "- Предоперационный уровень креатинина >176,8 мкмоль/л\n\n" +
                        "Интерпретация:\n" +
                        "0 баллов — риск 3,9%\n" +
                        "1 балл — риск 6,0%\n" +
                        "2 балла — риск 10,1%\n" +
                        "≥3 баллов — риск 15%"
        ));
    }

    private void bind() {
        chkHighRiskSurgery.selectedProperty().bindBidirectional(model.highRiskSurgeryProperty());
        chkIschemicHeartDisease.selectedProperty().bindBidirectional(model.ischemicHeartDiseaseProperty());
        chkHeartFailure.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkCerebrovascularDisease.selectedProperty().bindBidirectional(model.cerebrovascularDiseaseProperty());
        chkInsulinTreatment.selectedProperty().bindBidirectional(model.insulinTreatmentProperty());
        chkHighCreatinine.selectedProperty().bindBidirectional(model.highCreatinineProperty());
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void unbind() {
        chkHighRiskSurgery.selectedProperty().unbindBidirectional(model.highRiskSurgeryProperty());
        chkIschemicHeartDisease.selectedProperty().unbindBidirectional(model.ischemicHeartDiseaseProperty());
        chkHeartFailure.selectedProperty().unbindBidirectional(model.heartFailureProperty());
        chkCerebrovascularDisease.selectedProperty().unbindBidirectional(model.cerebrovascularDiseaseProperty());
        chkInsulinTreatment.selectedProperty().unbindBidirectional(model.insulinTreatmentProperty());
        chkHighCreatinine.selectedProperty().unbindBidirectional(model.highCreatinineProperty());
        txtResult.textProperty().unbind();
    }

    private void addListeners() {
        chkHighRiskSurgery.selectedProperty().addListener(highRiskSurgeryListener);
        chkIschemicHeartDisease.selectedProperty().addListener(ischemicHeartDiseaseListener);
        chkHeartFailure.selectedProperty().addListener(heartFailureListener);
        chkCerebrovascularDisease.selectedProperty().addListener(cerebrovascularDiseaseListener);
        chkInsulinTreatment.selectedProperty().addListener(insulinTreatmentListener);
        chkHighCreatinine.selectedProperty().addListener(highCreatinineListener);
    }

    private void removeListeners() {
        chkHighRiskSurgery.selectedProperty().removeListener(highRiskSurgeryListener);
        chkIschemicHeartDisease.selectedProperty().removeListener(ischemicHeartDiseaseListener);
        chkHeartFailure.selectedProperty().removeListener(heartFailureListener);
        chkCerebrovascularDisease.selectedProperty().removeListener(cerebrovascularDiseaseListener);
        chkInsulinTreatment.selectedProperty().removeListener(insulinTreatmentListener);
        chkHighCreatinine.selectedProperty().removeListener(highCreatinineListener);
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 620;
    }

    @Override
    public double getDefaultHeight() {
        return 400;
    }
}
