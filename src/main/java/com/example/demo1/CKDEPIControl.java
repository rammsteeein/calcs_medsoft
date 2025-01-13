package com.example.demo1;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

/**
 * Контрол для расчета CKDEPI.
 */
public class CKDEPIControl extends StackPane implements Closeable {

    private ComboBox<Gender> cmbGender;
    private TextField nmrKreatinin;
    private ComboBox<CreatininUnit> cmbCreatininUnit;
    private TextField nmrAge;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String GENDER_TEXT = "Пол:";
    private static final String BUTTON_TEXT = "Рассчитать";

    private CKDEPIModel model;

    public CKDEPIControl(CKDEPIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText(GENDER_TEXT);

        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Креатинин");

        cmbCreatininUnit = new ComboBox<>();
        cmbCreatininUnit.getItems().addAll(CreatininUnit.values());
        cmbCreatininUnit.setPromptText("Ед. измерения");

        nmrAge = new TextField();
        nmrAge.setPromptText("Возраст");

        btnCalc = new Button();
        btnCalc.setText(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, cmbGender, nmrKreatinin, cmbCreatininUnit, nmrAge, btnCalc, txtResult));
    }

    private void bind() {
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        cmbGender.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                model.setGender(newVal);
            }
        });

        cmbCreatininUnit.valueProperty().bindBidirectional(model.creatininUnitProperty());
        cmbCreatininUnit.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                model.setCreatininUnit(newVal);
            }
        });

        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        nmrAge.textProperty().bindBidirectional(model.ageProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        cmbGender.valueProperty().unbindBidirectional(model.genderProperty());
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
        cmbCreatininUnit.valueProperty().unbindBidirectional(model.creatininUnitProperty());
        nmrAge.textProperty().unbindBidirectional(model.ageProperty());
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculateResult() {
        try {
            model.calc();
        } catch (Exception ex) {
            txtResult.setText("Ошибка ввода: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        unbind();
    }

    /**
     * Модель для контрола CKDEPI.
     */
    public static class CKDEPIModel {
        private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
        private final StringProperty kreatinin = new SimpleStringProperty();
        private final ObjectProperty<CreatininUnit> creatininUnit = new SimpleObjectProperty<>();
        private final StringProperty age = new SimpleStringProperty();
        private final StringProperty result = new SimpleStringProperty();

        public Gender getGender() {
            return gender.get();
        }

        public void setGender(Gender gender) {
            this.gender.set(gender);
        }

        public ObjectProperty<Gender> genderProperty() {
            return gender;
        }

        public String getKreatinin() {
            return kreatinin.get();
        }

        public void setKreatinin(String kreatinin) {
            this.kreatinin.set(kreatinin);
        }

        public StringProperty kreatininProperty() {
            return kreatinin;
        }

        public CreatininUnit getCreatininUnit() {
            return creatininUnit.get();
        }

        public void setCreatininUnit(CreatininUnit creatininUnit) {
            this.creatininUnit.set(creatininUnit);
        }

        public ObjectProperty<CreatininUnit> creatininUnitProperty() {
            return creatininUnit;
        }

        public String getAge() {
            return age.get();
        }

        public void setAge(String age) {
            this.age.set(age);
        }

        public StringProperty ageProperty() {
            return age;
        }

        public String getResult() {
            return result.get();
        }

        public void setResult(String result) {
            this.result.set(result);
        }

        public StringProperty resultProperty() {
            return result;
        }

        public void calc() {
            try {
                Gender genderValue = getGender();
                double kreatininValue = Double.parseDouble(getKreatinin());
                CreatininUnit creatininUnitValue = getCreatininUnit();
                int ageValue = Integer.parseInt(getAge());

                CKDEPIResult calcResult = CKDEPICalculator.calc(genderValue, kreatininValue, creatininUnitValue, ageValue);
                setResult(calcResult.toString());
            } catch (Exception e) {
                setResult("Ошибка: " + e.getMessage());
            }
        }
    }
}