package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.DomainEvent;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.When;

import java.time.LocalDateTime;

public record ControllerSwitchedOff(When when,
                                    VesselId vesselId) implements DomainEvent {
    public static ControllerSwitchedOff on(final VesselId id) {
        return new ControllerSwitchedOff(When.now(), id);
    }
}
