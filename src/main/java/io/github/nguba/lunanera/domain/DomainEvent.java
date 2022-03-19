package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.domain.controller.When;

import java.time.LocalDateTime;

public interface DomainEvent {
    VesselId vesselId();

    When when();
}
