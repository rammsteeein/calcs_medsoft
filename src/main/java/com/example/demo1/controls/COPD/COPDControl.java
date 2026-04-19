package com.example.demo1.controls.COPD;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class COPDControl extends StackPane implements AutoCloseable, CalculatorControl {

    private COPDModel model;

    private ComboBox<String> cmbAge;
    private Spinner<Integer> spnPackYears;
    private TextField txtWeight;
    private TextField txtHeight;
    private Label lblBMI;
    private ComboBox<String> cmbWeatherCough;
    private ComboBox<String> cmbCoughWithPhlegm;
    private ComboBox<String> cmbMorningCough;
    private ComboBox<String> cmbDyspnea;
    private ComboBox<String> cmbAllergy;
    private TextArea txtResult;

    private final ChangeListener<String> ageListener = (o, ov, nv) -> { model.ageProperty().set(nv); calculate(); };
    private final ChangeListener<Number> packYearsListener = (o, ov, nv) -> { model.packYearsProperty().set(nv.intValue()); calculate(); };
    private final ChangeListener<String> weightListener = (o, ov, nv) -> { updateBMI(); calculate(); };
    private final ChangeListener<String> heightListener = (o, ov, nv) -> { updateBMI(); calculate(); };
    private final ChangeListener<String> weatherCoughListener = (o, ov, nv) -> { model.weatherCoughProperty().set(nv); calculate(); };
    private final ChangeListener<String> coughWithPhlegmListener = (o, ov, nv) -> { model.coughWithPhlegmProperty().set(nv); calculate(); };
    private final ChangeListener<String> morningCoughListener = (o, ov, nv) -> { model.morningCoughProperty().set(nv); calculate(); };
    private final ChangeListener<String> dyspneaListener = (o, ov, nv) -> { model.dyspneaProperty().set(nv); calculate(); };
    private final ChangeListener<String> allergyListener = (o, ov, nv) -> { model.allergyProperty().set(nv); calculate(); };

    private final ChangeListener<Number> resultListener = (o, ov, nv) ->
            ResultStyler.applyStyleForValue(txtResult, nv.intValue(), 17, 16);

    public COPDControl(COPDModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    // ... остальной код метода initialize() без изменений ...
    private void initialize() {
        cmbAge = createCombo("Возраст",
                "40 – 49 лет",
                "50 – 59 лет",
                "60 – 69 лет",
                "70 лет и старше");

        spnPackYears = new Spinner<>(0, 100, 0);
        spnPackYears.setEditable(true);
        spnPackYears.setMaxWidth(Double.MAX_VALUE);
        spnPackYears.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                spnPackYears.getValueFactory().setValue(value);
                model.packYearsProperty().set(value);
                calculate();
            } catch (NumberFormatException ignored) {}
        });

        txtWeight = new TextField();
        txtWeight.setPromptText("Вес (кг)");
        txtWeight.setMaxWidth(Double.MAX_VALUE);

        txtHeight = new TextField();
        txtHeight.setPromptText("Рост (см)");
        txtHeight.setMaxWidth(Double.MAX_VALUE);

        lblBMI = new Label("ИМТ: —");
        lblBMI.setStyle("-fx-text-fill: #666;");

        HBox weightHeightBox = new HBox(10, txtWeight, txtHeight);
        weightHeightBox.setMaxWidth(Double.MAX_VALUE);

        cmbWeatherCough = createCombo("Плохая погода вызывает кашель", "Да", "Нет");
        cmbCoughWithPhlegm = createCombo("Кашель с мокротой вне простуды", "Да", "Нет", "У меня нет кашля");
        cmbMorningCough = createCombo("Кашель с мокротой по утрам", "Да", "Нет");
        cmbDyspnea = createCombo("Как часто возникает одышка", "Никогда", "Иногда или чаще");
        cmbAllergy = createCombo("Аллергия", "Да", "Нет");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefRowCount(5);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Опросник для диагностики ХОБЛ"),
                new Label("1. Возраст:"), cmbAge,
                new Label("2. Курение (пачка-лет):"), spnPackYears,
                new Label("3. Вес и рост:"), weightHeightBox, lblBMI,
                new Separator(),
                new Label("4. Плохая погода вызывает кашель:"), cmbWeatherCough,
                new Label("5. Кашель с мокротой вне простуды:"), cmbCoughWithPhlegm,
                new Label("6. Кашель с мокротой по утрам:"), cmbMorningCough,
                new Label("7. Частота одышки:"), cmbDyspnea,
                new Label("8. Аллергия:"), cmbAllergy,
                new Separator(),
                new Label("Результат:"), txtResult
        );
        left.setPadding(new Insets(10));
        left.setPrefWidth(450);

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Опросник для оценки вероятности ХОБЛ (хронической обструктивной болезни лёгких).\n" +
                                "\n" +
                                "Используется для первичного скрининга и выявления пациентов с повышенным риском заболевания.\n" +
                                "\n" +
                                "Учитываются:\n" +
                                "• возраст  \n" +
                                "• стаж курения (пачка-лет)  \n" +
                                "• индекс массы тела (ИМТ)  \n" +
                                "• кашель (в т.ч. с мокротой и утренний)  \n" +
                                "• одышка  \n" +
                                "• аллергия  \n" +
                                "\n" +
                                "Пачка-лет = (сигареты в день / 20) × годы курения  \n" +
                                "ИМТ рассчитывается автоматически: вес (кг) / рост² (м²)\n" +
                                "\n" +
                                "Интерпретация:\n" +
                                "• ≥17 баллов — ХОБЛ вероятен, рекомендуется спирометрия  \n" +
                                "• ≤16 баллов — рассмотрите другие заболевания (например, астму)\n" +
                                "\n" +
                                "Важно: опросник не заменяет диагностику врача.\n" +
                                "Источник: Chronic Airways Diseases, 2005"
                )
        ));
    }

    private ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getItems().addAll(items);
        cmb.setPromptText(prompt);
        cmb.setMaxWidth(Double.MAX_VALUE);
        return cmb;
    }

    private void updateBMI() {
        try {
            double weight = Double.parseDouble(txtWeight.getText().trim());
            double heightCm = Double.parseDouble(txtHeight.getText().trim());

            if (weight > 0 && heightCm > 0) {
                double heightM = heightCm / 100.0;
                double bmi = weight / (heightM * heightM); // ✅ ВОТ ТУТ ФИКС

                lblBMI.setText(String.format("ИМТ: %.1f", bmi));
                model.weightProperty().set(weight);
                model.heightCmProperty().set(heightCm);

                return;
            }
        } catch (NumberFormatException ignored) {}

        lblBMI.setText("ИМТ: —");
    }

    private void bind() {
        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultValueProperty().addListener(resultListener);
    }

    private void addListeners() {
        cmbAge.valueProperty().addListener(ageListener);
        spnPackYears.valueProperty().addListener(packYearsListener);
        txtWeight.textProperty().addListener(weightListener);
        txtHeight.textProperty().addListener(heightListener);
        cmbWeatherCough.valueProperty().addListener(weatherCoughListener);
        cmbCoughWithPhlegm.valueProperty().addListener(coughWithPhlegmListener);
        cmbMorningCough.valueProperty().addListener(morningCoughListener);
        cmbDyspnea.valueProperty().addListener(dyspneaListener);
        cmbAllergy.valueProperty().addListener(allergyListener);

        model.ageProperty().addListener((o, ov, nv) -> cmbAge.setValue(nv));
        model.packYearsProperty().addListener((o, ov, nv) -> {
            if (spnPackYears.getValueFactory() != null && nv != null) {
                spnPackYears.getValueFactory().setValue(nv.intValue());
            }
        });
        model.weatherCoughProperty().addListener((o, ov, nv) -> cmbWeatherCough.setValue(nv));
        model.coughWithPhlegmProperty().addListener((o, ov, nv) -> cmbCoughWithPhlegm.setValue(nv));
        model.morningCoughProperty().addListener((o, ov, nv) -> cmbMorningCough.setValue(nv));
        model.dyspneaProperty().addListener((o, ov, nv) -> cmbDyspnea.setValue(nv));
        model.allergyProperty().addListener((o, ov, nv) -> cmbAllergy.setValue(nv));
    }

    private void removeListeners() {
        cmbAge.valueProperty().removeListener(ageListener);
        spnPackYears.valueProperty().removeListener(packYearsListener);
        txtWeight.textProperty().removeListener(weightListener);
        txtHeight.textProperty().removeListener(heightListener);
        cmbWeatherCough.valueProperty().removeListener(weatherCoughListener);
        cmbCoughWithPhlegm.valueProperty().removeListener(coughWithPhlegmListener);
        cmbMorningCough.valueProperty().removeListener(morningCoughListener);
        cmbDyspnea.valueProperty().removeListener(dyspneaListener);
        cmbAllergy.valueProperty().removeListener(allergyListener);
        model.resultValueProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();
        ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 17, 16);
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 850;
    }

    @Override
    public double getDefaultHeight() {
        return 700;
    }
}