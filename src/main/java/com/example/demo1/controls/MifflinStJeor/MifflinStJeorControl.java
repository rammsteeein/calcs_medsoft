package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MifflinStJeorControl extends BorderPane {

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

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Формула Миффлина-Сан Жеора"),
                cmbGender, txtWeight, txtHeight, txtAge, txtResult
        );
        leftBox.setAlignment(Pos.TOP_LEFT);

        Label lblDescription = CalculatorDescription.createDescription(
                "Формула Миффлина-Сан Жеора используется для расчёта базального уровня метаболизма (BMR).\n\n" +
                        "BMR показывает, сколько энергии тратит организм в состоянии покоя. " +
                        "Формула учитывает вес, рост, возраст и пол."
        );

        VBox rightBox = new VBox(lblDescription);
        rightBox.setAlignment(Pos.CENTER);

        this.setLeft(leftBox);
        this.setCenter(rightBox);
        leftBox.setPrefWidth(250);

        this.setPrefSize(700, 600);
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

        txtResult.textProperty().bind(model.calculationProperty());
    }
}
