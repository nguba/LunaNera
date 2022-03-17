package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProcessValueReceived(ProcessValue processValue,
                                   LocalDateTime when, VesselId vesselId,
                                   UUID batchId) implements DomainEvent {

    public ProcessValue processValue() {
        return processValue;
    }

    public UUID batchId() {
        return batchId;
    }

    public static ProcessValueReceived with(final ProcessValue processValue, final VesselId vesselId, final UUID batchId) {
        return new ProcessValueReceived(processValue, LocalDateTime.now(), vesselId, batchId);
    }
}
