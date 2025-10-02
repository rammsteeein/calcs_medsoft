package com.example.demo1.controls.REACH;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class REACHControl extends StackPane {

    private final REACHModel model;

    private TextField txtAge;
    private CheckBox chkPeripheral, chkHF, chkDiabetes, chkCholesterol, chkHypertension, chkOAC;
    private ComboBox<String> cmbSmoking, cmbAntiplatelet;
    private TextArea txtResult;

    public REACHControl(REACHModel model) {
        this.model = model;
        initialize();
        bind();

        model.setOnResultStyled(res -> {
            if (res.getScore() <= 6) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
            else if (res.getScore() <= 10) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            else ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        });
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст");
        chkPeripheral = new CheckBox("Периферический атеросклероз");
        chkHF = new CheckBox("Сердечная недостаточность");
        chkDiabetes = new CheckBox("Диабет");
        chkCholesterol = new CheckBox("Гиперхолестеринемия");
        chkHypertension = new CheckBox("Артериальная гипертония");
        chkOAC = new CheckBox("Прием оральных антикоагулянтов");

        cmbSmoking = new ComboBox<>();
        cmbSmoking.getItems().addAll("Никогда", "Раньше", "Продолжает");
        cmbSmoking.getSelectionModel().select(0);

        cmbAntiplatelet = new ComboBox<>();
        cmbAntiplatelet.getItems().addAll("Нет", "Аспирин", "Другие", "Комбинация");
        cmbAntiplatelet.getSelectionModel().select(0);

        txtResult = new TextArea();
        txtResult.setEditable(false); txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала REACH"),
                txtAge, chkPeripheral, chkHF, chkDiabetes, chkCholesterol,
                chkHypertension, cmbSmoking, cmbAntiplatelet, chkOAC, txtResult
        );

        VBox rightBox = new VBox(
                CalculatorDescription.createDescription(
                        "Шкала REACH для оценки риска кровотечений у пациентов с атеросклерозом.\n" +
                                "Позволяет прогнозировать риск и корректировать терапию."
                )
        );

        this.getChildren().add(new HBox(20, leftBox, rightBox));
    }

    private void bind() {
        txtAge.textProperty().bindBidirectional(model.ageProperty());
        chkPeripheral.selectedProperty().bindBidirectional(model.peripheralAtherosclerosisProperty());
        chkHF.selectedProperty().bindBidirectional(model.heartFailureProperty());
        chkDiabetes.selectedProperty().bindBidirectional(model.diabetesProperty());
        chkCholesterol.selectedProperty().bindBidirectional(model.hypercholesterolemiaProperty());
        chkHypertension.selectedProperty().bindBidirectional(model.hypertensionProperty());
        chkOAC.selectedProperty().bindBidirectional(model.oralAnticoagulantProperty());

        cmbSmoking.getSelectionModel().selectedIndexProperty()
                .addListener((obs, o, n) -> { model.smokingStatusProperty().set(n.intValue()); model.calc(); });
        cmbAntiplatelet.getSelectionModel().selectedIndexProperty()
                .addListener((obs, o, n) -> { model.antiplateletProperty().set(n.intValue()); model.calc(); });

        txtAge.textProperty().addListener((obs, o, n) -> model.calc());
        chkPeripheral.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkHF.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkDiabetes.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkCholesterol.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkHypertension.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkOAC.selectedProperty().addListener((obs, o, n) -> model.calc());

        txtResult.textProperty().bind(model.resultProperty());
    }
}
