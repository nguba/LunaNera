package io.github.nguba.lunanera.domain;

import java.util.Objects;
import java.util.StringJoiner;

public class PidControllerID {

    private final Integer value;

    private PidControllerID(final Integer value) {
        if(value < Integer.valueOf(0) || value > Integer.valueOf(255)) {
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
        this.value = value;
    }

    public static PidControllerID of(final Integer value) {
        return new PidControllerID(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PidControllerID pidControllerID = (PidControllerID) o;
        return Objects.equals(value, pidControllerID.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PidControllerID.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
