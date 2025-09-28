package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HSIControl extends StackPane {

    private final HSIModel model;

    private TextField txtALT;
    private TextField txtAST;
    private TextField txtBMI;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkDiabetes;
    private TextArea txtResult;

    public HSIControl(HSIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtALT = new TextField();
        txtALT.setPromptText("Введите АЛТ (Ед/л)");

        txtAST = new TextField();
        txtAST.setPromptText("Введите АСТ (Ед/л)");

        txtBMI = new TextField();
        txtBMI.setPromptText("Введите ИМТ");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");
        cmbGender.setValue(Gender.MALE);

        chkDiabetes = new CheckBox("Сахарный диабет");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс стеатоза печени (HSI)"),
                new VBox(new Label("АЛТ (Ед/л)"), txtALT),
                new VBox(new Label("АСТ (Ед/л)"), txtAST),
                new VBox(new Label("ИМТ"), txtBMI),
                new VBox(new Label("Пол"), cmbGender),
                chkDiabetes,
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "HSI (Hepatic Steatosis Index) — это простой показатель для оценки " +
                                "вероятности неалкогольной жировой болезни печени (NAFLD) без проведения биопсии.\n\n" +
                                "Формула:\n" +
                                "HSI = 8 × (АЛТ / АСТ) + ИМТ\n" +
                                "    + 2, если пациент женщина\n" +
                                "    + 2, если у пациента сахарный диабет\n\n" +
                                "Интерпретация:\n" +
                                "- Используется для первичного скрининга."
                )
        ));
    }

    private void bind() {
        txtALT.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAlt(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtAST.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAst(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtBMI.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setBmi(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> model.calc());

        chkDiabetes.selectedProperty().bindBidirectional(model.hasDiabetesProperty());
        chkDiabetes.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());

        txtResult.textProperty().bind(model.resultProperty());
    }
}
