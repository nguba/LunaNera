package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOff;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOn;

import java.math.BigDecimal;
import java.util.Optional;

public abstract class Vessel {

    private State state = State.ON; // could be named as heating / off instead....

    protected Vessel(VesselId id) {
        this.id = id;
    }

    public enum State {
        ON, OFF
    }
    public State getState() {
        return state;
    }

    public Optional<ControllerSwitchedOn> off() {
        if(State.OFF.equals(state)) return Optional.empty();
        this.state = State.OFF;
        return Optional.of(ControllerSwitchedOn.on(id));
    }

    public Optional<ControllerSwitchedOff> on() {
        if(State.ON.equals(state)) return Optional.empty();

        this.state = State.ON;
        return Optional.of(ControllerSwitchedOff.on(id));
    }

    protected final VesselId id;

    public VesselId getId() {
        return id;
    }

    public abstract String getName();

    public abstract BigDecimal readProcessValue() throws Exception;

    public abstract BigDecimal readSetpoint() throws Exception;

    public abstract BigDecimal readStatus() throws Exception;
}
