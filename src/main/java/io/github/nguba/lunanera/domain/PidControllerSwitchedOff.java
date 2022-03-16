package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;

public class PidControllerSwitchedOff implements DomainEvent {

    private final LocalDateTime when;
    private final PidControllerID id;

    public PidControllerSwitchedOff(final LocalDateTime when, final PidControllerID id) {
        this.when = when;
        this.id = id;
    }

    public PidControllerID getId() {
        return id;
    }

    public static PidControllerSwitchedOff on(final PidControllerID id) {
        return new PidControllerSwitchedOff(LocalDateTime.now(), id);
    }

    @Override
    public LocalDateTime getWhen() {
        return when;
    }
}
