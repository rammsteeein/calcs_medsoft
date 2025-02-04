package com.example.demo1.common.enums;

public enum Gender {
    MALE("Мужчина"),
    FEMALE("Женщина");

    private final String name;

    private Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}