package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public record ControllerStatusReceivedListener() {

    public static final Logger LOGGER = LoggerFactory.getLogger(ControllerStatusReceivedListener.class);

    @EventListener
    public void onStatusReceived(ControllerStatusReceived event) throws SQLException {
        LOGGER.debug("{}", event);
    }
}
