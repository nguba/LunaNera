package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public class SetpointMeasurement extends TimestampedMeasurement<Setpoint> {

    public SetpointMeasurement(final Setpoint value, final LocalDateTime when, final PidControllerID pidId, final UUID batchId) {
        super(value, when, pidId, batchId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SetpointMeasurement.class.getSimpleName() + "[", "]")
                .add("when=" + when)
                .add("pidId=" + pidId)
                .add("batchId=" + batchId)
                .add("value=" + value)
                .toString();
    }
}
