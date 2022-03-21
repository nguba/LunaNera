package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.*;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.math.BigDecimal;
import java.util.StringJoiner;
import java.util.UUID;

// Read register 18
public class ReadStatus extends ModbusCommand {

    public ReadStatus(final EventPublisher publisher, final Vessel pid, final UUID batchId) {
        super(publisher, pid, batchId);
    }

    @Override
    protected void executeCommandOnDevice(final Vessel pid, final EventPublisher publisher, final UUID batchId) throws Exception {
        if(Vessel.State.OFF.equals(pid.getState()))
            return;
        final BigDecimal value = pid.readStatus();
        publisher.publish(ControllerStatusReceived.with(ControllerStatus.of(value.intValue()), pid.getId(), batchId));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReadStatus.class.getSimpleName() + "[", "]")
                .add("pid=" + pid)
                .toString();
    }
}
