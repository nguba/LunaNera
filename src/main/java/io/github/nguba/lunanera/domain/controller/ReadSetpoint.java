package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.BatchId;
import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.domain.Setpoint;
import io.github.nguba.lunanera.domain.SetpointReceived;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.math.BigDecimal;
import java.util.StringJoiner;
import java.util.UUID;

public class ReadSetpoint extends ModbusCommand {

    public ReadSetpoint(final EventPublisher publisher, final Vessel pid, final BatchId batchId) {
        super(publisher, pid, batchId);
    }

    @Override
    protected void executeCommandOnDevice(final Vessel pid, final EventPublisher publisher, final BatchId batchId) throws Exception {
        if(Vessel.State.OFF.equals(pid.getState()))
            return;

        final BigDecimal value = pid.readSetpoint();
        final Setpoint setpoint = Setpoint.withSingleDecimalPrecision(value);
        final SetpointReceived event = SetpointReceived.with(setpoint, pid.getId(), batchId);
        publisher.publish(event);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReadSetpoint.class.getSimpleName() + "[", "]")
                .add("pid=" + pid)
                .toString();
    }
}
