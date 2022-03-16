package io.github.nguba.lunanera.domain.command;

import io.github.nguba.lunanera.domain.AbstractPidController;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.util.UUID;

public class CommandFactory {

    private final EventPublisher publisher;

    private final UUID batchId = UUID.randomUUID();

    public CommandFactory(final EventPublisher publisher) {
        this.publisher = publisher;
    }

    public ModbusCommand readProcessValue(AbstractPidController controller) {
        return new ReadProcessValue(publisher, controller, batchId);
    }

    public ModbusCommand readSetpoint(final AbstractPidController controller) {
        return new ReadSetpoint(publisher, controller, batchId);
    }
}
