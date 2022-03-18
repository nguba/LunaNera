package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record StatusReceived(ControllerStatus value,
                             LocalDateTime when, VesselId vesselId,
                             UUID batchId) implements DomainEvent {

    public static StatusReceived with(final ControllerStatus status, final VesselId vesselId, final UUID batchId) {
        return new StatusReceived(status, LocalDateTime.now(), vesselId, batchId);
    }
}
