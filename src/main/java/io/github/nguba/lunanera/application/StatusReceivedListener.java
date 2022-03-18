package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.SetpointMeasurement;
import io.github.nguba.lunanera.domain.SetpointReceived;
import io.github.nguba.lunanera.domain.StatusReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public record StatusReceivedListener() {

    public static final Logger LOGGER = LoggerFactory.getLogger(StatusReceivedListener.class);

    @EventListener
    public void onStatusReceived(StatusReceived event) throws SQLException {
        LOGGER.debug("{}", event);
    }
}
