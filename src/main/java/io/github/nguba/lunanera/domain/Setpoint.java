package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Setpoint(Float value) {

    public static Setpoint of(final float value) {
        return new Setpoint(value);
    }

    public static Setpoint withSingleDecimalPrecision(final BigDecimal value) {
       return Setpoint.of(value.divide(BigDecimal.valueOf(10), 1, RoundingMode.UNNECESSARY).floatValue());
    }
}
