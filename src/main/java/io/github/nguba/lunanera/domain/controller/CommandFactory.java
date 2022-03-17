package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.PidController;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.util.UUID;

public class CommandFactory {

    private final EventPublisher publisher;

    private final UUID batchId = UUID.randomUUID();

    public CommandFactory(final EventPublisher publisher) {
        this.publisher = publisher;
    }

    public Command readProcessValue(PidController controller) {
        return new ReadProcessValue(publisher, controller, batchId);
    }

    public Command readSetpoint(final PidController controller) {
        return new ReadSetpoint(publisher, controller, batchId);
    }
}
