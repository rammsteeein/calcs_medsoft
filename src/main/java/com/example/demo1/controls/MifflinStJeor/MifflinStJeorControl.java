package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MifflinStJeorControl extends StackPane {

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
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");

        txtWeight = new TextField(); txtWeight.setPromptText("Вес (кг)");
        txtHeight = new TextField(); txtHeight.setPromptText("Рост (см)");
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10, cmbGender, txtWeight, txtHeight, txtAge, txtResult));
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());

        txtWeight.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setWeight(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtHeight.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setHeight(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
