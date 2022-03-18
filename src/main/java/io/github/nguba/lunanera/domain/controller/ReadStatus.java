package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.*;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.math.BigDecimal;
import java.util.UUID;

// Read register 18
public class ReadStatus extends ModbusCommand {

    public ReadStatus(final EventPublisher publisher, final Vessel pid, final UUID batchId) {
        super(publisher, pid, batchId);
    }

    @Override
    protected void executeCommandOnDevice(final Vessel pid, final EventPublisher publisher, final UUID batchId) throws Exception {
        final BigDecimal value = pid.readStatus();
        publisher.publish(StatusReceived.with(ControllerStatusFactory.INSTANCE.makeFrom(value), pid.getId(), batchId));
    }
}
