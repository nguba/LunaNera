package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A process value (process variable or process parameter) is the current measured value of a particular part of a
 * process which is being monitored or controlled. An example of this would be the temperature of a mash tun.
 *
 * @param value
 */
public record ProcessValue(Float value)  {

    public static ProcessValue of(final float value) {
        return new ProcessValue(value);
    }

    /**
     * Returns the process value for instruments reading the value as decimal with a single point precision.
     *
     * @param value
     * @return ProcessValue
     */
    public static ProcessValue withSingleDecimalPrecision(final BigDecimal value) {
       return ProcessValue.of(value.divide(BigDecimal.valueOf(10), 1, RoundingMode.UNNECESSARY).floatValue());
    }
}
