package io.github.nguba.lunanera.domain.command;

import io.github.nguba.lunanera.domain.AbstractPidController;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.util.UUID;

public abstract class AbstractModbusCommand implements ModbusCommand {
    protected final EventPublisher publisher;
    protected final AbstractPidController pid;
    protected UUID batchId;

    public AbstractModbusCommand(final EventPublisher publisher, final AbstractPidController pid, UUID batchId) {
        this.publisher = publisher;
        this.pid = pid;
        this.batchId = batchId;
    }

    @Override
    public void execute() {
        try {
            executeCommandOnDevice(pid, publisher, batchId);

            pid.on().ifPresent(statusEvent -> {
                publisher.publish(statusEvent);
            });

        } catch (Exception e) {
            pid.off().ifPresent(event -> {
                publisher.publish(event);
            });
        }
    }

    protected abstract void executeCommandOnDevice(final AbstractPidController pid, final EventPublisher publisher, final UUID batchId) throws Exception;
}
