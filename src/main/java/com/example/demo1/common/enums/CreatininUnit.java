package com.example.demo1.common.enums;

public enum CreatininUnit {
    MKMOL("мкмоль/л"), MGDL("мг/дл");

    private final String name;

    private CreatininUnit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}