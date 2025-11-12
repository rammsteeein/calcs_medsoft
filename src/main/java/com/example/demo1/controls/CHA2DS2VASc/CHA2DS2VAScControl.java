package com.example.demo1.controls.CHA2DS2VASc;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CHA2DS2VAScControl extends StackPane implements CalculatorControl {

    private final CHA2DS2VAScModel model;

    private TextField txtAge;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkCHF, chkHTN, chkDM, chkStroke, chkCV;
    private TextArea txtResult;

    public CHA2DS2VAScControl(CHA2DS2VAScModel model) {
        this.model = model;
        initialize();
        bind();
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
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала CHA₂DS₂-VASc"),
                new VBox(new Label("Возраст (лет)"), txtAge),
                new VBox(new Label("Пол"), cmbGender),
                chkCHF,
                chkHTN,
                chkDM,
                chkStroke,
                chkCV,
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "CHA₂DS₂-VASc — шкала для оценки риска инсульта у пациентов с фибрилляцией предсердий.\n\n" +
                                "Баллы начисляются за:\n" +
                                "- Застойную сердечную недостаточность\n" +
                                "- Артериальную гипертензию\n" +
                                "- Возраст ≥65 или ≥75 лет\n" +
                                "- Сахарный диабет\n" +
                                "- Инсульт/ТИА\n" +
                                "- Сосудистые заболевания\n" +
                                "- Женский пол\n\n" +
                                "Чем выше сумма, тем выше риск тромбоэмболических осложнений."
                )
        ));
    }

    private void bind() {
        model.congestiveHeartFailureProperty().bind(chkCHF.selectedProperty());
        model.hypertensionProperty().bind(chkHTN.selectedProperty());
        model.diabetesProperty().bind(chkDM.selectedProperty());
        model.strokeProperty().bind(chkStroke.selectedProperty());
        model.cardiovascularProperty().bind(chkCV.selectedProperty());

        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> {
            model.setGender(newVal);
            calculate();
        });

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                model.setAge(Integer.parseInt(newVal));
            } catch (Exception ignored) {
                model.setAge(0);
            }
            calculate();
        });

        chkCHF.selectedProperty().addListener((obs, oldVal, newVal) -> calculate());
        chkHTN.selectedProperty().addListener((obs, oldVal, newVal) -> calculate());
        chkDM.selectedProperty().addListener((obs, oldVal, newVal) -> calculate());
        chkStroke.selectedProperty().addListener((obs, oldVal, newVal) -> calculate());
        chkCV.selectedProperty().addListener((obs, oldVal, newVal) -> calculate());

        txtResult.textProperty().bind(model.resultProperty());
    }
    private void calculate() {
        model.calc();
        double val = model.resultValueProperty().get();
        ResultStyler.applyStyleForValue(txtResult, val, 1, 1.5);
    }

    @Override
    public double getDefaultWidth() { return 750; }

    @Override
    public double getDefaultHeight() { return 500; }
}
