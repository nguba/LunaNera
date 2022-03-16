package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.StringJoiner;

public class ProcessValue implements ValueObject<Float> {
    private final Float value;

    private ProcessValue(final float value) {
        this.value = value;
    }

    public static ProcessValue of(final float value) {
        return new ProcessValue(value);
    }

    public static ProcessValue withSingleDecimalPrecision(final BigDecimal value) {
       return ProcessValue.of(value.divide(BigDecimal.valueOf(10), 1, RoundingMode.UNNECESSARY).floatValue());
    }

    @Override
    public Float value() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProcessValue.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProcessValue that = (ProcessValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
