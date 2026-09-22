package com.example.demo1.controls.APACHEII;
public class APACHEIIResult {
    private final int value;
    private final String interpretation;
    public APACHEIIResult(int value,String interpretation){
        this.value=value;this.interpretation=interpretation;
    } public int getValue(){
        return value;
    }
    @Override public String toString(){
        return String.format("Баллы APACHE II: %d%nИнтерпретация: %s",value,interpretation);
    }
}
