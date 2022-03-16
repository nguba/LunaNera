package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;

public class PidControllerSwitchedOn implements DomainEvent {
    private final LocalDateTime when;
    private final PidControllerID id;

    public PidControllerSwitchedOn(final LocalDateTime when, final PidControllerID id) {
        this.when = when;
        this.id = id;
    }

    public PidControllerID getId() {
        return id;
    }

    public static PidControllerSwitchedOn on(final PidControllerID id) {
        return new PidControllerSwitchedOn(LocalDateTime.now(), id);
    }

    @Override
    public LocalDateTime getWhen() {
        return when;
    }
}
