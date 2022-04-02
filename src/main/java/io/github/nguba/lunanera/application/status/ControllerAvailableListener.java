package io.github.nguba.lunanera.application.status;

import io.github.nguba.lunanera.domain.BatchId;
import io.github.nguba.lunanera.domain.instrumentation.ControllerStatus;
import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOn;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

/**
 * Monitors the availability of a controller.  This could be due to errors or the device being switched off.
 */
public record ControllerAvailableListener(StatusRepository repository) {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAvailableListener.class);

    @EventListener
    public void onPidControllerSwitchedOn(ControllerSwitchedOn event) throws SQLException {
        LOGGER.debug("Controller switched on: {}", event.vesselId());
    }

    @EventListener
    public void onPidControllerSwitchedOff(ControllerSwitchedOff event) throws SQLException {
        LOGGER.debug("Controller switched off: {}", event.vesselId());
        repository.save(ControllerStatusReceived.with(ControllerStatus.OFF, event.vesselId(), BatchId.create()));
    }
}
