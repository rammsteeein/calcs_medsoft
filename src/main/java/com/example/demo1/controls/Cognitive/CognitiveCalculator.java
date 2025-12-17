package com.example.demo1.controls.Cognitive;

public class CognitiveCalculator {

    /**
     * Когнитивная оценка (Cognitive Assessment)
     * <p>
     * Разделы и баллы:
     * А. Ориентация (10 вопросов, по 1 баллу каждый):
     * 1. Какой сейчас год?
     * 2. Какое сейчас время года?
     * 3. Какая сегодня дата?
     * 4. Какой сегодня день недели?
     * 5. Какой сейчас месяц?
     * 6. Скажите, где Вы сейчас находитесь? (клиника/больница)
     * 7. В какой стране Вы находитесь?
     * 8. В каком городе Вы находитесь?
     * 9. Назовите адрес того места, где мы сейчас находимся
     * 10. На каком этаже Вы находитесь?
     * <p>
     * В. Немедленная память (запоминание) - 3 балла:
     * 11. Повторение трех слов: АВТОБУС, ДВЕРЬ, РОЗА (по 1 баллу за каждое слово)
     * <p>
     * С. Внимание и счет - 5 баллов:
     * 12. Последовательно вычитайте из 100 число 7 (93, 86, 79, 72, 65)
     * По 1 баллу за каждое правильное вычитание (0-5 баллов)
     * <p>
     * D. Воспроизведение слов - 3 балла:
     * 13. Автобус (1 балл)
     * 14. Дверь (1 балл)
     * 15. Роза (1 балл)
     * <p>
     * Е. Речь - 9 баллов:
     * 16. (Покажите наручные часы) Как это называется? (1 балл)
     * 17. (Покажите карандаш) Как это называется? (1 балл)
     * 18. Повторите за мной фразу: «Никаких если, и или но». Только одна попытка (1 балл)
     * 19. Прочитайте слова, которые написаны на этом листе, и сделайте то, что написано.
     * На бумаге написано «Закройте глаза» (1 балл)
     * 20. Возьмите бумагу в правую руку, согните ее пополам двумя руками и положите на колени.
     * (1 балл за каждый правильно выполненный компонент задания) (3 балла)
     * 21. Напишите на листе бумаги законченное предложение (1 балл)
     * 22. Вот рисунок, пожалуйста, скопируйте его на том же листе бумаги.
     * Правильный ответ засчитывается, если два пятиугольника пересекаются,
     * образуя при этом четырехугольник (1 балл)
     * <p>
     * Максимальный балл: 30
     */

    public static CognitiveResult calc(
            Boolean orientation1, Boolean orientation2, Boolean orientation3, Boolean orientation4, Boolean orientation5,
            Boolean orientation6, Boolean orientation7, Boolean orientation8, Boolean orientation9, Boolean orientation10,
            Boolean memoryBus, Boolean memoryDoor, Boolean memoryRose,
            Boolean subtract1, Boolean subtract2, Boolean subtract3, Boolean subtract4, Boolean subtract5,
            Boolean recallBus, Boolean recallDoor, Boolean recallRose,
            Boolean speech16, Boolean speech17, Boolean speech18, Boolean speech19,
            Boolean speech20a, Boolean speech20b, Boolean speech20c,
            Boolean speech21, Boolean speech22
    ) {
        int total = 0;

        if (orientation1 != null && orientation1) {
            total += 1;
        }
        if (orientation2 != null && orientation2) {
            total += 1;
        }
        if (orientation3 != null && orientation3) {
            total += 1;
        }
        if (orientation4 != null && orientation4) {
            total += 1;
        }
        if (orientation5 != null && orientation5) {
            total += 1;
        }
        if (orientation6 != null && orientation6) {
            total += 1;
        }
        if (orientation7 != null && orientation7) {
            total += 1;
        }
        if (orientation8 != null && orientation8) {
            total += 1;
        }
        if (orientation9 != null && orientation9) {
            total += 1;
        }
        if (orientation10 != null && orientation10) {
            total += 1;
        }

        if (memoryBus != null && memoryBus) {
            total += 1;
        }
        if (memoryDoor != null && memoryDoor) {
            total += 1;
        }
        if (memoryRose != null && memoryRose) {
            total += 1;
        }

        if (subtract1 != null && subtract1) {
            total += 1;
        }
        if (subtract2 != null && subtract2) {
            total += 1;
        }
        if (subtract3 != null && subtract3) {
            total += 1;
        }
        if (subtract4 != null && subtract4) {
            total += 1;
        }
        if (subtract5 != null && subtract5) {
            total += 1;
        }

        if (recallBus != null && recallBus) {
            total += 1;
        }
        if (recallDoor != null && recallDoor) {
            total += 1;
        }
        if (recallRose != null && recallRose) {
            total += 1;
        }

        if (speech16 != null && speech16) {
            total += 1;
        }
        if (speech17 != null && speech17) {
            total += 1;
        }
        if (speech18 != null && speech18) {
            total += 1;
        }
        if (speech19 != null && speech19) {
            total += 1;
        }
        if (speech20a != null && speech20a) {
            total += 1;
        }
        if (speech20b != null && speech20b) {
            total += 1;
        }
        if (speech20c != null && speech20c) {
            total += 1;
        }
        if (speech21 != null && speech21) {
            total += 1;
        }
        if (speech22 != null && speech22) {
            total += 1;
        }

        String interpretation = interpretScore(total);

        return new CognitiveResult(total, interpretation);
    }

    private static String interpretScore(int score) {
        if (score >= 29) {
            return "Нет нарушений";
        }
        else if (score == 28 ) {
            return "Лёгкие когнитивные нарушения";
        }
        else if (score >= 25) {
            return "Умеренные когнитивные нарушения";
        }
        else if (score >= 20) {
            return "Легкая деменция";
        }
        else if (score >= 10) {
            return "Умеренная деменция";
        }
        else {
            return "Тяжелая деменция";
        }
    }
}

