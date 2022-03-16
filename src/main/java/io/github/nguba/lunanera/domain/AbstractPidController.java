package io.github.nguba.lunanera.domain;

import java.util.Optional;

public abstract class AbstractPidController implements PidController {

    private State state = State.ON; // could be named as heating / off instead....

    protected AbstractPidController(PidControllerID id) {
        this.id = id;
    }

    @Override
    public State getState() {
        return state;
    }

    public Optional<PidControllerSwitchedOff> off() {
        if(State.OFF.equals(state)) return Optional.empty();
        this.state = State.OFF;
        return Optional.of(PidControllerSwitchedOff.on(id));
    }

    public Optional<PidControllerSwitchedOn> on() {
        if(State.ON.equals(state)) return Optional.empty();

        this.state = State.ON;
        return Optional.of(PidControllerSwitchedOn.on(id));
    }

    private final PidControllerID id;

    @Override
    public PidControllerID getId() {
        return id;
    }
}
