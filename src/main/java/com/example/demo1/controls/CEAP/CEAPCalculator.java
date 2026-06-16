package com.example.demo1.controls.CEAP;

import com.example.demo1.controls.CEAP.CEAPModel.LegData;
import com.example.demo1.controls.CEAP.CEAPResult.LegResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CEAPCalculator {

    private static final List<String> CLINICAL_ORDER = Arrays.asList(
            "0", "1", "2", "2r", "3", "4", "4a", "4b", "4c", "5", "6", "6r"
    );

    /**
     * Генератор классификации CEAP (Clinical-Etiology-Anatomy-Pathophysiology).
     * Классификация используется для стандартизированного описания хронических
     * венозных заболеваний нижних конечностей.
     *
     * C — клинические проявления (C0–C6, подклассы C4a–C4c, рецидивы C2r/C6r)
     *     с модификатором субъективных симптомов A (асимптомный) или S (симптомный).
     * E — этиология (En, Ep, Ec, Es, Esi, Ese).
     * A — анатомия поражения (An, As, Ap, Ad), определяется автоматически по разделу P.
     * P — патофизиология (Pn, (?), Pr, Po, Pr,o) и поражённые венозные сегменты.
     * L — уровень диагностики (LI, LII, LIII).
     */
    public static CEAPResult calc(LegData right, LegData left) {
        return new CEAPResult(buildLegResult(right), buildLegResult(left));
    }

    static LegResult buildLegResult(LegData leg) {
        String clinical = buildClinical(leg);
        String etiology = leg.getEtiology();
        String anatomy = buildAnatomy(leg);
        String pathophysiology = buildPathophysiology(leg);
        String level = leg.getDiagnosticLevel();

        if (clinical.isEmpty() && (etiology == null || etiology.isEmpty())
                && anatomy.isEmpty() && pathophysiology.isEmpty()
                && (level == null || level.isEmpty())) {
            return LegResult.empty();
        }

        return new LegResult(
                LegResult.orEmpty(clinical),
                LegResult.orEmpty(etiology),
                LegResult.orEmpty(anatomy),
                LegResult.orEmpty(pathophysiology),
                LegResult.orEmpty(level),
                buildLegCode(leg)
        );
    }

    static String buildLegCode(LegData leg) {
        List<String> parts = new ArrayList<>();

        String clinical = buildClinical(leg);
        if (!clinical.isEmpty()) {
            parts.add(clinical);
        }

        if (leg.getEtiology() != null && !leg.getEtiology().isEmpty()) {
            parts.add(leg.getEtiology());
        }

        String anatomy = buildAnatomy(leg);
        if (!anatomy.isEmpty()) {
            parts.add(anatomy);
        }

        String pathophysiology = buildPathophysiology(leg);
        if (!pathophysiology.isEmpty()) {
            parts.add(pathophysiology);
        }

        if (leg.getDiagnosticLevel() != null && !leg.getDiagnosticLevel().isEmpty()) {
            parts.add(leg.getDiagnosticLevel());
        }

        if (parts.isEmpty()) {
            return "(Не заполнено!)";
        }

        return String.join(" ", parts);
    }

    static String buildClinical(LegData leg) {
        Set<String> classes = leg.getClinical();
        if (classes.isEmpty()) {
            return "";
        }

        if (classes.contains("0")) {
            return appendSymptoms("C0", leg.getSymptoms());
        }

        List<String> ordered = new ArrayList<>();
        for (String code : CLINICAL_ORDER) {
            if (classes.contains(code)) {
                ordered.add(code);
            }
        }

        return appendSymptoms("C" + String.join(",", ordered), leg.getSymptoms());
    }

    private static String appendSymptoms(String clinical, String symptoms) {
        if (symptoms == null || symptoms.isEmpty()) {
            return clinical;
        }
        return clinical + symptoms;
    }

    static String buildAnatomy(LegData leg) {
        if ("Pn".equals(leg.getPathophysiology())) {
            return "An";
        }

        boolean hasSuperficial = !leg.getSuperficialVeins().isEmpty();
        boolean hasPerforating = !leg.getPerforatingVeins().isEmpty();
        boolean hasDeep = !leg.getDeepVeins().isEmpty();

        if (!hasSuperficial && !hasPerforating && !hasDeep) {
            return "";
        }

        List<String> parts = new ArrayList<>();
        if (hasSuperficial) {
            parts.add("As");
        }
        if (hasPerforating) {
            parts.add("Ap");
        }
        if (hasDeep) {
            parts.add("Ad");
        }
        return String.join(",", parts);
    }

    static String buildPathophysiology(LegData leg) {
        String type = leg.getPathophysiology();
        if (type == null || type.isEmpty()) {
            return "";
        }

        if ("Pn".equals(type) || "(?)".equals(type)) {
            return type;
        }

        List<String> veins = new ArrayList<>();
        veins.addAll(leg.getSuperficialVeins());
        veins.addAll(leg.getPerforatingVeins());
        veins.addAll(leg.getDeepVeins());

        if (veins.isEmpty()) {
            return type;
        }

        return type + " " + String.join(",", veins);
    }
}
