package com.example.demo1.controls.Fagerstrom;


import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FagerstromControl extends StackPane
        implements AutoCloseable, CalculatorControl {

    private final FagerstromModel model;
    private ToggleGroup tg1;
    private ToggleGroup tg2;
    private ToggleGroup tg3;
    private ToggleGroup tg4;
    private ToggleGroup tg5;
    private ToggleGroup tg6;
    private TextArea txtResult;


    public FagerstromControl(FagerstromModel model) {

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



        VBox left = new VBox(
                12,

                CalculatorHeader.createHeader(
                        "Тест Фагерстрема"
                ),


                createQuestion(
                        "1. Как скоро после пробуждения Вы выкуриваете первую сигарету?",
                        tg1,
                        "Первые 5 минут",
                        "6-30 минут",
                        "30-60 минут",
                        "Через 1 час"
                ),


                createQuestion(
                        "2. Сложно ли Вам воздержаться от курения в местах, где курение запрещено?",
                        tg2,
                        "Да",
                        "Нет"
                ),


                createQuestion(
                        "3. От какой сигареты Вам сложнее всего отказаться?",
                        tg3,
                        "Первая утром",
                        "Все остальные"
                ),


                createQuestion(
                        "4. Сколько сигарет Вы выкуриваете в день?",
                        tg4,
                        "10 и меньше",
                        "11-20",
                        "21-30",
                        "31 и более"
                ),


                createQuestion(
                        "5. Вы курите чаще в первые часы после пробуждения?",
                        tg5,
                        "Да",
                        "Нет"
                ),


                createQuestion(
                        "6. Курите ли Вы, если сильно больны и вынуждены находиться в кровати весь день?",
                        tg6,
                        "Да",
                        "Нет"
                )

        );

        txtResult = new TextArea();

        txtResult.setEditable(false);

        txtResult.setWrapText(true);

        left.getChildren().add(txtResult);

        getChildren().add(

                new HBox(
                        20,

                        left,


                        CalculatorDescription.createDescription(

                                "Тест Фагерстрёма на никотиновую зависимость (Fagerström Nicotine Dependence Test," +
                                        " FBNT) — краткий опросник, который позволяет оценить уровень никотиновой" +
                                        " зависимости у курящих людей. Разработан шведским врачом Карлом-Олофом Фагерстрёмом.\n\n" +
                                        "Интерпретация\n" +
                                        "Сумма баллов по тесту указывает на степень физической никотиновой зависимости: \n" +
                                        "b17.ru\n" +
                                        "0–2 балла — очень низкая зависимость;\n" +
                                        "3–4 балла — низкая зависимость;\n" +
                                        "5 баллов — средняя зависимость;\n" +
                                        "6–7 баллов — высокая зависимость;\n" +
                                        "8–10 баллов — очень высокая зависимость."

                        )
                )
        );
    }

    private VBox createQuestion(
            String text,
            ToggleGroup group,
            String... answers
    ) {


        Label label = new Label(text);


        VBox box = new VBox(4);


        box.getChildren().add(label);



        for (String answer : answers) {


            RadioButton rb = new RadioButton(answer);

            rb.setToggleGroup(group);

            box.getChildren().add(rb);
        }


        return box;
    }

    private void bind() {

        txtResult.textProperty()
                .bindBidirectional(
                        model.resultProperty()
                );
    }

    private void addListeners() {


        tg1.selectedToggleProperty()
                .addListener((o,a,b)->{
                    model.question1Property()
                            .set(getValue(b));
                    calculate();
                });

        tg2.selectedToggleProperty()
                .addListener((o,a,b)->{
                    model.question2Property()
                            .set(getValue(b));
                    calculate();
                });

        tg3.selectedToggleProperty()
                .addListener((o,a,b)->{
                    model.question3Property()
                            .set(getValue(b));
                    calculate();
                });

        tg4.selectedToggleProperty()
                .addListener((o,a,b)->{
                    model.question4Property()
                            .set(getValue(b));
                    calculate();
                });

        tg5.selectedToggleProperty()
                .addListener((o,a,b)->{
                    model.question5Property()
                            .set(getValue(b));
                    calculate();
                });

        tg6.selectedToggleProperty()
                .addListener((o,a,b)->{
                    model.question6Property()
                            .set(getValue(b));
                    calculate();
                });

    }

    private String getValue(Toggle toggle) {


        if (toggle == null) {
            return null;
        }


        return ((RadioButton) toggle)
                .getText();
    }






    private void calculate() {


        model.calc();


        ResultStyler.applyStyleForValue(
                txtResult,
                model.resultValueProperty().get(),
                5,
                8
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

        return 800;
    }





    @Override
    public double getDefaultHeight() {

        return 750;
    }

}