package io.github.nguba.lunanera.domain;

public interface DomainEvent {
    VesselId vesselId();

    When when();
}
