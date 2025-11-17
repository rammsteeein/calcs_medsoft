package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class MifflinStJeorControl extends BorderPane implements CalculatorControl, Closeable {

    private final MifflinStJeorModel model;

    private ComboBox<Gender> cmbGender;
    private TextField txtWeight;
    private TextField txtHeight;
    private TextField txtAge;
    private TextArea txtResult;

    public MifflinStJeorControl(MifflinStJeorModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        txtWeight = new TextField(); txtWeight.setPromptText("Вес (кг)");
        txtHeight = new TextField(); txtHeight.setPromptText("Рост (см)");
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Формула Миффлина-Сан Жеора"),
                cmbGender, txtWeight, txtHeight, txtAge, txtResult
        );
        leftBox.setPrefWidth(250);

        Label lblDescription = CalculatorDescription.createDescription(
                "Формула Миффлина-Сан Жеора используется для расчёта базального уровня метаболизма (BMR).\n\n" +
                        "BMR показывает, сколько энергии тратит организм в состоянии покоя. " +
                        "Формула учитывает вес, рост, возраст и пол."
        );

        VBox rightBox = new VBox(lblDescription);
        rightBox.setPrefWidth(400);

        this.setLeft(leftBox);
        this.setCenter(rightBox);
        this.setPrefSize(700, 600);
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        txtResult.textProperty().bind(model.calculationProperty());
    }

    private void addListeners() {
        cmbGender.valueProperty().addListener((obs, oldV, newV) -> model.calc());

        txtWeight.textProperty().addListener((obs, oldV, newV) -> {
            try { model.setWeight(Double.parseDouble(newV)); } catch (Exception ignored) {}
            model.calc();
        });

        txtHeight.textProperty().addListener((obs, oldV, newV) -> {
            try { model.setHeight(Double.parseDouble(newV)); } catch (Exception ignored) {}
            model.calc();
        });

        txtAge.textProperty().addListener((obs, oldV, newV) -> {
            try { model.setAge(Integer.parseInt(newV)); } catch (Exception ignored) {}
            model.calc();
        });
    }

    private void removeListeners() {
        cmbGender.valueProperty().removeListener((obs, oldV, newV) -> model.calc());
    }

    @Override
    public void close() {
        removeListeners();
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
    }
}
