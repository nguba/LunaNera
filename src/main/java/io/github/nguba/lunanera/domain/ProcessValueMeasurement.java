package io.github.nguba.lunanera.domain;

import java.util.StringJoiner;
import java.util.UUID;

public class ProcessValueMeasurement extends Measurement<ProcessValue> {

    public ProcessValueMeasurement(final ProcessValue value, final When when, final VesselId pidId, final UUID batchId) {
        super(value, when, pidId, batchId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProcessValueMeasurement.class.getSimpleName() + "[", "]")
                .add("when=" + when)
                .add("pidId=" + pidId)
                .add("batchId=" + batchId)
                .add("value=" + value)
                .toString();
    }
}
