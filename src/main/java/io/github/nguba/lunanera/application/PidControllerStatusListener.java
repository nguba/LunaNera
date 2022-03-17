package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOn;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class PidControllerStatusListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PidControllerStatusListener.class);

    @EventListener
    public void onPidControllerSwitchedOff(ControllerSwitchedOn event) {
        LOGGER.info("Controller switched off: {}", event.vesselId());
    }

    @EventListener
    public void onPidControllerSwitchedOn(ControllerSwitchedOff event) {
        LOGGER.info("Controller switched on: {}", event.vesselId());
    }
}
