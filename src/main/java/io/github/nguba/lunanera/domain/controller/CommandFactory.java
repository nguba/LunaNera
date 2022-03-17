package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.util.UUID;

public class CommandFactory {

    private final EventPublisher publisher;

    private final UUID batchId = UUID.randomUUID();

    public CommandFactory(final EventPublisher publisher) {
        this.publisher = publisher;
    }

    public Command readProcessValue(Vessel controller) {
        return new ReadProcessValue(publisher, controller, batchId);
    }

    public Command readSetpoint(final Vessel controller) {
        return new ReadSetpoint(publisher, controller, batchId);
    }
}
