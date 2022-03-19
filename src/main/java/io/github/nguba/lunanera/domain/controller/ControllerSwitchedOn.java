package io.github.nguba.lunanera.domain.controller;

import io.github.nguba.lunanera.domain.DomainEvent;
import io.github.nguba.lunanera.domain.VesselId;

import java.time.LocalDateTime;

public record ControllerSwitchedOn(When when,
                                   VesselId vesselId) implements DomainEvent {

    public static ControllerSwitchedOn on(final VesselId id) {
        return new ControllerSwitchedOn(When.now(), id);
    }
}
