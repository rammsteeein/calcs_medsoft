package com.example.demo1.common.enums;

import java.util.Arrays;
import java.util.List;

public enum Unit {
    MKMOL("мкмоль/л"),
    MGDL("мг/дл"),
    KCAL_PER_DAY("ккал/сутки");

    private final String name;

    Unit(String name) {
        this.name = name;
    }

    public static List<Unit> forCkdEpi() {
        return Arrays.asList(MKMOL, MGDL);
    }

    public static Unit defaultForCkdEpi() {
        return MKMOL;
    }

    @Override
    public String toString() {
        return name;
    }
}