package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.SetpointMeasurement;
import io.github.nguba.lunanera.domain.SetpointReceived;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public class SetpointReceivedListener {

    private final SetpointMeasurementRepository repository;

    public SetpointReceivedListener(final SetpointMeasurementRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onSetpointReceived(SetpointReceived event) throws SQLException {
        SetpointMeasurement measurement = new SetpointMeasurement(event.getValue(), event.when(), event.vesselId(), event.batchId());
        //repository.delete(event.getDeviceId().getValue().intValue());
        repository.save(measurement);
    }
}
