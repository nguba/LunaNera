package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;

public record ControllerSwitchedOff(LocalDateTime when,
                                    VesselId vesselId) implements DomainEvent {
    public static ControllerSwitchedOff on(final VesselId id) {
        return new ControllerSwitchedOff(LocalDateTime.now(), id);
    }
}
