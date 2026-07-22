package com.example.demo1.controls.Naranjo;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class NaranjoModel {



    private final StringProperty question1 = new SimpleStringProperty();
    private final StringProperty question2 = new SimpleStringProperty();
    private final StringProperty question3 = new SimpleStringProperty();
    private final StringProperty question4 = new SimpleStringProperty();
    private final StringProperty question5 = new SimpleStringProperty();

    private final StringProperty question6 = new SimpleStringProperty();
    private final StringProperty question7 = new SimpleStringProperty();
    private final StringProperty question8 = new SimpleStringProperty();
    private final StringProperty question9 = new SimpleStringProperty();
    private final StringProperty question10 = new SimpleStringProperty();



    private final DoubleProperty resultValue =
            new SimpleDoubleProperty();



    private final StringProperty result =
            new SimpleStringProperty();






    public StringProperty question1Property() {
        return question1;
    }


    public StringProperty question2Property() {
        return question2;
    }


    public StringProperty question3Property() {
        return question3;
    }


    public StringProperty question4Property() {
        return question4;
    }


    public StringProperty question5Property() {
        return question5;
    }


    public StringProperty question6Property() {
        return question6;
    }


    public StringProperty question7Property() {
        return question7;
    }


    public StringProperty question8Property() {
        return question8;
    }


    public StringProperty question9Property() {
        return question9;
    }


    public StringProperty question10Property() {
        return question10;
    }





    public DoubleProperty resultValueProperty() {

        return resultValue;
    }



    public StringProperty resultProperty() {

        return result;
    }






    public void calc() {



        NaranjoResult res = NaranjoCalculator.calc(

                question1.get(),
                question2.get(),
                question3.get(),
                question4.get(),
                question5.get(),

                question6.get(),
                question7.get(),
                question8.get(),
                question9.get(),
                question10.get()

        );



        resultValue.set(
                res.getValue()
        );


        result.set(
                res.toString()
        );
    }
}