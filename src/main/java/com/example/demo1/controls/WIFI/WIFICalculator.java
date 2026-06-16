package com.example.demo1.controls.WIFI;

/**
 * Классификация WIfI (Wound, Ischemia, foot Infection) — SVS.
 * Оценивает тяжесть поражения тканей стопы, перфузию и инфекцию.
 * По комбинации W–I–fI определяются клинические стадии 1–4
 * (риск ампутации и потребность в реваскуляризации).
 */
public class WIFICalculator {

    private static final String[] WOUND = {
            "Нет язвы и гангрены. Ишемические боли в покое, раны нет.",
            "Маленькая поверхностная язва в дистальном отделе голени или стопы; "
                    + "без вовлечения костей, кроме дистальных фаланг. Минимальное повреждение тканей.",
            "Глубокая язва с вовлечением костей, суставов или сухожилий; "
                    + "гангрена ограничена фалангами. Выраженное повреждение тканей.",
            "Обширная глубокая язва переднего/среднего отдела стопы или пяточной области с костью; "
                    + "распространённая гангрена. Распространённое поражение тканей."
    };

    private static final String[] ISCHEMIA = {
            "ЛПИ ≥ 0,8; систолическое АД в артерии голени > 100 мм рт. ст.; "
                    + "пальцевое давление ≥ 60; TcPO2 ≥ 60 мм рт. ст.",
            "ЛПИ 0,6–0,79; АД голени 70–100; пальцевое давление 40–59; TcPO2 40–59.",
            "ЛПИ 0,4–0,59; АД голени 50–70; пальцевое давление 30–39; TcPO2 30–39.",
            "ЛПИ < 0,4; АД голени < 50; пальцевое давление < 30; TcPO2 < 30."
    };

    private static final String[] INFECTION = {
            "Инфекции нет — нет симптомов и признаков инфекции.",
            "Лёгкая — ≥ 2 признаков: местный отёк/инфильтрация; эритема 0,5–2 см; "
                    + "местная болезненность; локальная гипертермия; гнойное отделяемое.",
            "Средняя — локальная инфекция с эритемой > 2 см или вовлечением глубже кожи "
                    + "(абсцесс, остеомиелит, септический артрит, фасциит); без системных признаков.",
            "Тяжёлая — местная инфекция с ≥ 2 системными признаками: T > 38° или < 36 °C; "
                    + "ЧСС > 90; ЧД > 20 или PaCO2 < 32; лейкоцитоз > 12000 или < 4000 или 10% юных форм."
    };

    /** Риск ампутации: W × I × fI → стадия 1–4 (SVS Table 4a). */
    private static final int[][][] AMPUTATION_STAGE = {
            {{1, 1, 2, 3}, {1, 2, 3, 4}, {2, 2, 3, 4}, {2, 3, 3, 4}},
            {{1, 1, 2, 3}, {1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 4, 4}},
            {{2, 2, 3, 4}, {3, 3, 4, 4}, {3, 4, 4, 4}, {4, 4, 4, 4}},
            {{3, 3, 4, 4}, {4, 4, 4, 4}, {4, 4, 4, 4}, {4, 4, 4, 4}}
    };

    /** Потребность в реваскуляризации: W × I × fI → стадия 1–4 (SVS Table 4b). */
    private static final int[][][] REVASCULARIZATION_STAGE = {
            {{1, 1, 1, 1}, {1, 2, 2, 3}, {2, 2, 3, 3}, {3, 4, 4, 4}},
            {{1, 1, 1, 1}, {2, 3, 3, 3}, {3, 4, 4, 4}, {4, 4, 4, 4}},
            {{1, 1, 1, 1}, {3, 3, 4, 4}, {4, 4, 4, 4}, {4, 4, 4, 4}},
            {{1, 1, 1, 1}, {3, 3, 3, 4}, {4, 4, 4, 4}, {4, 4, 4, 4}}
    };

    private static final String[] STAGE_LABEL = {
            "",
            "очень низкий риск",
            "низкий риск",
            "умеренный риск",
            "высокий риск"
    };

    public static WIFIResult calc(Integer wound, Integer ischemia, Integer infection) {
        if (wound == null || ischemia == null || infection == null) {
            return incomplete(wound, ischemia, infection);
        }
        if (!isValidGrade(wound) || !isValidGrade(ischemia) || !isValidGrade(infection)) {
            return new WIFIResult(wound, ischemia, infection, -1, -1,
                    "", "", "", "Некорректные значения", "", false);
        }

        int ampStage = AMPUTATION_STAGE[wound][ischemia][infection];
        int revStage = REVASCULARIZATION_STAGE[wound][ischemia][infection];

        return new WIFIResult(
                wound, ischemia, infection,
                ampStage, revStage,
                WOUND[wound], ISCHEMIA[ischemia], INFECTION[infection],
                STAGE_LABEL[ampStage],
                STAGE_LABEL[revStage],
                true
        );
    }

    private static WIFIResult incomplete(Integer wound, Integer ischemia, Integer infection) {
        return new WIFIResult(
                wound == null ? -1 : wound,
                ischemia == null ? -1 : ischemia,
                infection == null ? -1 : infection,
                -1, -1, "", "", "", "", "", false
        );
    }

    private static boolean isValidGrade(int grade) {
        return grade >= 0 && grade <= 3;
    }
}
