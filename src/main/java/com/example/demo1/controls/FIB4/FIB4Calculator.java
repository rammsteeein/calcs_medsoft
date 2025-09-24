package com.example.demo1.controls.FIB4;

public class FIB4Calculator {

    /**
     * Расчет индекса FIB-4 для оценки фиброза печени
     *
     * Формула:
     * FIB-4 = (Возраст в годах × AST) / (тромбоциты (10^9/л) × sqrt(ALT))
     *
     * Где:
     * - Возраст: возраст пациента в годах
     * - AST (аспартатаминотрансфераза): уровень фермента в крови
     * - ALT (аланинаминотрансфераза): уровень фермента в крови
     * - Тромбоциты: количество тромбоцитов в 10^9/л
     *
     * Примечания:
     * - Индекс FIB-4 используется для неинвазивной оценки степени фиброза печени.
     * - Обычно применяется для пациентов с хроническими гепатитами или жировой болезнью печени.
     * - Значения FIB-4 интерпретируются по клиническим порогам для оценки риска значимого фиброза.
     */

    public static FIB4Result calc(int age, double ast, double alt, double platelets) {
        if (alt <= 0 || platelets <= 0) {
            return new FIB4Result("Ошибка: некорректные данные");
        }

        double fib4 = (age * ast) / (platelets * Math.sqrt(alt));
        String result = String.format("FIB-4 = %.3f", fib4);

        return new FIB4Result(result);
    }
}