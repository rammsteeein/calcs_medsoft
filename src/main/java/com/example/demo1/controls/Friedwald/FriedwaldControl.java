package com.example.demo1.controls.Friedwald;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class FriedwaldControl extends StackPane implements Closeable, CalculatorControl {
    private final FriedwaldModel model;

    private TextField nmrTotalChol;
    private TextField nmrTriglycerides;
    private TextField nmrHDL;
    private TextArea txtResult;

    public FriedwaldControl(FriedwaldModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        nmrTotalChol = new TextField();
        nmrTotalChol.setPromptText("Общий холестерин (ммоль/л)");

        nmrTriglycerides = new TextField();
        nmrTriglycerides.setPromptText("Триглицериды (ммоль/л)");

        nmrHDL = new TextField();
        nmrHDL.setPromptText("ХС ЛПВП (ммоль/л)");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Расчёт ХС ЛПНП по формуле Фридвальда"),
                nmrTotalChol, nmrTriglycerides, nmrHDL, txtResult
        );

        HBox mainBox = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Назначение: Оценка концентрации липопротеинов низкой плотности (ЛПНП) в крови.\n\n" +
                                "Формула Фридвальда:\n" +
                                "ХС ЛПНП = Общий холестерин - (Триглицериды / 2.2) - ХС ЛПВП\n\n" +
                                "Примечания:\n" +
                                "- Формула справедлива при уровне триглицеридов < 4.5 ммоль/л.\n" +
                                "- Единицы измерения: ммоль/л.\n" +
                                "- Применяется в клинической практике для расчёта показателя \"плохого\" холестерина."
                )
        );

        getChildren().add(mainBox);
    }

    private void bind() {
        nmrTotalChol.textProperty().bindBidirectional(model.totalCholProperty());
        nmrTriglycerides.textProperty().bindBidirectional(model.triglyceridesProperty());
        nmrHDL.textProperty().bindBidirectional(model.hdlProperty());

        nmrTotalChol.textProperty().addListener((obs, o, n) -> calculate());
        nmrTriglycerides.textProperty().addListener((obs, o, n) -> calculate());
        nmrHDL.textProperty().addListener((obs, o, n) -> calculate());


        model.resultProperty().addListener((obs, oldVal, newVal) -> txtResult.setText(newVal));
    }

    private void calculate() {
        if (nmrTotalChol.getText().isEmpty() || nmrTriglycerides.getText().isEmpty() || nmrHDL.getText().isEmpty()) {
            model.setResult("Введите все поля для расчёта");
            return;
        }

        try {
            Double.parseDouble(nmrTotalChol.getText());
            Double.parseDouble(nmrTriglycerides.getText());
            Double.parseDouble(nmrHDL.getText());

            model.calc();
        } catch (NumberFormatException e) {
            model.setResult("Некорректный ввод чисел");
        } catch (Exception e) {
            model.setResult("Ошибка: " + e.getMessage());
        }
    }

    private void unbind() {
        nmrTotalChol.textProperty().unbindBidirectional(model.totalCholProperty());
        nmrTriglycerides.textProperty().unbindBidirectional(model.triglyceridesProperty());
        nmrHDL.textProperty().unbindBidirectional(model.hdlProperty());
    }

    @Override
    public void close() {
        unbind();
    }

    @Override
    public double getDefaultHeight() {
        return 400;
    }
}
