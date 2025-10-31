package com.example.demo1.controls.RCRI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RCRIControl extends BorderPane implements CalculatorControl {

    private final RCRIModel model;

    private CheckBox chkHighRiskSurgery;
    private CheckBox chkIschemicHeartDisease;
    private CheckBox chkHeartFailure;
    private CheckBox chkCerebrovascularDisease;
    private CheckBox chkInsulinTreatment;
    private CheckBox chkHighCreatinine;
    private TextArea txtResult;

    public RCRIControl(RCRIModel model) {
        this.model = model;
        initialize();
        bind();
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

        chkHighRiskSurgery.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkIschemicHeartDisease.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkHeartFailure.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkCerebrovascularDisease.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkInsulinTreatment.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkHighCreatinine.selectedProperty().addListener((obs, o, n) -> model.calc());

        txtResult.textProperty().bind(model.resultProperty());
        model.calc();
    }
}
