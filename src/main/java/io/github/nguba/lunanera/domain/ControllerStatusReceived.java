package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.domain.controller.When;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public record ControllerStatusReceived(ControllerStatus value,
                                       When when, VesselId vesselId,
                                       UUID batchId) implements DomainEvent {

    public static ControllerStatusReceived with(final ControllerStatus status, final VesselId vesselId, final UUID batchId) {
        return ControllerStatusReceived.with(status, vesselId, batchId, When.now());
    }

    public static ControllerStatusReceived with(final ControllerStatus status, final VesselId vesselId, final UUID batchId, final When when) {
        return new ControllerStatusReceived(status, when, vesselId, batchId);
    }
}