package io.github.nguba.lunanera.application.measurement;

import io.github.nguba.lunanera.domain.SetpointMeasurement;
import io.github.nguba.lunanera.domain.SetpointReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public class SetpointReceivedListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(SetpointReceivedListener.class);

    private final SetpointMeasurementRepository repository;

    public SetpointReceivedListener(final SetpointMeasurementRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onSetpointReceived(SetpointReceived event) throws SQLException {
        LOGGER.debug("{}", event);
        SetpointMeasurement measurement = new SetpointMeasurement(event.getValue(), event.when(), event.vesselId(), event.batchId());
        repository.save(measurement);
    }
}
