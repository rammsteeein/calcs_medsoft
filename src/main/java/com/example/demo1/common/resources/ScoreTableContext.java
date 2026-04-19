package com.example.demo1.common.resources;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

public class ScoreTableContext {

    private static final Map<ScoreTableKey, Supplier<Map<ScoreKey, Integer>>> scoreTablesFunctions = new HashMap<>();
    private static final List<ScoreKey> scoreKeysCache = new ArrayList<>();

    static {
        scoreTablesFunctions.put(new ScoreTableKey(true, 85, Integer.MAX_VALUE, "Ж", false),
                ScoreTableContext::createWomen85NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 85, Integer.MAX_VALUE, "Ж", true),
                ScoreTableContext::createWomen85Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 80, 84, "Ж", false),
                ScoreTableContext::createWomen80NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 80, 84, "Ж", true),
                ScoreTableContext::createWomen80Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 75, 79, "Ж", false),
                ScoreTableContext::createWomen75NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 75, 79, "Ж", true),
                ScoreTableContext::createWomen75Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 70, 74, "Ж", false),
                ScoreTableContext::createWomen70NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 70, 74, "Ж", true),
                ScoreTableContext::createWomen70Smoke);

        scoreTablesFunctions.put(new ScoreTableKey(true, 85, Integer.MAX_VALUE, "М", false),
                ScoreTableContext::createMan85NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 85, Integer.MAX_VALUE, "М", true),
                ScoreTableContext::createMan85Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 80, 84, "М", false),
                ScoreTableContext::createMan80NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 80, 84, "М", true),
                ScoreTableContext::createMan80Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 75, 79, "М", false),
                ScoreTableContext::createMan75NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 75, 79, "М", true),
                ScoreTableContext::createMan75Smoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 70, 74, "М", false),
                ScoreTableContext::createMan70NoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(true, 70, 74, "М", true),
                ScoreTableContext::createMan70Smoke);

        scoreTablesFunctions.put(new ScoreTableKey(false, 70, Integer.MAX_VALUE, "М", false),
                ScoreTableContext::createRelativeNoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(false, 70, Integer.MAX_VALUE, "М", true),
                ScoreTableContext::createRelativeSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(false, 70, Integer.MAX_VALUE, "Ж", false),
                ScoreTableContext::createRelativeNoSmoke);
        scoreTablesFunctions.put(new ScoreTableKey(false, 70, Integer.MAX_VALUE, "Ж", true),
                ScoreTableContext::createRelativeSmoke);
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
                .filter(p->p.getCholesterol().equals(cholesterol) && p.getSysAd().equals(sysAd))
                .findAny().orElse(new ScoreKey(cholesterol, sysAd));
    }

    private static Map<ScoreKey, Integer> createWomen85NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 62);
        map.put(createKey(160, 4), 63);
        map.put(createKey(160, 5), 64);
        map.put(createKey(160, 6), 65);

        map.put(createKey(140, 3), 60);
        map.put(createKey(140, 4), 61);
        map.put(createKey(140, 5), 62);
        map.put(createKey(140, 6), 63);

        map.put(createKey(120, 3), 58);
        map.put(createKey(120, 4), 59);
        map.put(createKey(120, 5), 60);
        map.put(createKey(120, 6), 61);

        map.put(createKey(100, 3), 56);
        map.put(createKey(100, 4), 57);
        map.put(createKey(100, 5), 58);
        map.put(createKey(100, 6), 60);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen85Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 65);
        map.put(createKey(160, 4), 66);
        map.put(createKey(160, 5), 67);
        map.put(createKey(160, 6), 68);

        map.put(createKey(140, 3), 63);
        map.put(createKey(140, 4), 64);
        map.put(createKey(140, 5), 65);
        map.put(createKey(140, 6), 66);

        map.put(createKey(120, 3), 61);
        map.put(createKey(120, 4), 62);
        map.put(createKey(120, 5), 63);
        map.put(createKey(120, 6), 65);

        map.put(createKey(100, 3), 59);
        map.put(createKey(100, 4), 60);
        map.put(createKey(100, 5), 61);
        map.put(createKey(100, 6), 63);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen80NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 53);
        map.put(createKey(160, 4), 54);
        map.put(createKey(160, 5), 55);
        map.put(createKey(160, 6), 57);

        map.put(createKey(140, 3), 50);
        map.put(createKey(140, 4), 51);
        map.put(createKey(140, 5), 52);
        map.put(createKey(140, 6), 54);

        map.put(createKey(120, 3), 47);
        map.put(createKey(120, 4), 48);
        map.put(createKey(120, 5), 49);
        map.put(createKey(120, 6), 51);

        map.put(createKey(100, 3), 44);
        map.put(createKey(100, 4), 45);
        map.put(createKey(100, 5), 47);
        map.put(createKey(100, 6), 48);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen80Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 59);
        map.put(createKey(160, 4), 60);
        map.put(createKey(160, 5), 62);
        map.put(createKey(160, 6), 63);

        map.put(createKey(140, 3), 56);
        map.put(createKey(140, 4), 57);
        map.put(createKey(140, 5), 59);
        map.put(createKey(140, 6), 60);

        map.put(createKey(120, 3), 53);
        map.put(createKey(120, 4), 54);
        map.put(createKey(120, 5), 56);
        map.put(createKey(120, 6), 57);

        map.put(createKey(100, 3), 50);
        map.put(createKey(100, 4), 51);
        map.put(createKey(100, 5), 53);
        map.put(createKey(100, 6), 54);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen75NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 44);
        map.put(createKey(160, 4), 46);
        map.put(createKey(160, 5), 47);
        map.put(createKey(160, 6), 48);

        map.put(createKey(140, 3), 41);
        map.put(createKey(140, 4), 42);
        map.put(createKey(140, 5), 43);
        map.put(createKey(140, 6), 45);

        map.put(createKey(120, 3), 37);
        map.put(createKey(120, 4), 39);
        map.put(createKey(120, 5), 40);
        map.put(createKey(120, 6), 41);

        map.put(createKey(100, 3), 34);
        map.put(createKey(100, 4), 35);
        map.put(createKey(100, 5), 36);
        map.put(createKey(100, 6), 37);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen75Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 53);
        map.put(createKey(160, 4), 55);
        map.put(createKey(160, 5), 56);
        map.put(createKey(160, 6), 58);

        map.put(createKey(140, 3), 49);
        map.put(createKey(140, 4), 51);
        map.put(createKey(140, 5), 52);
        map.put(createKey(140, 6), 53);

        map.put(createKey(120, 3), 46);
        map.put(createKey(120, 4), 47);
        map.put(createKey(120, 5), 48);
        map.put(createKey(120, 6), 49);

        map.put(createKey(100, 3), 42);
        map.put(createKey(100, 4), 44);
        map.put(createKey(100, 5), 44);
        map.put(createKey(100, 6), 46);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen70NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 37);
        map.put(createKey(160, 4), 38);
        map.put(createKey(160, 5), 39);
        map.put(createKey(160, 6), 41);

        map.put(createKey(140, 3), 33);
        map.put(createKey(140, 4), 34);
        map.put(createKey(140, 5), 35);
        map.put(createKey(140, 6), 36);

        map.put(createKey(120, 3), 29);
        map.put(createKey(120, 4), 30);
        map.put(createKey(120, 5), 31);
        map.put(createKey(120, 6), 32);

        map.put(createKey(100, 3), 26);
        map.put(createKey(100, 4), 27);
        map.put(createKey(100, 5), 28);
        map.put(createKey(100, 6), 29);
        return map;
    }

    private static Map<ScoreKey, Integer> createWomen70Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 48);
        map.put(createKey(160, 4), 49);
        map.put(createKey(160, 5), 51);
        map.put(createKey(160, 6), 52);

        map.put(createKey(140, 3), 43);
        map.put(createKey(140, 4), 44);
        map.put(createKey(140, 5), 46);
        map.put(createKey(140, 6), 47);

        map.put(createKey(120, 3), 39);
        map.put(createKey(120, 4), 40);
        map.put(createKey(120, 5), 41);
        map.put(createKey(120, 6), 43);

        map.put(createKey(100, 3), 34);
        map.put(createKey(100, 4), 36);
        map.put(createKey(100, 5), 37);
        map.put(createKey(100, 6), 38);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan85NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 49);
        map.put(createKey(160, 4), 54);
        map.put(createKey(160, 5), 59);
        map.put(createKey(160, 6), 64);
        map.put(createKey(140, 3), 48);
        map.put(createKey(140, 4), 53);
        map.put(createKey(140, 5), 58);
        map.put(createKey(140, 6), 63);
        map.put(createKey(120, 3), 47);
        map.put(createKey(120, 4), 52);
        map.put(createKey(120, 5), 56);
        map.put(createKey(120, 6), 61);
        map.put(createKey(100, 3), 46);
        map.put(createKey(100, 4), 50);
        map.put(createKey(100, 5), 55);
        map.put(createKey(100, 6), 60);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan85Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160, 3), 49);
        map.put(createKey(160, 4), 54);
        map.put(createKey(160, 5), 59);
        map.put(createKey(160, 6), 64);
        map.put(createKey(140, 3), 48);
        map.put(createKey(140, 4), 53);
        map.put(createKey(140, 5), 58);
        map.put(createKey(140, 6), 63);
        map.put(createKey(120, 3), 47);
        map.put(createKey(120, 4), 52);
        map.put(createKey(120, 5), 56);
        map.put(createKey(120, 6), 61);
        map.put(createKey(100, 3), 46);
        map.put(createKey(100, 4), 50);
        map.put(createKey(100, 5), 55);
        map.put(createKey(100, 6), 60);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan80NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 44);
        map.put(createKey(160,4), 48);
        map.put(createKey(160,5), 52);
        map.put(createKey(160,6), 56);
        map.put(createKey(140,3), 42);
        map.put(createKey(140,4), 46);
        map.put(createKey(140,5), 49);
        map.put(createKey(140,6), 53);
        map.put(createKey(120,3), 40);
        map.put(createKey(120,4), 43);
        map.put(createKey(120,5), 47);
        map.put(createKey(120,6), 51);
        map.put(createKey(100,3), 38);
        map.put(createKey(100,4), 41);
        map.put(createKey(100,5), 45);
        map.put(createKey(100,6), 48);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan80Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 47);
        map.put(createKey(160,4), 51);
        map.put(createKey(160,5), 55);
        map.put(createKey(160,6), 59);
        map.put(createKey(140,3), 45);
        map.put(createKey(140,4), 49);
        map.put(createKey(140,5), 52);
        map.put(createKey(140,6), 56);
        map.put(createKey(120,3), 43);
        map.put(createKey(120,4), 46);
        map.put(createKey(120,5), 50);
        map.put(createKey(120,6), 54);
        map.put(createKey(100,3), 40);
        map.put(createKey(100,4), 44);
        map.put(createKey(100,5), 48);
        map.put(createKey(100,6), 51);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan75NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 40);
        map.put(createKey(160,4), 42);
        map.put(createKey(160,5), 45);
        map.put(createKey(160,6), 48);
        map.put(createKey(140,3), 37);
        map.put(createKey(140,4), 39);
        map.put(createKey(140,5), 42);
        map.put(createKey(140,6), 44);
        map.put(createKey(120,3), 34);
        map.put(createKey(120,4), 36);
        map.put(createKey(120,5), 39);
        map.put(createKey(120,6), 41);
        map.put(createKey(100,3), 31);
        map.put(createKey(100,4), 33);
        map.put(createKey(100,5), 36);
        map.put(createKey(100,6), 38);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan75Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 45);
        map.put(createKey(160,4), 48);
        map.put(createKey(160,5), 51);
        map.put(createKey(160,6), 54);
        map.put(createKey(140,3), 42);
        map.put(createKey(140,4), 44);
        map.put(createKey(140,5), 47);
        map.put(createKey(140,6), 50);
        map.put(createKey(120,3), 39);
        map.put(createKey(120,4), 41);
        map.put(createKey(120,5), 44);
        map.put(createKey(120,6), 47);
        map.put(createKey(100,3), 36);
        map.put(createKey(100,4), 38);
        map.put(createKey(100,5), 41);
        map.put(createKey(100,6), 43);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan70NoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 35);
        map.put(createKey(160,4), 37);
        map.put(createKey(160,5), 39);
        map.put(createKey(160,6), 40);
        map.put(createKey(140,3), 32);
        map.put(createKey(140,4), 33);
        map.put(createKey(140,5), 35);
        map.put(createKey(140,6), 36);
        map.put(createKey(120,3), 28);
        map.put(createKey(120,4), 30);
        map.put(createKey(120,5), 31);
        map.put(createKey(120,6), 33);
        map.put(createKey(100,3), 25);
        map.put(createKey(100,4), 26);
        map.put(createKey(100,5), 28);
        map.put(createKey(100,6), 29);
        return map;
    }

    private static Map<ScoreKey, Integer> createMan70Smoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 43);
        map.put(createKey(160,4), 45);
        map.put(createKey(160,5), 47);
        map.put(createKey(160,6), 49);
        map.put(createKey(140,3), 39);
        map.put(createKey(140,4), 41);
        map.put(createKey(140,5), 42);
        map.put(createKey(140,6), 44);
        map.put(createKey(120,3), 35);
        map.put(createKey(120,4), 36);
        map.put(createKey(120,5), 38);
        map.put(createKey(120,6), 40);
        map.put(createKey(100,3), 31);
        map.put(createKey(100,4), 33);
        map.put(createKey(100,5), 34);
        map.put(createKey(100,6), 36);
        return map;
    }

    private static Map<ScoreKey, Integer> createRelativeNoSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 35);
        map.put(createKey(160,4), 37);
        map.put(createKey(160,5), 39);
        map.put(createKey(160,6), 40);
        map.put(createKey(140,3), 32);
        map.put(createKey(140,4), 33);
        map.put(createKey(140,5), 35);
        map.put(createKey(140,6), 36);
        map.put(createKey(120,3), 28);
        map.put(createKey(120,4), 30);
        map.put(createKey(120,5), 31);
        map.put(createKey(120,6), 33);
        map.put(createKey(100,3), 25);
        map.put(createKey(100,4), 26);
        map.put(createKey(100,5), 28);
        map.put(createKey(100,6), 29);
        return map;
    }

    private static Map<ScoreKey, Integer> createRelativeSmoke() {
        Map<ScoreKey, Integer> map = new LinkedHashMap<>();
        map.put(createKey(160,3), 35);
        map.put(createKey(160,4), 37);
        map.put(createKey(160,5), 39);
        map.put(createKey(160,6), 40);
        map.put(createKey(140,3), 32);
        map.put(createKey(140,4), 33);
        map.put(createKey(140,5), 35);
        map.put(createKey(140,6), 36);
        map.put(createKey(120,3), 28);
        map.put(createKey(120,4), 30);
        map.put(createKey(120,5), 31);
        map.put(createKey(120,6), 33);
        map.put(createKey(100,3), 25);
        map.put(createKey(100,4), 26);
        map.put(createKey(100,5), 28);
        map.put(createKey(100,6), 29);
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
