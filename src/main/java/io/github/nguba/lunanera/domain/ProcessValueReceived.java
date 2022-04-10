package io.github.nguba.lunanera.domain;

public record ProcessValueReceived(ProcessValue processValue,
                                   When when, VesselId vesselId,
                                   BatchId batchId) implements DomainEvent {

    public ProcessValue processValue() {
        return processValue;
    }

    public BatchId batchId() {
        return batchId;
    }

    public static ProcessValueReceived with(final ProcessValue processValue, final VesselId vesselId, final BatchId batchId) {
        return new ProcessValueReceived(processValue, When.now(), vesselId, batchId);
    }
}
