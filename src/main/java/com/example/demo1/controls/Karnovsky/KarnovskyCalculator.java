package com.example.demo1.controls.Karnovsky;

public class KarnovskyCalculator {

    /**
     * Индекс Карновского (Karnofsky Performance Status)
     * 
     * <p>
     * Шкала оценки функционального состояния пациента от 0 до 100%.
     * Каждый уровень снижается на 10%.
     * 
     * <p>
     * Клиническое применение:
     * - Прогнозирование выживаемости и качества жизни
     * - Оценка переносимости агрессивного лечения (химио-, радиотерапия, операция)
     * - Критерий включения в клинические исследования
     * - Мониторинг динамики состояния при хронических и терминальных заболеваниях
     * 
     * <p>
     * Интерпретация диапазонов:
     * - 70–100% — пациент активен, возможна интенсивная терапия
     * - 40–60% — ограниченная активность, требуется помощь
     * - 10–30% — тяжёлое состояние, преимущественно паллиативный подход
     * - 0% — смерть
     * 
     * <p>
     * Первичный источник:
     * Karnofsky D. A., Abelmann W. H., Craver L. F., Burchenal J. H. (1948).
     * The use of the nitrogen mustards in the palliative treatment of carcinoma:
     * with particular reference to bronchogenic carcinoma. Cancer, 1(4), 634–656.
     */

    public static KarnovskyResult calc(String status) {
        if (status == null) {
            return new KarnovskyResult(0, "Выберите состояние пациента");
        }

        int score = mapStatusToScore(status);
        String interpretation = interpretScore(score);

        return new KarnovskyResult(score, interpretation);
    }

    private static int mapStatusToScore(String status) {
        switch (status) {
            case "Состояние нормальное, жалоб нет":
                return 100;
            case "Способен к нормальной деятельности, есть незначительные симптомы":
                return 90;
            case "Нормальная активность возможна с усилием, умеренные симптомы":
                return 80;
            case "Самообслуживание сохранено, но не способен к нормальной работе":
                return 70;
            case "Иногда требуется помощь, большую часть потребностей удовлетворяет самостоятельно":
                return 60;
            case "Требует значительной помощи и медицинского обслуживания":
                return 50;
            case "Инвалид, нуждается в регулярной специализированной помощи":
                return 40;
            case "Тяжёлая инвалидность, показана госпитализация, смерть не неизбежна":
                return 30;
            case "Тяжёлый больной, требуется госпитальный уход и активная терапия":
                return 20;
            case "Терминальное состояние (агония), стремительное ухудшение":
                return 10;
            case "Смерть":
                return 0;
            default:
                return 0;
        }
    }

    private static String interpretScore(int score) {
        if (score >= 70) {
            return "Пациент активен, возможна интенсивная терапия";
        } else if (score >= 40) {
            return "Ограниченная активность, требуется помощь";
        } else if (score >= 10) {
            return "Тяжёлое состояние, преимущественно паллиативный подход";
        } else {
            return "Смерть";
        }
    }
}



