package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.ProcessValueMeasurement;
import io.github.nguba.lunanera.domain.ProcessValueReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public class ProcessValueMeasuredListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessValueMeasuredListener.class);

    private final ProcessValueMeasurementRepository repository;

    public ProcessValueMeasuredListener(final ProcessValueMeasurementRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onProcessValueMeasured(final ProcessValueReceived event) {
        try {
            ProcessValueMeasurement temp =
                    new ProcessValueMeasurement(event.getProcessValue(), event.getWhen(), event.getDeviceId(), event.getBatchId());
            repository.save(temp);
        } catch (SQLException e) {
            LOGGER.warn("Error persisting {}", event.getProcessValue(), e);
        }
    }
}


