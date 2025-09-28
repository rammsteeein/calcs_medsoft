package com.example.demo1.common.enums;

public enum Unit {
    MKMOL("мкмоль/л"),
    MGDL("мг/дл"),
    KCAL_PER_DAY("ккал/сутки");

    private final String name;

    Unit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}