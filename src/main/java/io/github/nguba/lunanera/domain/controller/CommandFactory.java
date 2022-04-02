package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.BatchId;
import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import java.util.UUID;

public class CommandFactory {

    private final EventPublisher publisher;

    private final BatchId batchId = BatchId.create();

    public CommandFactory(final EventPublisher publisher) {
        this.publisher = publisher;
    }

    public Command readProcessValue(Vessel controller) {
        return new ReadProcessValue(publisher, controller, batchId);
    }

    public Command readSetpoint(final Vessel controller) {
        return new ReadSetpoint(publisher, controller, batchId);
    }

    public Command readStatus(final Vessel controller) {
        return new ReadStatus(publisher, controller, batchId);
    }
}
