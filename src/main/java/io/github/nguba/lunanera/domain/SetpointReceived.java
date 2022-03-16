package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public class SetpointReceived implements DomainEvent {

    public Setpoint getValue() {
        return setpoint;
    }

    private final Setpoint setpoint;

    private final LocalDateTime when;

    private final PidControllerID pidControllerId;

    private final UUID batchId;

    @Override
    public LocalDateTime getWhen() {
        return when;
    }

    public PidControllerID getDeviceId() {
        return pidControllerId;
    }

    public UUID getBatchId() {
        return batchId;
    }

    public SetpointReceived(final Setpoint setpoint, final LocalDateTime when, final PidControllerID pidControllerId, final UUID batchId) {
        this.setpoint = setpoint;
        this.when = when;
        this.pidControllerId = pidControllerId;
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SetpointReceived.class.getSimpleName() + "[", "]")
                .add("processValue=" + setpoint)
                .add("when=" + when)
                .add("pidControllerId=" + pidControllerId)
                .add("batchId=" + batchId)
                .toString();
    }

    public static SetpointReceived with(final Setpoint value, final PidControllerID pidControllerId, final UUID batchId) {
        return new SetpointReceived(value, LocalDateTime.now(), pidControllerId, batchId);
    }

}
