package com.example.demo1.common.resources;

import java.math.BigDecimal;
import java.util.Objects;

public class ScoreKey {

    private final BigDecimal cholesterol;
    private final Integer sysAd;

    public ScoreKey(BigDecimal cholesterol, Integer sysAd) {
        this.cholesterol = cholesterol;
        this.sysAd = sysAd;
    }

    public BigDecimal getCholesterol() {
        return cholesterol;
    }

    public Integer getSysAd() {
        return sysAd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreKey)) return false;
        ScoreKey scoreKey = (ScoreKey) o;
        return Objects.equals(cholesterol, scoreKey.cholesterol) &&
                Objects.equals(sysAd, scoreKey.sysAd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cholesterol, sysAd);
    }
}