package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  A setpoint is the desired or target value for an essential variable, or process value of a system.
 *
 * @param value
 */
public record Setpoint(Float value) {

    public static Setpoint of(final float value) {
        return new Setpoint(value);
    }

    /**
     * Returns the setpoint for instruments reading the value as decimal with a single point precision.
     *
     * TODO this effectively duplicates the action in the ProcessValue class.  Consider moving this into the TemperatureController implementation to avoid duplication.
     *
     * @param value
     * @return Setpoint
     */
    public static Setpoint withSingleDecimalPrecision(final BigDecimal value) {
       return Setpoint.of(value.divide(BigDecimal.valueOf(10), 1, RoundingMode.UNNECESSARY).floatValue());
    }
}
