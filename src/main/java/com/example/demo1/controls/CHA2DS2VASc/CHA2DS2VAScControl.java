package com.example.demo1.controls.CHA2DS2VASc;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CHA2DS2VAScControl extends StackPane implements AutoCloseable, CalculatorControl {

    private CHA2DS2VAScModel model;

    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkCHF, chkHTN, chkDM, chkStroke, chkCV;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (o, ov, nv) -> {
        try { model.setAge(Integer.parseInt(nv)); } catch (Exception ignored) { model.setAge(0); }
        calculate();
    };

    private final ChangeListener<Gender> genderListener = (o, ov, nv) -> {
        model.setGender(nv);
        calculate();
    };

    private final ChangeListener<Boolean> chfListener    = (o, ov, nv) -> calculate();
    private final ChangeListener<Boolean> htnListener    = (o, ov, nv) -> calculate();
    private final ChangeListener<Boolean> dmListener     = (o, ov, nv) -> calculate();
    private final ChangeListener<Boolean> strokeListener = (o, ov, nv) -> calculate();
    private final ChangeListener<Boolean> cvListener     = (o, ov, nv) -> calculate();

    private final ChangeListener<String> resultListener  = (o, ov, nv) ->
            ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 1, 1.5);

    public CHA2DS2VAScControl(CHA2DS2VAScModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        chkCHF = new CheckBox("Застойная сердечная недостаточность");
        chkHTN = new CheckBox("Артериальная гипертензия");
        chkDM = new CheckBox("Сахарный диабет");
        chkStroke = new CheckBox("Инсульт/ТИА");
        chkCV = new CheckBox("СС-заболевания в анамнезе");

        txtResult = new TextArea();
        txtResult.setEditable(false);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Шкала CHA₂DS₂-VASc"),
                new VBox(new Label("Возраст"), txtAge),
                new VBox(new Label("Пол"), cmbGender),
                chkCHF,
                chkHTN,
                chkDM,
                chkStroke,
                chkCV,
                txtResult
        );

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Шкала CHA₂DS₂-VASc для оценки риска инсульта у пациентов с ФП.\n" +
                                "Чем выше сумма, тем выше риск тромбоэмболических осложнений."
                )
        ));
    }

    private void bind() {
        model.resultProperty().addListener(resultListener);
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);
        cmbGender.valueProperty().addListener(genderListener);

        chkCHF.selectedProperty().addListener(chfListener);
        chkHTN.selectedProperty().addListener(htnListener);
        chkDM.selectedProperty().addListener(dmListener);
        chkStroke.selectedProperty().addListener(strokeListener);
        chkCV.selectedProperty().addListener(cvListener);

        model.congestiveHeartFailureProperty().bind(chkCHF.selectedProperty());
        model.hypertensionProperty().bind(chkHTN.selectedProperty());
        model.diabetesProperty().bind(chkDM.selectedProperty());
        model.strokeProperty().bind(chkStroke.selectedProperty());
        model.cardiovascularProperty().bind(chkCV.selectedProperty());
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);
        cmbGender.valueProperty().removeListener(genderListener);

        chkCHF.selectedProperty().removeListener(chfListener);
        chkHTN.selectedProperty().removeListener(htnListener);
        chkDM.selectedProperty().removeListener(dmListener);
        chkStroke.selectedProperty().removeListener(strokeListener);
        chkCV.selectedProperty().removeListener(cvListener);

        model.resultProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbind();
        model.congestiveHeartFailureProperty().unbind();
        model.hypertensionProperty().unbind();
        model.diabetesProperty().unbind();
        model.strokeProperty().unbind();
        model.cardiovascularProperty().unbind();
    }

    private void calculate() {
        model.calc();
        ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 1, 1.5);
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 750; }

    @Override
    public double getDefaultHeight() { return 500; }
}
