package io.github.nguba.lunanera.domain;

public record VesselId(Integer value) {

    public VesselId {
        if (value != null && (value < 0 || value > 255)) {
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
    }

    public static VesselId of(final Integer value) {
        return new VesselId(value);
    }
}
