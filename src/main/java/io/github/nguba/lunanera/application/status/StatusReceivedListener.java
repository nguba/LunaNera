package io.github.nguba.lunanera.application.status;

import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public record StatusReceivedListener(StatusRepository repository) {

    public static final Logger LOGGER = LoggerFactory.getLogger(StatusReceivedListener.class);

    @EventListener
    public void onStatusReceived(ControllerStatusReceived event) throws SQLException {
        repository.save(event);
    }
}
