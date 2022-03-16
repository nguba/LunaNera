package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.PidControllerSwitchedOff;
import io.github.nguba.lunanera.domain.PidControllerSwitchedOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class PidControllerStatusListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PidControllerStatusListener.class);

    private final PidControllerStatusRepository repository;

    public PidControllerStatusListener(final PidControllerStatusRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onPidControllerSwitchedOff(PidControllerSwitchedOff event) {
        LOGGER.info("Controller switched off: {}", event.getId());
    }

    @EventListener
    public void onPidControllerSwitchedOn(PidControllerSwitchedOn event) {
        LOGGER.info("Controller switched on: {}", event.getId());
    }
}
