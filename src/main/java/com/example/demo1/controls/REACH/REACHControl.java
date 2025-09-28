package com.example.demo1.controls.REACH;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class REACHControl extends StackPane implements Closeable {

    private final REACHModel model;

    private TextField txtAge;
    private CheckBox chkPeripheral;
    private CheckBox chkHF;
    private CheckBox chkDiabetes;
    private CheckBox chkCholesterol;
    private CheckBox chkHypertension;
    private ComboBox<String> cmbSmoking;
    private ComboBox<String> cmbAntiplatelet;
    private CheckBox chkOAC;
    private TextArea txtResult;

    public REACHControl(REACHModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        chkPeripheral = new CheckBox("Периферический атеросклероз");
        chkHF = new CheckBox("Сердечная недостаточность");
        chkDiabetes = new CheckBox("Диабет");
        chkCholesterol = new CheckBox("Гиперхолестеринемия");
        chkHypertension = new CheckBox("Артериальная гипертония");

        cmbSmoking = new ComboBox<>();
        cmbSmoking.getItems().addAll("Никогда", "Раньше", "Продолжает");
        cmbSmoking.getSelectionModel().select(0);

        cmbAntiplatelet = new ComboBox<>();
        cmbAntiplatelet.getItems().addAll("Нет", "Аспирин", "Другие", "Комбинация");
        cmbAntiplatelet.getSelectionModel().select(0);

        chkOAC = new CheckBox("Прием оральных антикоагулянтов");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала REACH"),
                txtAge, chkPeripheral, chkHF, chkDiabetes,
                chkCholesterol, chkHypertension, cmbSmoking, cmbAntiplatelet, chkOAC, txtResult
        );

        this.getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала REACH (REduction of Atherothrombosis for Continued Health) создана для расчета риска" +
                                " серьезных кровотечений у пациентов с атеросклерозом," +
                                " не имеющих фибрилляции предсердий, острого коронарного синдрома," +
                                " не проходивших эндоваскулярного лечения (стентирования)." +
                                "\n" +
                                "\n" +
                                "Расчет по шкале выполнялся по данным наблюдения за 56 616 амбулаторными больными с" +
                                " признаками атеротромбоза (ИБС, цереброваскулярные заболевания, периферический атеросклероз)." +
                                " В регистре REACH учитывался прием антитромботических препаратов, что позволило" +
                                " определить дополнительные риски кровотечений, связанные с этим.\n" +
                                "\n" +
                                "Шкала может помочь клиницистам предвидеть риск серьезных кровотечений и подойти" +
                                " к решению вопроса подбора антитромботической терапии у амбулаторных больных."
                )
        ));
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

    @Override
    public void close() {
    }
}
