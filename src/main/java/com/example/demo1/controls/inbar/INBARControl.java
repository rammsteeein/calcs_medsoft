package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class INBARControl extends StackPane implements Closeable {
    private final INBARModel model;

    private TextField nmrAge;
    private TextField nmrWeight;
    private ComboBox<Gender> cmbGender;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

    public INBARControl(INBARModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        nmrWeight = new TextField();
        nmrWeight.setPromptText("Вес (кг)");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setValue(Gender.MALE);

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Расчет максимальной ЧСС по формуле Inbar"),
                nmrAge, nmrWeight, cmbGender, btnCalc, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Формула Inbar используется для оценки максимальной частоты сердечных сокращений (ЧССmax).\n\n" +
                                "Для мужчин: ЧССmax = 210 - возраст - (0,11 × вес) + 4\n" +
                                "Для женщин: ЧССmax = 210 - возраст - (0,11 × вес)\n\n" +
                                "Простая альтернатива: ЧССmax = 220 - возраст\n\n" +
                                "Применение:\n" +
                                "- Подбор тренировочных зон\n" +
                                "- Оценка кардиореспираторной выносливости"
                )
        ));
    }

    private void bind() {
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculateResult() {
        model.calc();
    }

    @Override
    public void close() {
        unbind();
    }
}
