package com.example.demo1.controls.DASI;

public class DASICalculator {

    /**
     * Расчет максимального потребления кислорода (VO2 max) и MET с использованием индекса DASI
     * <p>
     * Формула:
     * VO2 max (мл/кг/мин) = 0,43 * DASI + 9,6
     * MET = VO2 max / 3,5
     * <p>
     * Где DASI — сумма баллов за физическую активность пациента:
     * 1. Забота о себе (еда, одевание, ванна, туалет): +2,75
     * 2. Ходьба в помещении: +1,75
     * 3. Пройти 1-2 квартала по ровной местности: +2,75
     * 4. Подняться по лестнице или на холм: +5,5
     * 5. Пробежать короткую дистанцию: +8,0
     * 6. Легкая работа по дому (вытирание пыли, мытье посуды): +2,7
     * 7. Умеренная работа по дому (пылесос, подметание, перенос продуктов): +3,5
     * 8. Тяжелая работа по дому (мытье полов, перенос тяжелой мебели): +8,0
     * 9. Работа во дворе (сгребание листьев, прополка, толкание газонокосилки): +4,5
     * 10. Вступление в сексуальные отношения: +5,25
     * 11. Умеренные развлекательные мероприятия (гольф, боулинг, танцы, теннис парный, бейсбол, футбол): +6,0
     * 12. Интенсивные виды спорта (плавание, одиночный теннис, футбол, баскетбол, лыжный спорт): +7,5
     * <p>
     * Примечания:
     * - За каждый вид активности пациент получает баллы, если способен выполнять задачу.
     * - Сумма всех баллов дает DASI, по которому вычисляется VO2 max.
     * - MET (Metabolic Equivalent of Task) используется для оценки физической выносливости.
     * - Индекс DASI позволяет количественно оценить функциональный статус пациента перед хирургическим вмешательством или нагрузочным тестом.
     */


        public static DASIResult calc(
                boolean selfCare,
                boolean walkIndoors,
                boolean walk1to2Blocks,
                boolean climbStairsOrHill,
                boolean runShortDistance,
                boolean lightHousework,
                boolean moderateHousework,
                boolean heavyHousework,
                boolean yardWork,
                boolean sexualActivity,
                boolean moderateRecreation,
                boolean strenuousSports
        ) {
            double DASI = 0.0;
            if (selfCare) DASI += 2.75;
            if (walkIndoors) DASI += 1.75;
            if (walk1to2Blocks) DASI += 2.75;
            if (climbStairsOrHill) DASI += 5.5;
            if (runShortDistance) DASI += 8.0;
            if (lightHousework) DASI += 2.7;
            if (moderateHousework) DASI += 3.5;
            if (heavyHousework) DASI += 8.0;
            if (yardWork) DASI += 4.5;
            if (sexualActivity) DASI += 5.25;
            if (moderateRecreation) DASI += 6.0;
            if (strenuousSports) DASI += 7.5;

            double VO2max = 0.43 * DASI + 9.6;
            double MET = VO2max / 3.5;

            String calculation = String.format(
                    "DASI score: %.2f\nVO2max: %.2f мл/кг/мин\nMET: %.2f",
                    DASI, VO2max, MET
            );

            return new DASIResult(DASI, VO2max, MET, calculation);
        }
    }
