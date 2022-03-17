package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.PidController;
import io.github.nguba.lunanera.domain.Setpoint;
import io.github.nguba.lunanera.domain.SetpointReceived;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.math.BigDecimal;
import java.util.UUID;

public class ReadSetpoint extends ModbusCommand {

    public ReadSetpoint(final EventPublisher publisher, final PidController pid, final UUID batchId) {
        super(publisher, pid, batchId);
    }

    @Override
    protected void executeCommandOnDevice(final PidController pid, final EventPublisher publisher, final UUID batchId) throws Exception {
        final BigDecimal value = pid.readSetpoint();
        final Setpoint setpoint = Setpoint.withSingleDecimalPrecision(value);
        final SetpointReceived event = SetpointReceived.with(setpoint, pid.getId(), batchId);
        publisher.publish(event);
    }
}
