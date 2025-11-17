package com.example.demo1.controls.PESI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class PESIControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final PESIModel model;

    private TextField txtAge, txtHeartRate, txtSystolicBP, txtRespiratoryRate, txtTemperature, txtOxygenSaturation;
    private ChoiceBox<Gender> choiceGender;
    private CheckBox chkCancer, chkCHF, chkChronicLung, chkAlteredMental;
    private TextArea txtResult;

    private final List<ChangeListener<?>> listeners = new ArrayList<>();

    public PESIControl(PESIModel model) {
        this.model = model;
        initialize();
        addListeners();
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

    private void addListeners() {
        ChangeListener<Object> genericListener = (obs, o, n) -> model.calc();

        choiceGender.valueProperty().bindBidirectional(model.genderProperty());
        chkCancer.selectedProperty().bindBidirectional(model.hasCancerProperty());
        chkCHF.selectedProperty().bindBidirectional(model.hasCHFProperty());
        chkChronicLung.selectedProperty().bindBidirectional(model.hasChronicLungDiseaseProperty());
        chkAlteredMental.selectedProperty().bindBidirectional(model.alteredMentalStatusProperty());

        choiceGender.valueProperty().addListener(genericListener); listeners.add(genericListener);
        chkCancer.selectedProperty().addListener(genericListener); listeners.add(genericListener);
        chkCHF.selectedProperty().addListener(genericListener); listeners.add(genericListener);
        chkChronicLung.selectedProperty().addListener(genericListener); listeners.add(genericListener);
        chkAlteredMental.selectedProperty().addListener(genericListener); listeners.add(genericListener);

        ChangeListener<String> ageListener = (obs, o, n) -> { try { model.ageProperty().set(Integer.parseInt(n)); } catch (Exception ignored) {} model.calc(); };
        txtAge.textProperty().addListener(ageListener); listeners.add(ageListener);

        ChangeListener<String> hrListener = (obs, o, n) -> { try { model.heartRateProperty().set(Integer.parseInt(n)); } catch (Exception ignored) {} model.calc(); };
        txtHeartRate.textProperty().addListener(hrListener); listeners.add(hrListener);

        ChangeListener<String> sbpListener = (obs, o, n) -> { try { model.systolicBPProperty().set(Integer.parseInt(n)); } catch (Exception ignored) {} model.calc(); };
        txtSystolicBP.textProperty().addListener(sbpListener); listeners.add(sbpListener);

        ChangeListener<String> rrListener = (obs, o, n) -> { try { model.respiratoryRateProperty().set(Integer.parseInt(n)); } catch (Exception ignored) {} model.calc(); };
        txtRespiratoryRate.textProperty().addListener(rrListener); listeners.add(rrListener);

        ChangeListener<String> tempListener = (obs, o, n) -> { try { model.temperatureProperty().set(Double.parseDouble(n)); } catch (Exception ignored) {} model.calc(); };
        txtTemperature.textProperty().addListener(tempListener); listeners.add(tempListener);

        ChangeListener<String> spo2Listener = (obs, o, n) -> { try { model.oxygenSaturationProperty().set(Double.parseDouble(n)); } catch (Exception ignored) {} model.calc(); };
        txtOxygenSaturation.textProperty().addListener(spo2Listener); listeners.add(spo2Listener);

        model.setOnResultStyled(res -> {
            if (res.getRiskClass().contains("Очень низкий") || res.getRiskClass().contains("Низкий")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
            } else if (res.getRiskClass().contains("Умеренный")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            } else if (res.getRiskClass().contains("Высокий") || res.getRiskClass().contains("Очень высокий")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
            } else {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            }
        });

        txtResult.textProperty().bind(model.resultProperty());
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener((ChangeListener<? super String>) listeners.get(0));
        txtHeartRate.textProperty().removeListener((ChangeListener<? super String>) listeners.get(1));
        txtSystolicBP.textProperty().removeListener((ChangeListener<? super String>) listeners.get(2));
        txtRespiratoryRate.textProperty().removeListener((ChangeListener<? super String>) listeners.get(3));
        txtTemperature.textProperty().removeListener((ChangeListener<? super String>) listeners.get(4));
        txtOxygenSaturation.textProperty().removeListener((ChangeListener<? super String>) listeners.get(5));

        choiceGender.valueProperty().removeListener((ChangeListener<? super Gender>) listeners.get(6));
        chkCancer.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(7));
        chkCHF.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(8));
        chkChronicLung.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(9));
        chkAlteredMental.selectedProperty().removeListener((ChangeListener<? super Boolean>) listeners.get(10));

        listeners.clear();
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 800; }

    @Override
    public double getDefaultHeight() { return 500; }
}
