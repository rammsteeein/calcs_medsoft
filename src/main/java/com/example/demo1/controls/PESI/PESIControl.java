package com.example.demo1.controls.PESI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PESIControl extends StackPane {

    private final PESIModel model;

    private TextField txtAge, txtHeartRate, txtSystolicBP, txtRespiratoryRate, txtTemperature, txtOxygenSaturation;
    private ChoiceBox<Gender> choiceGender;
    private CheckBox chkCancer, chkCHF, chkChronicLung, chkAlteredMental;
    private TextArea txtResult;

    public PESIControl(PESIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (годы)");
        choiceGender = new ChoiceBox<>(); choiceGender.getItems().addAll(Gender.MALE, Gender.FEMALE);
        chkCancer = new CheckBox("Рак");
        chkCHF = new CheckBox("Хроническая сердечная недостаточность");
        chkChronicLung = new CheckBox("Хроническое заболевание легких");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("ЧСС (уд/мин)");
        txtSystolicBP = new TextField(); txtSystolicBP.setPromptText("Систолическое АД (мм рт.ст.)");
        txtRespiratoryRate = new TextField(); txtRespiratoryRate.setPromptText("Частота дыхания (раз/мин)");
        txtTemperature = new TextField(); txtTemperature.setPromptText("Температура (°С)");
        chkAlteredMental = new CheckBox("Нарушение сознания");
        txtOxygenSaturation = new TextField(); txtOxygenSaturation.setPromptText("Сатурация (%)");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("PESI"),
                txtAge, choiceGender, chkCancer, chkCHF, chkChronicLung,
                txtHeartRate, txtSystolicBP, txtRespiratoryRate, txtTemperature,
                chkAlteredMental, txtOxygenSaturation, txtResult
        );

        HBox container = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала PESI — прогноз 30-дневной летальности при ТЭЛА.\n\n" +
                                "Критерии:\n" +
                                "- Возраст: баллы = возраст\n" +
                                "- Мужской пол: +10\n" +
                                "- Рак: +30\n" +
                                "- ХСН: +10\n" +
                                "- Хроническое заболевание легких: +10\n" +
                                "- ЧСС ≥110: +20\n" +
                                "- Систолическое АД <100: +30\n" +
                                "- Частота дыхания >30: +20\n" +
                                "- Температура <36°C: +20\n" +
                                "- Нарушение сознания: +60\n" +
                                "- SpO₂ <90%: +20\n\n" +
                                "Классы риска:\n" +
                                "I: ≤65 — очень низкий\n" +
                                "II: 66–85 — низкий\n" +
                                "III: 86–105 — умеренный\n" +
                                "IV: 106–125 — высокий\n" +
                                "V: >125 — очень высокий"
                )
        );

        getChildren().add(container);
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> model.calc();

        choiceGender.valueProperty().bindBidirectional(model.genderProperty());
        chkCancer.selectedProperty().bindBidirectional(model.hasCancerProperty());
        chkCHF.selectedProperty().bindBidirectional(model.hasCHFProperty());
        chkChronicLung.selectedProperty().bindBidirectional(model.hasChronicLungDiseaseProperty());
        chkAlteredMental.selectedProperty().bindBidirectional(model.alteredMentalStatusProperty());

        choiceGender.valueProperty().addListener(listener);
        chkCancer.selectedProperty().addListener(listener);
        chkCHF.selectedProperty().addListener(listener);
        chkChronicLung.selectedProperty().addListener(listener);
        chkAlteredMental.selectedProperty().addListener(listener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> { try { model.ageProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {} model.calc(); });
        txtHeartRate.textProperty().addListener((obs, oldVal, newVal) -> { try { model.heartRateProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {} model.calc(); });
        txtSystolicBP.textProperty().addListener((obs, oldVal, newVal) -> { try { model.systolicBPProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {} model.calc(); });
        txtRespiratoryRate.textProperty().addListener((obs, oldVal, newVal) -> { try { model.respiratoryRateProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {} model.calc(); });
        txtTemperature.textProperty().addListener((obs, oldVal, newVal) -> { try { model.temperatureProperty().set(Double.parseDouble(newVal)); } catch (Exception ignored) {} model.calc(); });
        txtOxygenSaturation.textProperty().addListener((obs, oldVal, newVal) -> { try { model.oxygenSaturationProperty().set(Double.parseDouble(newVal)); } catch (Exception ignored) {} model.calc(); });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
