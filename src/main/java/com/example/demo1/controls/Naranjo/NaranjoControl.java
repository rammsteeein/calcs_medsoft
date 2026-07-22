package com.example.demo1.controls.Naranjo;


import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class NaranjoControl extends StackPane
        implements AutoCloseable, CalculatorControl {

    private final NaranjoModel model;
    private ToggleGroup tg1;
    private ToggleGroup tg2;
    private ToggleGroup tg3;
    private ToggleGroup tg4;
    private ToggleGroup tg5;
    private ToggleGroup tg6;
    private ToggleGroup tg7;
    private ToggleGroup tg8;
    private ToggleGroup tg9;
    private ToggleGroup tg10;
    private TextArea txtResult;

    public NaranjoControl(NaranjoModel model) {

        this.model = model;

        initialize();
        bind();
        addListeners();
    }

    private void initialize() {


        tg1 = new ToggleGroup();
        tg2 = new ToggleGroup();
        tg3 = new ToggleGroup();
        tg4 = new ToggleGroup();
        tg5 = new ToggleGroup();

        tg6 = new ToggleGroup();
        tg7 = new ToggleGroup();
        tg8 = new ToggleGroup();
        tg9 = new ToggleGroup();
        tg10 = new ToggleGroup();

        VBox left = new VBox(
                12,

                CalculatorHeader.createHeader(
                        "Шкала Наранжо"
                ),


                createQuestion(
                        "1. Были ли ранее достоверные сообщения об этой реакции?",
                        tg1
                ),


                createQuestion(
                        "2. Реакция возникла после введения подозреваемого лекарства?",
                        tg2
                ),


                createQuestion(
                        "3. Улучшилось ли состояние после отмены препарата или введения специфического антагониста?",
                        tg3
                ),


                createQuestion(
                        "4. Возобновилась ли побочная реакция после повторного введения препарата?",
                        tg4
                ),


                createQuestion(
                        "5. Есть ли другие причины, кроме лекарства, которые могли вызвать реакцию?",
                        tg5
                ),


                createQuestion(
                        "6. Возобновилась ли реакция при использовании плацебо?",
                        tg6
                ),


                createQuestion(
                        "7. Было ли лекарство обнаружено в крови в токсической концентрации?",
                        tg7
                ),


                createQuestion(
                        "8. Была ли реакция тяжелее после увеличения дозы или легче после уменьшения?",
                        tg8
                ),


                createQuestion(
                        "9. Отмечалась ли аналогичная реакция ранее на этот или подобный препарат?",
                        tg9
                ),


                createQuestion(
                        "10. Была ли побочная реакция подтверждена объективно?",
                        tg10
                )

        );


        txtResult = new TextArea();

        txtResult.setEditable(false);

        txtResult.setWrapText(true);

        left.getChildren().add(txtResult);

        ScrollPane scrollPane = new ScrollPane(left);

        scrollPane.setFitToWidth(true);



        getChildren().add(
                new HBox(
                        20,

                        scrollPane,


                        CalculatorDescription.createDescription(

                                "Шкала Наранжо (Naranjo Adverse Drug Reaction Probability Scale) — " +
                                        "клинико-фармакологический инструмент для оценки вероятности причинно-следственной " +
                                        "связи между применением лекарственного препарата и возникновением побочной " +
                                        "реакции (нежелательного лекарственного явления).\n\n" +

                                        "Методика разработана в 1981 году группой исследователей во главе с " +
                                        "С. Наранжо и предназначена для стандартизированной оценки подозреваемых " +
                                        "нежелательных реакций на лекарственные средства.\n\n" +

                                        "Интерпретация результатов:\n\n" +

                                        "9 баллов и выше — определенно: причинно-следственная связь установлена.\n\n" +

                                        "5-8 баллов — вероятно: связь между препаратом и реакцией имеет высокую вероятность.\n\n" +

                                        "1-4 балла — возможно: связь допускается, но имеются недостаточные доказательства.\n\n" +

                                        "0 баллов — сомнительно: причинно-следственная связь не подтверждена.\n\n" +

                                        "Шкала Наранжо используется в клинической практике и научных исследованиях " +
                                        "для унифицированной оценки безопасности лекарственной терапии."

                        )
                )
        );
    }

    private VBox createQuestion(
            String text,
            ToggleGroup group
    ) {


        Label label = new Label(text);


        RadioButton yes =
                new RadioButton("Да");

        RadioButton no =
                new RadioButton("Нет");

        RadioButton unknown =
                new RadioButton("Не знаю");


        yes.setToggleGroup(group);
        no.setToggleGroup(group);
        unknown.setToggleGroup(group);


        return new VBox(
                4,
                label,
                yes,
                no,
                unknown
        );
    }
    private void bind() {

        txtResult.textProperty()
                .bindBidirectional(
                        model.resultProperty()
                );
    }

    private void addListeners() {


        tg1.selectedToggleProperty()
                .addListener((o,a,b)->update(1,b));


        tg2.selectedToggleProperty()
                .addListener((o,a,b)->update(2,b));


        tg3.selectedToggleProperty()
                .addListener((o,a,b)->update(3,b));


        tg4.selectedToggleProperty()
                .addListener((o,a,b)->update(4,b));


        tg5.selectedToggleProperty()
                .addListener((o,a,b)->update(5,b));


        tg6.selectedToggleProperty()
                .addListener((o,a,b)->update(6,b));


        tg7.selectedToggleProperty()
                .addListener((o,a,b)->update(7,b));


        tg8.selectedToggleProperty()
                .addListener((o,a,b)->update(8,b));


        tg9.selectedToggleProperty()
                .addListener((o,a,b)->update(9,b));


        tg10.selectedToggleProperty()
                .addListener((o,a,b)->update(10,b));

    }

    private void update(
            int number,
            Toggle toggle
    ) {


        String value = getValue(toggle);


        switch (number) {

            case 1:
                model.question1Property().set(value);
                break;

            case 2:
                model.question2Property().set(value);
                break;

            case 3:
                model.question3Property().set(value);
                break;

            case 4:
                model.question4Property().set(value);
                break;

            case 5:
                model.question5Property().set(value);
                break;

            case 6:
                model.question6Property().set(value);
                break;

            case 7:
                model.question7Property().set(value);
                break;

            case 8:
                model.question8Property().set(value);
                break;

            case 9:
                model.question9Property().set(value);
                break;

            case 10:
                model.question10Property().set(value);
                break;

            default:
                throw new IllegalArgumentException(
                        "Неизвестный номер вопроса"
                );
        }


        calculate();
    }

    private String getValue(Toggle toggle) {


        if (toggle == null) {
            return null;
        }


        return ((RadioButton) toggle).getText();
    }

    private void calculate() {


        model.calc();


        ResultStyler.applyStyleForValue(
                txtResult,
                model.resultValueProperty().get(),
                5,
                9
        );
    }

    @Override
    public void close() {

        txtResult.textProperty()
                .unbindBidirectional(
                        model.resultProperty()
                );
    }

    @Override
    public double getDefaultWidth() {

        return 900;
    }

    @Override
    public double getDefaultHeight() {

        return 900;
    }

}