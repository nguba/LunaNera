package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.ProcessValueReceived;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.math.BigDecimal;
import java.util.StringJoiner;
import java.util.UUID;

public class ReadProcessValue extends ModbusCommand {

    public ReadProcessValue(final EventPublisher publisher, final Vessel pid, UUID batchId) {
        super(publisher, pid, batchId);
    }

    @Override
    protected void executeCommandOnDevice(final Vessel pid, final EventPublisher publisher, UUID batchId) throws Exception {
        final BigDecimal value = pid.readProcessValue();
        final ProcessValue processValue = ProcessValue.withSingleDecimalPrecision(value);
        final ProcessValueReceived event = ProcessValueReceived.with(processValue, pid.getId(), batchId);
        publisher.publish(event);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReadProcessValue.class.getSimpleName() + "[", "]")
                .add("publisher=" + publisher)
                .add("pid=" + pid)
                .toString();
    }
}
