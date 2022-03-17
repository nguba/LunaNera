package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;

public record ControllerSwitchedOn(LocalDateTime when,
                                   VesselId vesselId) implements DomainEvent {

    public static ControllerSwitchedOn on(final VesselId id) {
        return new ControllerSwitchedOn(LocalDateTime.now(), id);
    }
}
