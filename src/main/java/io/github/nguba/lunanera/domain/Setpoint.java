package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.StringJoiner;

public class Setpoint implements ValueObject<Float> {
    private final Float value;

    private Setpoint(final float value) {
        this.value = value;
    }

    public static Setpoint of(final float value) {
        return new Setpoint(value);
    }

    public static Setpoint withSingleDecimalPrecision(final BigDecimal value) {
       return Setpoint.of(value.divide(BigDecimal.valueOf(10), 1, RoundingMode.UNNECESSARY).floatValue());
    }

    @Override
    public Float value() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Setpoint.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Setpoint that = (Setpoint) o;
        return Float.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
