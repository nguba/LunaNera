package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.domain.controller.When;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public record SetpointReceived(Setpoint setpoint, When when,
                               VesselId vesselId,
                               UUID batchId) implements DomainEvent {

    public Setpoint getValue() {
        return setpoint;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SetpointReceived.class.getSimpleName() + "[", "]")
                .add("processValue=" + setpoint)
                .add("when=" + when)
                .add("vesselId=" + vesselId)
                .add("batchId=" + batchId)
                .toString();
    }

    public static SetpointReceived with(final Setpoint value, final VesselId vesselId, final UUID batchId) {
        return new SetpointReceived(value, When.now(), vesselId, batchId);
    }
}
