package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.infrastructure.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public abstract class ModbusCommand implements Command {
    protected final EventPublisher publisher;
    protected final Vessel pid;
    protected UUID batchId;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModbusCommand.class);

    public ModbusCommand(final EventPublisher publisher, final Vessel pid, UUID batchId) {
        this.publisher = publisher;
        this.pid = pid;
        this.batchId = batchId;
    }

    @Override
    public void execute() {
        try {
            executeCommandOnDevice(pid, publisher, batchId);
            pid.on().ifPresent(event -> {
                publisher.publish(event);
            });

        } catch (Exception e) {
            LOGGER.warn("Modbus", e);
            pid.off().ifPresent(event -> {
                publisher.publish(event);
            });
        }
    }

    protected abstract void executeCommandOnDevice(final Vessel pid, final EventPublisher publisher, final UUID batchId) throws Exception;
}
