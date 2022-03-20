package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOn;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

/**
 * Monitors the availability of a controller.  This could be due to errors or the device being switched off.
 */
public class ControllerAvailableListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAvailableListener.class);

    @EventListener
    public void onPidControllerSwitchedOff(ControllerSwitchedOn event) {
        LOGGER.debug("Controller switched off: {}", event.vesselId());
    }

    @EventListener
    public void onPidControllerSwitchedOn(ControllerSwitchedOff event) {
        LOGGER.debug("Controller switched on: {}", event.vesselId());
    }
}
