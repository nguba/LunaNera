package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public class ProcessValueReceived implements DomainEvent {

    public ProcessValue getProcessValue() {
        return processValue;
    }

    private final ProcessValue processValue;

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

    public ProcessValueReceived(final ProcessValue processValue, final LocalDateTime when, final PidControllerID pidControllerId, final UUID batchId) {
        this.processValue = processValue;
        this.when = when;
        this.pidControllerId = pidControllerId;
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProcessValueReceived.class.getSimpleName() + "[", "]")
                .add("processValue=" + processValue)
                .add("when=" + when)
                .add("pidControllerId=" + pidControllerId)
                .add("batchId=" + batchId)
                .toString();
    }

    public static ProcessValueReceived with(final ProcessValue processValue, final PidControllerID pidControllerId, final UUID batchId) {
        return new ProcessValueReceived(processValue, LocalDateTime.now(), pidControllerId, batchId);
    }

}
