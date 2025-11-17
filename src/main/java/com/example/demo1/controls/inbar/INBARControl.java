package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class INBARControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final INBARModel model;

    private TextField nmrAge;
    private TextField nmrWeight;
    private ComboBox<Gender> cmbGender;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (obs, oldVal, newVal) -> calculate();
    private final ChangeListener<String> weightListener = (obs, oldVal, newVal) -> calculate();
    private final ChangeListener<Gender> genderListener = (obs, oldVal, newVal) -> calculate();

    public INBARControl(INBARModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
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

        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {
        nmrAge.textProperty().addListener(ageListener);
        nmrWeight.textProperty().addListener(weightListener);
        cmbGender.valueProperty().addListener(genderListener);
    }

    private void removeListeners() {
        nmrAge.textProperty().removeListener(ageListener);
        nmrWeight.textProperty().removeListener(weightListener);
        cmbGender.valueProperty().removeListener(genderListener);
    }

    private void calculate() {
        String ageText = nmrAge.getText();
        String weightText = nmrWeight.getText();
        Gender genderValue = cmbGender.getValue();

        if (ageText == null || ageText.isBlank() ||
                weightText == null || weightText.isBlank() ||
                genderValue == null) {
            txtResult.setText("Введите все поля");
            return;
        }

        try {
            double ageValue = Double.parseDouble(ageText);
            double weightValue = Double.parseDouble(weightText);
            INBARResult result = INBARCalculator.calc(ageValue, weightValue, genderValue);
            txtResult.setText(result.toString());
        } catch (NumberFormatException e) {
            txtResult.setText("Некорректный ввод чисел");
        } catch (Exception e) {
            txtResult.setText("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        removeListeners();
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        nmrWeight.textProperty().unbindBidirectional(model.weightProperty());
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        txtResult.textProperty().unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 720;
    }

    @Override
    public double getDefaultHeight() {
        return 460;
    }
}
