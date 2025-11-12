package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class INBARControl extends StackPane implements Closeable, CalculatorControl {
    private final INBARModel model;

    private TextField nmrAge;
    private TextField nmrWeight;
    private ComboBox<Gender> cmbGender;
    private TextArea txtResult;

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

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Расчет максимальной ЧСС по формуле Inbar"),
                nmrAge, nmrWeight, cmbGender, txtResult
        );

        HBox mainBox = new HBox(20,
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
        );

        getChildren().add(mainBox);
    }

    private void bind() {
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        nmrWeight.textProperty().bindBidirectional(model.weightProperty());
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());

        nmrAge.textProperty().addListener((obs, o, n) -> calculate());
        nmrWeight.textProperty().addListener((obs, o, n) -> calculate());
        cmbGender.valueProperty().addListener((obs, o, n) -> calculate());

        model.resultProperty().addListener((obs, oldVal, newVal) -> txtResult.setText(newVal));
    }

    private void calculate() {
        try {
            if (nmrAge == null || nmrWeight == null || cmbGender == null
                    || nmrAge.getText() == null || nmrWeight.getText() == null
                    || nmrAge.getText().isEmpty() || nmrWeight.getText().isEmpty()
                    || cmbGender.getValue() == null) {
                model.resultProperty().set("Введите все поля");
                return;
            }
            model.calc();
        } catch (NumberFormatException e) {
            model.resultProperty().set("Некорректный ввод чисел");
        } catch (Exception e) {
            model.resultProperty().set("Ошибка: " + e.getMessage());
        }
    }

    private void unbind() {
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
    }

    @Override
    public void close() {
        unbind();
    }
}
