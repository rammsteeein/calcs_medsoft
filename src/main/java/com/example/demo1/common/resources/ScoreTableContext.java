package com.example.demo1.common.resources;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

public class ScoreTableContext {

    private static final Map<ScoreTableKey, Supplier<Map<ScoreKey, Integer>>> scoreTablesFunctions = new HashMap<>();
    private static final List<ScoreKey> scoreKeysCache = new ArrayList<>();

    static {
        // Женщины
        scoreTablesFunctions.put(new ScoreTableKey(true, 65, Integer.MAX_VALUE, "Ж", false),
                ScoreTableContext::createWomen65NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 65, Integer.MAX_VALUE, "Ж", true),
                ScoreTableContext::createWomen65Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 60, 64, "Ж", false),
                ScoreTableContext::createWomen60NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 60, 64, "Ж", true),
                ScoreTableContext::createWomen60Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 55, 59, "Ж", false),
                ScoreTableContext::createWomen55NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 55, 59, "Ж", true),
                ScoreTableContext::createWomen55Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 45, 54, "Ж", false),
                ScoreTableContext::createWomen45NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 45, 54, "Ж", true),
                ScoreTableContext::createWomen45Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 0, 44, "Ж", false),
                ScoreTableContext::createWomen40NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 0, 44, "Ж", true),
                ScoreTableContext::createWomen40Smoke);

        // Мужчины
        scoreTablesFunctions.put(new ScoreTableKey(true, 65, Integer.MAX_VALUE, "М", false),
                ScoreTableContext::createMan65NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 65, Integer.MAX_VALUE, "М", true),
                ScoreTableContext::createMan65Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 60, 64, "М", false),
                ScoreTableContext::createMan60NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 60, 64, "М", true),
                ScoreTableContext::createMan60Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 55, 59, "М", false),
                ScoreTableContext::createMan55NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 55, 59, "М", true),
                ScoreTableContext::createMan55Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 45, 54, "М", false),
                ScoreTableContext::createMan45NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 45, 54, "М", true),
                ScoreTableContext::createMan45Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 0, 44, "М", false),
                ScoreTableContext::createMan40NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 0, 44, "М", true),
                ScoreTableContext::createMan40Smoke);
    }

    public static Map<ScoreKey, Integer> getScoreTable(boolean absolute, int age, String gender, boolean isSmoking) {

        Optional<ScoreTableKey> key = scoreTablesFunctions.keySet().stream()
                .filter(p ->
                        p.absolute == absolute
                                && p.minAge <= age
                                && age <= p.maxAge
                                && p.gender.equals(gender)
                                && p.isSmoking == isSmoking
                )
                .findFirst();

        if (key.isPresent()) {
            return scoreTablesFunctions.get(key.get()).get();
        } else {
            throw new RuntimeException("Таблица рассчета риска не найдена");
        }
    }

    private static ScoreKey createKey(int sysAd, int cholesterolInt) {
        BigDecimal cholesterol = BigDecimal.valueOf(cholesterolInt).setScale(0);
        return scoreKeysCache.stream()
                .filter(p -> p.getCholesterol().equals(cholesterol) && p.getSysAd().equals(sysAd))
                .findAny().orElse(new ScoreKey(cholesterol, sysAd));
    }

    // Женщины

    private static Map<ScoreKey, Integer> createWomen65NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 7);
        map.put(createKey(180, 5), 8);
        map.put(createKey(180, 6), 9);
        map.put(createKey(180, 7), 10);
        map.put(createKey(180, 8), 12);
        map.put(createKey(160, 4), 5);
        map.put(createKey(160, 5), 5);
        map.put(createKey(160, 6), 6);
        map.put(createKey(160, 7), 7);
        map.put(createKey(160, 8), 8);
        map.put(createKey(140, 4), 3);
        map.put(createKey(140, 5), 3);
        map.put(createKey(140, 6), 4);
        map.put(createKey(140, 7), 5);
        map.put(createKey(140, 8), 6);
        map.put(createKey(120, 4), 2);
        map.put(createKey(120, 5), 2);
        map.put(createKey(120, 6), 3);
        map.put(createKey(120, 7), 3);
        map.put(createKey(120, 8), 4);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen65Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 13);
        map.put(createKey(180, 5), 15);
        map.put(createKey(180, 6), 17);
        map.put(createKey(180, 7), 19);
        map.put(createKey(180, 8), 22);
        map.put(createKey(160, 4), 9);
        map.put(createKey(160, 5), 10);
        map.put(createKey(160, 6), 12);
        map.put(createKey(160, 7), 13);
        map.put(createKey(160, 8), 16);
        map.put(createKey(140, 4), 6);
        map.put(createKey(140, 5), 7);
        map.put(createKey(140, 6), 8);
        map.put(createKey(140, 7), 9);
        map.put(createKey(140, 8), 11);
        map.put(createKey(120, 4), 4);
        map.put(createKey(120, 5), 5);
        map.put(createKey(120, 6), 5);
        map.put(createKey(120, 7), 6);
        map.put(createKey(120, 8), 7);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen60NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 4);
        map.put(createKey(180, 5), 4);
        map.put(createKey(180, 6), 5);
        map.put(createKey(180, 7), 6);
        map.put(createKey(180, 8), 7);
        map.put(createKey(160, 4), 3);
        map.put(createKey(160, 5), 3);
        map.put(createKey(160, 6), 3);
        map.put(createKey(160, 7), 4);
        map.put(createKey(160, 8), 5);
        map.put(createKey(140, 4), 2);
        map.put(createKey(140, 5), 2);
        map.put(createKey(140, 6), 2);
        map.put(createKey(140, 7), 3);
        map.put(createKey(140, 8), 3);
        map.put(createKey(120, 4), 1);
        map.put(createKey(120, 5), 1);
        map.put(createKey(120, 6), 2);
        map.put(createKey(120, 7), 2);
        map.put(createKey(120, 8), 2);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen60Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 8);
        map.put(createKey(180, 5), 9);
        map.put(createKey(180, 6), 10);
        map.put(createKey(180, 7), 11);
        map.put(createKey(180, 8), 13);
        map.put(createKey(160, 4), 5);
        map.put(createKey(160, 5), 6);
        map.put(createKey(160, 6), 7);
        map.put(createKey(160, 7), 8);
        map.put(createKey(160, 8), 9);
        map.put(createKey(140, 4), 3);
        map.put(createKey(140, 5), 4);
        map.put(createKey(140, 6), 5);
        map.put(createKey(140, 7), 5);
        map.put(createKey(140, 8), 6);
        map.put(createKey(120, 4), 2);
        map.put(createKey(120, 5), 3);
        map.put(createKey(120, 6), 3);
        map.put(createKey(120, 7), 4);
        map.put(createKey(120, 8), 4);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen55NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 2);
        map.put(createKey(180, 5), 2);
        map.put(createKey(180, 6), 3);
        map.put(createKey(180, 7), 3);
        map.put(createKey(180, 8), 4);
        map.put(createKey(160, 4), 1);
        map.put(createKey(160, 5), 2);
        map.put(createKey(160, 6), 2);
        map.put(createKey(160, 7), 2);
        map.put(createKey(160, 8), 3);
        map.put(createKey(140, 4), 1);
        map.put(createKey(140, 5), 1);
        map.put(createKey(140, 6), 1);
        map.put(createKey(140, 7), 1);
        map.put(createKey(140, 8), 2);
        map.put(createKey(120, 4), 1);
        map.put(createKey(120, 5), 1);
        map.put(createKey(120, 6), 1);
        map.put(createKey(120, 7), 1);
        map.put(createKey(120, 8), 1);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen55Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 4);
        map.put(createKey(180, 5), 5);
        map.put(createKey(180, 6), 5);
        map.put(createKey(180, 7), 6);
        map.put(createKey(180, 8), 7);
        map.put(createKey(160, 4), 3);
        map.put(createKey(160, 5), 3);
        map.put(createKey(160, 6), 4);
        map.put(createKey(160, 7), 4);
        map.put(createKey(160, 8), 5);
        map.put(createKey(140, 4), 2);
        map.put(createKey(140, 5), 2);
        map.put(createKey(140, 6), 2);
        map.put(createKey(140, 7), 3);
        map.put(createKey(140, 8), 3);
        map.put(createKey(120, 4), 1);
        map.put(createKey(120, 5), 1);
        map.put(createKey(120, 6), 2);
        map.put(createKey(120, 7), 2);
        map.put(createKey(120, 8), 2);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen45NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 1);
        map.put(createKey(180, 5), 1);
        map.put(createKey(180, 6), 1);
        map.put(createKey(180, 7), 2);
        map.put(createKey(180, 8), 2);
        map.put(createKey(160, 4), 1);
        map.put(createKey(160, 5), 1);
        map.put(createKey(160, 6), 1);
        map.put(createKey(160, 7), 1);
        map.put(createKey(160, 8), 1);
        map.put(createKey(140, 4), 0);
        map.put(createKey(140, 5), 1);
        map.put(createKey(140, 6), 1);
        map.put(createKey(140, 7), 1);
        map.put(createKey(140, 8), 1);
        map.put(createKey(120, 4), 0);
        map.put(createKey(120, 5), 0);
        map.put(createKey(120, 6), 1);
        map.put(createKey(120, 7), 1);
        map.put(createKey(120, 8), 1);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen45Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 2);
        map.put(createKey(180, 5), 2);
        map.put(createKey(180, 6), 3);
        map.put(createKey(180, 7), 3);
        map.put(createKey(180, 8), 4);
        map.put(createKey(160, 4), 1);
        map.put(createKey(160, 5), 2);
        map.put(createKey(160, 6), 2);
        map.put(createKey(160, 7), 2);
        map.put(createKey(160, 8), 3);
        map.put(createKey(140, 4), 1);
        map.put(createKey(140, 5), 1);
        map.put(createKey(140, 6), 1);
        map.put(createKey(140, 7), 1);
        map.put(createKey(140, 8), 2);
        map.put(createKey(120, 4), 1);
        map.put(createKey(120, 5), 1);
        map.put(createKey(120, 6), 1);
        map.put(createKey(120, 7), 1);
        map.put(createKey(120, 8), 1);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen40NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 0);
        map.put(createKey(180, 5), 0);
        map.put(createKey(180, 6), 0);
        map.put(createKey(180, 7), 0);
        map.put(createKey(180, 8), 1);
        map.put(createKey(160, 4), 0);
        map.put(createKey(160, 5), 0);
        map.put(createKey(160, 6), 0);
        map.put(createKey(160, 7), 0);
        map.put(createKey(160, 8), 0);
        map.put(createKey(140, 4), 0);
        map.put(createKey(140, 5), 0);
        map.put(createKey(140, 6), 0);
        map.put(createKey(140, 7), 0);
        map.put(createKey(140, 8), 0);
        map.put(createKey(120, 4), 0);
        map.put(createKey(120, 5), 0);
        map.put(createKey(120, 6), 0);
        map.put(createKey(120, 7), 0);
        map.put(createKey(120, 8), 0);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen40Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 0);
        map.put(createKey(180, 5), 0);
        map.put(createKey(180, 6), 0);
        map.put(createKey(180, 7), 1);
        map.put(createKey(180, 8), 1);
        map.put(createKey(160, 4), 0);
        map.put(createKey(160, 5), 0);
        map.put(createKey(160, 6), 0);
        map.put(createKey(160, 7), 0);
        map.put(createKey(160, 8), 0);
        map.put(createKey(140, 4), 0);
        map.put(createKey(140, 5), 0);
        map.put(createKey(140, 6), 0);
        map.put(createKey(140, 7), 0);
        map.put(createKey(140, 8), 0);
        map.put(createKey(120, 4), 0);
        map.put(createKey(120, 5), 0);
        map.put(createKey(120, 6), 0);
        map.put(createKey(120, 7), 0);
        map.put(createKey(120, 8), 0);
        return map;
    }

    // Мужчины

    private static Map<ScoreKey, Integer> createMan65NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 14);
        map.put(createKey(180, 5), 16);
        map.put(createKey(180, 6), 19);
        map.put(createKey(180, 7), 22);
        map.put(createKey(180, 8), 26);
        map.put(createKey(160, 4), 9);
        map.put(createKey(160, 5), 11);
        map.put(createKey(160, 6), 13);
        map.put(createKey(160, 7), 15);
        map.put(createKey(160, 8), 16);
        map.put(createKey(140, 4), 6);
        map.put(createKey(140, 5), 8);
        map.put(createKey(140, 6), 9);
        map.put(createKey(140, 7), 11);
        map.put(createKey(140, 8), 13);
        map.put(createKey(120, 4), 4);
        map.put(createKey(120, 5), 5);
        map.put(createKey(120, 6), 6);
        map.put(createKey(120, 7), 7);
        map.put(createKey(120, 8), 9);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan65Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 26);
        map.put(createKey(180, 5), 30);
        map.put(createKey(180, 6), 35);
        map.put(createKey(180, 7), 41);
        map.put(createKey(180, 8), 47);

        map.put(createKey(160, 4), 18);
        map.put(createKey(160, 5), 21);
        map.put(createKey(160, 6), 25);
        map.put(createKey(160, 7), 29);
        map.put(createKey(160, 8), 34);

        map.put(createKey(140, 4), 13);
        map.put(createKey(140, 5), 15);
        map.put(createKey(140, 6), 17);
        map.put(createKey(140, 7), 20);
        map.put(createKey(140, 8), 24);

        map.put(createKey(120, 4), 9);
        map.put(createKey(120, 5), 10);
        map.put(createKey(120, 6), 12);
        map.put(createKey(120, 7), 14);
        map.put(createKey(120, 8), 17);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan60NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 9);
        map.put(createKey(180, 5), 11);
        map.put(createKey(180, 6), 13);
        map.put(createKey(180, 7), 15);
        map.put(createKey(180, 8), 18);

        map.put(createKey(160, 4), 6);
        map.put(createKey(160, 5), 7);
        map.put(createKey(160, 6), 9);
        map.put(createKey(160, 7), 10);
        map.put(createKey(160, 8), 12);

        map.put(createKey(140, 4), 4);
        map.put(createKey(140, 5), 5);
        map.put(createKey(140, 6), 6);
        map.put(createKey(140, 7), 7);
        map.put(createKey(140, 8), 9);

        map.put(createKey(120, 4), 3);
        map.put(createKey(120, 5), 3);
        map.put(createKey(120, 6), 4);
        map.put(createKey(120, 7), 5);
        map.put(createKey(120, 8), 6);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan60Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 18);
        map.put(createKey(180, 5), 21);
        map.put(createKey(180, 6), 24);
        map.put(createKey(180, 7), 28);
        map.put(createKey(180, 8), 33);

        map.put(createKey(160, 4), 12);
        map.put(createKey(160, 5), 14);
        map.put(createKey(160, 6), 17);
        map.put(createKey(160, 7), 20);
        map.put(createKey(160, 8), 24);

        map.put(createKey(140, 4), 8);
        map.put(createKey(140, 5), 10);
        map.put(createKey(140, 6), 12);
        map.put(createKey(140, 7), 14);
        map.put(createKey(140, 8), 17);

        map.put(createKey(120, 4), 6);
        map.put(createKey(120, 5), 7);
        map.put(createKey(120, 6), 8);
        map.put(createKey(120, 7), 10);
        map.put(createKey(120, 8), 12);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan55NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 6);
        map.put(createKey(180, 5), 7);
        map.put(createKey(180, 6), 8);
        map.put(createKey(180, 7), 10);
        map.put(createKey(180, 8), 12);

        map.put(createKey(160, 4), 4);
        map.put(createKey(160, 5), 5);
        map.put(createKey(160, 6), 6);
        map.put(createKey(160, 7), 7);
        map.put(createKey(160, 8), 8);

        map.put(createKey(140, 4), 3);
        map.put(createKey(140, 5), 3);
        map.put(createKey(140, 6), 4);
        map.put(createKey(140, 7), 5);
        map.put(createKey(140, 8), 6);

        map.put(createKey(120, 4), 2);
        map.put(createKey(120, 5), 2);
        map.put(createKey(120, 6), 3);
        map.put(createKey(120, 7), 3);
        map.put(createKey(120, 8), 4);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan55Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 12);
        map.put(createKey(180, 5), 13);
        map.put(createKey(180, 6), 16);
        map.put(createKey(180, 7), 19);
        map.put(createKey(180, 8), 22);

        map.put(createKey(160, 4), 8);
        map.put(createKey(160, 5), 9);
        map.put(createKey(160, 6), 11);
        map.put(createKey(160, 7), 13);
        map.put(createKey(160, 8), 16);

        map.put(createKey(140, 4), 5);
        map.put(createKey(140, 5), 6);
        map.put(createKey(140, 6), 8);
        map.put(createKey(140, 7), 9);
        map.put(createKey(140, 8), 11);

        map.put(createKey(120, 4), 4);
        map.put(createKey(120, 5), 4);
        map.put(createKey(120, 6), 5);
        map.put(createKey(120, 7), 6);
        map.put(createKey(120, 8), 8);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan45NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 4);
        map.put(createKey(180, 5), 4);
        map.put(createKey(180, 6), 5);
        map.put(createKey(180, 7), 6);
        map.put(createKey(180, 8), 7);

        map.put(createKey(160, 4), 2);
        map.put(createKey(160, 5), 3);
        map.put(createKey(160, 6), 3);
        map.put(createKey(160, 7), 4);
        map.put(createKey(160, 8), 5);

        map.put(createKey(140, 4), 2);
        map.put(createKey(140, 5), 2);
        map.put(createKey(140, 6), 2);
        map.put(createKey(140, 7), 3);
        map.put(createKey(140, 8), 3);

        map.put(createKey(120, 4), 1);
        map.put(createKey(120, 5), 1);
        map.put(createKey(120, 6), 2);
        map.put(createKey(120, 7), 2);
        map.put(createKey(120, 8), 2);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan45Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 7);
        map.put(createKey(180, 5), 8);
        map.put(createKey(180, 6), 10);
        map.put(createKey(180, 7), 12);
        map.put(createKey(180, 8), 14);

        map.put(createKey(160, 4), 5);
        map.put(createKey(160, 5), 6);
        map.put(createKey(160, 6), 7);
        map.put(createKey(160, 7), 8);
        map.put(createKey(160, 8), 10);

        map.put(createKey(140, 4), 3);
        map.put(createKey(140, 5), 4);
        map.put(createKey(140, 6), 5);
        map.put(createKey(140, 7), 6);
        map.put(createKey(140, 8), 7);

        map.put(createKey(120, 4), 2);
        map.put(createKey(120, 5), 3);
        map.put(createKey(120, 6), 3);
        map.put(createKey(120, 7), 4);
        map.put(createKey(120, 8), 5);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan40NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 1);
        map.put(createKey(180, 5), 1);
        map.put(createKey(180, 6), 1);
        map.put(createKey(180, 7), 2);
        map.put(createKey(180, 8), 2);

        map.put(createKey(160, 4), 1);
        map.put(createKey(160, 5), 1);
        map.put(createKey(160, 6), 1);
        map.put(createKey(160, 7), 1);
        map.put(createKey(160, 8), 1);

        map.put(createKey(140, 4), 0);
        map.put(createKey(140, 5), 1);
        map.put(createKey(140, 6), 1);
        map.put(createKey(140, 7), 1);
        map.put(createKey(140, 8), 1);

        map.put(createKey(120, 4), 0);
        map.put(createKey(120, 5), 0);
        map.put(createKey(120, 6), 1);
        map.put(createKey(120, 7), 1);
        map.put(createKey(120, 8), 1);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan40Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(180, 4), 2);
        map.put(createKey(180, 5), 2);
        map.put(createKey(180, 6), 3);
        map.put(createKey(180, 7), 3);
        map.put(createKey(180, 8), 4);

        map.put(createKey(160, 4), 1);
        map.put(createKey(160, 5), 2);
        map.put(createKey(160, 6), 2);
        map.put(createKey(160, 7), 2);
        map.put(createKey(160, 8), 3);

        map.put(createKey(140, 4), 1);
        map.put(createKey(140, 5), 1);
        map.put(createKey(140, 6), 1);
        map.put(createKey(140, 7), 2);
        map.put(createKey(140, 8), 2);

        map.put(createKey(120, 4), 1);
        map.put(createKey(120, 5), 1);
        map.put(createKey(120, 6), 1);
        map.put(createKey(120, 7), 1);
        map.put(createKey(120, 8), 1);
        return map;
    }

    private static class ScoreTableKey {
        private boolean absolute;
        private int minAge;
        private int maxAge;
        private String gender;
        private boolean isSmoking;

        public ScoreTableKey(boolean absolute, int minAge, int maxAge, String gender, boolean isSmoking) {
            this.absolute = absolute;
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.gender = gender;
            this.isSmoking = isSmoking;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ScoreTableKey)) return false;
            ScoreTableKey that = (ScoreTableKey) o;
            return absolute == that.absolute && minAge == that.minAge && maxAge == that.maxAge && isSmoking == that.isSmoking && Objects.equals(gender, that.gender);
        }

        @Override
        public int hashCode() {
            return Objects.hash(absolute, minAge, maxAge, gender, isSmoking);
        }
    }
}