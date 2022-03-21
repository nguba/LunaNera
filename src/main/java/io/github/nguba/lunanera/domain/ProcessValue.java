package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record ProcessValue(Float value)  {

    public static ProcessValue of(final float value) {
        return new ProcessValue(value);
    }

    public static ProcessValue withSingleDecimalPrecision(final BigDecimal value) {
       return ProcessValue.of(value.divide(BigDecimal.valueOf(10), 1, RoundingMode.UNNECESSARY).floatValue());
    }
}
