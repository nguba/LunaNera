package io.github.nguba.lunanera.domain;

import java.util.UUID;

public record ControllerStatusReceived(ControllerStatus value,
                                       When when, VesselId vesselId,
                                       BatchId batchId) implements DomainEvent {

    public static ControllerStatusReceived with(final ControllerStatus status, final VesselId vesselId, final BatchId batchId) {
        return ControllerStatusReceived.with(status, vesselId, batchId, When.now());
    }

    public static ControllerStatusReceived with(final ControllerStatus status, final VesselId vesselId, final BatchId batchId, final When when) {
        return new ControllerStatusReceived(status, when, vesselId, batchId);
    }
}
