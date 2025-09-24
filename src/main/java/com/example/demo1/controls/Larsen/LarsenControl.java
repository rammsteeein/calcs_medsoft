package com.example.demo1.controls.Larsen;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LarsenControl extends StackPane implements AutoCloseable {
    private LarsenModel model = LarsenModel.builder().build();

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

        this.getChildren().add(new VBox(10.0, new Node[]{
                CalculatorHeader.createHeader("Оценка кардиотоксичности перед противоопухолевой терапией (по Larsen CM, 2017)"),
                cmbDrug, cbCardiomegalia, cbIBS, cbAG, cbSD,
                cbAnthraHistory, cbRadiation, cbAge, cbFemale,
                btnCalc, txtResult
        }));
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
        } catch (Exception ex) {
            txtResult.setText("Ошибка: " + ex.getMessage());
        }

        txtResult.setText(model.resultProperty().get());
    }

    @Override
    public void close() {
        unbind();
    }
}
