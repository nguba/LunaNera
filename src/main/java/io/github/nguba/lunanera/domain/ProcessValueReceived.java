package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.domain.controller.When;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProcessValueReceived(ProcessValue processValue,
                                   When when, VesselId vesselId,
                                   UUID batchId) implements DomainEvent {

    public ProcessValue processValue() {
        return processValue;
    }

    public UUID batchId() {
        return batchId;
    }

    public static ProcessValueReceived with(final ProcessValue processValue, final VesselId vesselId, final UUID batchId) {
        return new ProcessValueReceived(processValue, When.now(), vesselId, batchId);
    }
}
