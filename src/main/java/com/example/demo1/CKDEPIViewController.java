package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CKDEPIViewController {

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private TextField nmrKreatinin;

    @FXML
    private ComboBox<String> cmbCreatininUnit;

    @FXML
    private TextField nmrAge;

    @FXML
    private Button btnCalc;

    @FXML
    private TextArea txtResult;

    @FXML
    public void initialize() {
        // Обработчик для кнопки
        btnCalc.setOnAction(e -> calculateResult());
    }

    private void calculateResult() {
        try {
            String gender = cmbGender.getValue();
            double kreatinin = Double.parseDouble(nmrKreatinin.getText());
            String creatininUnit = cmbCreatininUnit.getValue();
            int age = Integer.parseInt(nmrAge.getText());

            CKDEPIResult result = CKDEPICalculator.calc(gender, kreatinin, creatininUnit, age);
            txtResult.setText(result.toString());
        } catch (Exception ex) {
            txtResult.setText("Ошибка ввода: " + ex.getMessage());
        }
    }
}
