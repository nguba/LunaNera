package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class PidControllerStatus extends TimestampedMeasurement<PidController.State> {

    public PidControllerStatus(final PidController.State value, final LocalDateTime when, final PidControllerID pidId, final UUID batchId) {
        super(value, when, pidId, batchId);
    }

    public static PidControllerStatus with(final PidController.State value, final LocalDateTime when, final PidControllerID pidId, final UUID batchId) {
        return new PidControllerStatus(value, when, pidId, batchId);
    }
}
