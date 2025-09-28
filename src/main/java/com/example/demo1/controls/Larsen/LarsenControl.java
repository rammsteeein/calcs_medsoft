package com.example.demo1.controls.Larsen;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LarsenControl extends StackPane implements AutoCloseable {
    private LarsenModel model;

    private ComboBox<String> cmbDrug;
    private CheckBox cbCardiomegalia, cbIBS, cbAG, cbSD, cbAnthraHistory, cbRadiation, cbAge, cbFemale;
    private Button btnCalc;
    private TextArea txtResult;

    public LarsenControl(LarsenModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbDrug = new ComboBox<>();
        cmbDrug.getItems().addAll(
                "Антрациклины", "Циклофосфан", "Ифосфамид", "Клофарабин", "Герцептин",
                "Доцетаксел", "Пертузумаб", "Сунитиниб", "Сорафениб",
                "Бевацизумаб", "Дазатиниб", "Иматиниб", "Лапатиниб",
                "Этопозид", "Ритуксимаб", "Талидомид"
        );
        cmbDrug.setPromptText("Выберите препарат");

        cbCardiomegalia = new CheckBox("Кардиомегалия или ХСН");
        cbIBS = new CheckBox("ИБС/эквивалент (ЗПА)");
        cbAG = new CheckBox("АГ");
        cbSD = new CheckBox("СД");
        cbAnthraHistory = new CheckBox("Лечение антрациклинами в анамнезе");
        cbRadiation = new CheckBox("Предшествующая или сочетанная лучевая терапия на грудную клетку");
        cbAge = new CheckBox("Возраст <15 или >65 лет");
        cbFemale = new CheckBox("Женский пол");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Оценка кардиотоксичности перед противоопухолевой терапией (по Larsen CM, 2017)"),
                cmbDrug, cbCardiomegalia, cbIBS, cbAG, cbSD,
                cbAnthraHistory, cbRadiation, cbAge, cbFemale,
                btnCalc, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Методика Larsen CM (2017) применяется для оценки риска развития кардиотоксичности " +
                                "у пациентов перед началом противоопухолевой терапии.\n\n" +
                                "В расчет включаются:\n" +
                                "- Вид применяемого препарата (антрациклины, таргетная терапия и др.)\n" +
                                "- Наличие сердечно-сосудистых заболеваний (кардиомегалия/ХСН, ИБС, АГ, СД)\n" +
                                "- История предыдущего лечения (антрациклины, лучевая терапия)\n" +
                                "- Факторы риска (возраст <15 или >65 лет, женский пол)\n\n" +
                                "Применение:\n" +
                                "- Индивидуальная оценка риска осложнений перед выбором схемы лечения\n" +
                                "- Определение необходимости кардиологического наблюдения\n" +
                                "- Сравнение рисков при использовании различных противоопухолевых препаратов"
                )
        ));
    }

    private void bind() {
        cmbDrug.valueProperty().bindBidirectional(model.drugProperty());
    }

    private void unbind() {
        cmbDrug.valueProperty().unbindBidirectional(model.drugProperty());
    }

    private void calculateResult() {
        model.getPatientFactors().clear();
        if (cbCardiomegalia.isSelected()) model.getPatientFactors().add(cbCardiomegalia.getText());
        if (cbIBS.isSelected()) model.getPatientFactors().add(cbIBS.getText());
        if (cbAG.isSelected()) model.getPatientFactors().add(cbAG.getText());
        if (cbSD.isSelected()) model.getPatientFactors().add(cbSD.getText());
        if (cbAnthraHistory.isSelected()) model.getPatientFactors().add(cbAnthraHistory.getText());
        if (cbRadiation.isSelected()) model.getPatientFactors().add(cbRadiation.getText());
        if (cbAge.isSelected()) model.getPatientFactors().add(cbAge.getText());
        if (cbFemale.isSelected()) model.getPatientFactors().add(cbFemale.getText());

        try {
            model.calc();
            txtResult.setText(model.calculationProperty().get());
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }
}
