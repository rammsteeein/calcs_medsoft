package com.example.demo1.controls.POAK_doze;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class POAKControl extends StackPane implements Closeable, CalculatorControl {
    private final POAKModel model;
    private TextField nmrKreatinin;
    private Button btnCalc;
    private TextArea txtResult;

    private static final String BUTTON_TEXT = "Рассчитать";

    public POAKControl(POAKModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrKreatinin = new TextField();
        nmrKreatinin.setPromptText("Клиренс креатинина (мл/мин)");

        btnCalc = new Button(BUTTON_TEXT);
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(
                new HBox(
                        20,
                        new VBox(
                                10,
                                CalculatorHeader.createHeader("Выбор дозы ПОАК"),
                                nmrKreatinin,
                                btnCalc,
                                txtResult
                        ),
                        CalculatorDescription.createDescription(
                                "Формула позволяет определить периодичность наблюдения за пациентами,\n" +
                                        "получающими ПОАК, в зависимости от клиренса креатинина.\n\n" +
                                        "Формула:\n" +
                                        "N = клиренс креатинина / 10\n\n" +
                                        "Условие применения:\n" +
                                        "- Только если клиренс креатинина < 60 мл/мин\n\n" +
                                        "Результат:\n" +
                                        "- N — количество месяцев между наблюдениями"
                        )
                )
        );
    }

    private void bind() {
        nmrKreatinin.textProperty().bindBidirectional(model.kreatininProperty());
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void unbind() {
        nmrKreatinin.textProperty().unbindBidirectional(model.kreatininProperty());
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
}
